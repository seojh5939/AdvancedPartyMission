package bootcamp.sparta.advencedpartymission1.todo.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bootcamp.sparta.advencedpartymission1.databinding.TodoContentActivityBinding
import bootcamp.sparta.advencedpartymission1.todo.TodoModel

class TodoContentActivity : AppCompatActivity() {
    private lateinit var binding: TodoContentActivityBinding

    companion object {
        const val EXTRA_MODEL = "extra_model"
        fun newIntent(context: Context): Intent {
            return Intent(context, TodoContentActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoContentActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        tbTodoContentToolbar.setNavigationOnClickListener {
            finish()
        }

        btnTodoContentPositive.setOnClickListener {
            val intent = Intent().apply {
                putExtra(EXTRA_MODEL, TodoModel(
                    etTodoContentTitle.text.toString(),
                    etTodoContentContent.text.toString()
                ))
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}