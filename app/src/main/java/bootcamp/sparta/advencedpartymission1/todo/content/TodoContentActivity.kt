package bootcamp.sparta.advencedpartymission1.todo.content

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.databinding.WriteTodoActivityBinding
import bootcamp.sparta.advencedpartymission1.main.MainActivity
import bootcamp.sparta.advencedpartymission1.todo.TodoModel

class TodoContentActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TODO_ENTRY_TYPE = "extra_todo_entry_type"
        const val EXTRA_TODO_MODEL = "extra_todo_model"
        const val EXTRA_TODO_POSITION = "extra_todo_position"

        fun newIntentForAdd(
            context: Context
        ) = Intent(context, TodoContentActivity::class.java).apply {
            putExtra(EXTRA_TODO_ENTRY_TYPE, TodoContentType.ADD.name)
        }

        fun newIntentForEdit(
            context: Context,
            position: Int,
            todoModel: TodoModel
        ): Intent {
            return Intent(context, TodoContentActivity::class.java).apply {
                putExtra(EXTRA_TODO_ENTRY_TYPE, TodoContentType.EDIT.name)
                putExtra(EXTRA_TODO_POSITION, position)
                putExtra(EXTRA_TODO_MODEL, todoModel)
            }
        }
    }

    private val binding: WriteTodoActivityBinding by lazy {
        WriteTodoActivityBinding.inflate(layoutInflater)
    }

    private val entryType by lazy {
        TodoContentType.from(intent?.getStringExtra(EXTRA_TODO_ENTRY_TYPE))
    }

    private val todoModel by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(EXTRA_TODO_MODEL, TodoModel::class.java)
        } else {
            intent?.getParcelableExtra(EXTRA_TODO_MODEL)
        }
    }

    private val position by lazy {
        intent.getIntExtra(EXTRA_TODO_POSITION, -1)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        initData()
    }

    private fun initView() = with(binding) {
        writeSubmit.setText(
            when(entryType) {
                TodoContentType.EDIT -> "수정"
                else -> "등록"
            }
        )

        // 삭제버튼 Visibility
        when(entryType) {
            TodoContentType.EDIT -> writeDelete.visibility = View.VISIBLE
            else -> writeDelete.visibility = View.INVISIBLE
        }

        writeToolbar.setNavigationOnClickListener {
            finish()
        }

        // submit
        writeSubmit.setOnClickListener {
            val intent = MainActivity.newIntent(this@TodoContentActivity).apply {
                if (writeTitleEt.text.isEmpty() || writeContentEt.text.isEmpty()) {
                    Toast.makeText(
                        this@TodoContentActivity,
                        getString(R.string.edit_text_empty_toast_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                putExtra(
                    EXTRA_TODO_ENTRY_TYPE,
                    entryType?.name
                )
                putExtra(
                    EXTRA_TODO_POSITION,
                    position
                )
                putExtra(
                    EXTRA_TODO_MODEL,
                    TodoModel(
                        title = writeTitleEt.text.toString(),
                        content = writeContentEt.text.toString()
                    )
                )
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun initData() = with(binding) {
        todoModel?.apply {
            writeTitleEt.setText(this.title)
            writeContentEt.setText(this.content)
        }
    }
}
