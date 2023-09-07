package bootcamp.sparta.advencedpartymission1.todo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.databinding.WriteTodoActivityBinding
import bootcamp.sparta.advencedpartymission1.main.MainActivity
import kotlin.random.Random

class WriteTodoActivity : AppCompatActivity() {
    private val binding: WriteTodoActivityBinding by lazy {
        WriteTodoActivityBinding.inflate(layoutInflater)
    }

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, WriteTodoActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding){

    }
}