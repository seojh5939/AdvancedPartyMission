package bootcamp.sparta.advencedpartymission1.todo.add

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.databinding.TodoContentActivityBinding
import bootcamp.sparta.advencedpartymission1.main.MainActivity
import bootcamp.sparta.advencedpartymission1.todo.TodoFragment
import bootcamp.sparta.advencedpartymission1.todo.TodoModel

enum class TodoContentType {
    NONE, ADD, EDIT
}

class TodoContentActivity : AppCompatActivity() {
    private lateinit var binding: TodoContentActivityBinding

    companion object {
        const val EXTRA_MODEL = "extra_model"
        private var _contentType = TodoContentType.NONE
        fun getContentType() = _contentType

        fun newIntentForAdd(context: Context): Intent =
            Intent(context, TodoContentActivity::class.java).apply {
                _contentType = TodoContentType.ADD
            }

        fun newIntentForEdit(context: Context): Intent =
            Intent(context, TodoContentActivity::class.java).apply {
                _contentType = TodoContentType.EDIT
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoContentActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        // 뒤로가기
        tbTodoContentToolbar.setNavigationOnClickListener {
            finish()
        }

        when (getContentType()) {
            TodoContentType.ADD -> {
                // 등록버튼
                btnTodoContentSave.setOnClickListener {
                    val intent = Intent().apply {
                        putExtra(
                            EXTRA_MODEL, TodoModel(
                                title = etTodoContentTitle.text.toString(),
                                content = etTodoContentContent.text.toString()
                            )
                        )
                    }
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }

            TodoContentType.EDIT -> {
                // MainActivity와의 intent처리
                val todoModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra(TodoFragment.EXTRA_MODIFY, TodoModel::class.java)
                } else {
                    intent.getParcelableExtra(TodoFragment.EXTRA_MODIFY)
                }

                // EditText 바인딩
                todoModel?.let {
                    etTodoContentTitle.setText(it.title)
                    etTodoContentContent.setText(it.content)
                }

                // 버튼 텍스트 && 삭제버튼 표시
                btnTodoContentSave.text = getString(R.string.todo_content_modify_button_text)
                btnTodoContentRemove.visibility = View.VISIBLE

                // 삭제버튼 클릭이벤트
                removeButtonClickListener(this@TodoContentActivity, todoModel)

                // 수정버튼
                btnTodoContentSave.setOnClickListener {
                    MainActivity.getInstance()?.modifyTodoItem(
                        todoModel!!,
                        TodoModel(
                            etTodoContentTitle.text.toString(),
                            etTodoContentContent.text.toString()
                        )
                    )
                    finish()
                }
            }

            else -> {}
        }
    }

    // 삭제버튼 클릭이벤트
    private fun removeButtonClickListener(
        context: Context,
        todoModel: TodoModel?,
    ) {
        todoModel?.let {
            binding.btnTodoContentRemove.setOnClickListener {
                // 다이얼로그 클릭이벤트 정의
                val listener = object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        when (p1) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                MainActivity.getInstance()?.removeTodoItem(todoModel)
                                finish()
                            }
                        }
                    }
                }

                // 다이얼로그
                AlertDialog.Builder(it.context)
                    .setMessage(context.getString(R.string.todo_content_dialog_remove_msg))
                    .setPositiveButton(
                        context.getString(R.string.common_button_text_ok),
                        listener
                    )
                    .setNegativeButton(
                        context.getString(R.string.common_button_text_cancel),
                        listener
                    )
                    .setCancelable(false)
                    .create()
                    .show()
            }
        }
    }
}
