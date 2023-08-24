package bootcamp.sparta.advencedpartymission1.todo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.abstract.BaseActivity
import bootcamp.sparta.advencedpartymission1.databinding.ActivityWriteTodoBinding
import bootcamp.sparta.advencedpartymission1.main.MainActivity
import kotlin.random.Random

class WriteTodoActivity : BaseActivity() {
    private lateinit var binding: ActivityWriteTodoBinding

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, WriteTodoActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolBarTitle(findViewById(R.id.layout_toolbar_write), "할일", true)
        addButtonListener()
    }


    // 툴바 뒤로가기버튼 눌렀을때 동작
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 내용추가버튼
    private fun addButtonListener() {
        binding.btnAddWrite.setOnClickListener {
            if (isEditTextEmpty()) {
                toastMsg("제목 혹은 내용을 입력해주셔야 합니다.")
            } else {
                val intent = MainActivity.newIntent(this).apply {
                    putExtra(
                        getString(R.string.intent_todoModel),
                        TodoModel(
                            title = binding.etTitleWrite.text.toString(),
                            image = getRandomImage(),
                            contents = binding.etContentWrite.text.toString(),
                        )
                    )
                }
                intent.putExtra("mybundle", TodoModel::class.java)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    // EditText Empty 체크
    private fun isEditTextEmpty(): Boolean = binding.etTitleWrite.text.isEmpty() || binding.etContentWrite.text.isEmpty()

    private fun getRandomImage(): Int {
        val charList = listOf(
            R.drawable.character_ddolgi,
            R.drawable.character_ddunge,
            R.drawable.character_hochi,
            R.drawable.character_saechomi,
            R.drawable.character_drago,
            R.drawable.character_yoreungi,
            R.drawable.character_macho,
            R.drawable.character_mimi,
            R.drawable.character_mungchi,
            R.drawable.character_kiki,
            R.drawable.character_gangdari,
            R.drawable.character_zhingzhing,
        )
        val randNum = Random.nextInt(0, 12)
        return charList[randNum]
    }
}