package bootcamp.sparta.advencedpartymission1.todo

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.abstract.BaseActivity
import bootcamp.sparta.advencedpartymission1.data.Todo
import bootcamp.sparta.advencedpartymission1.data.TodoData
import bootcamp.sparta.advencedpartymission1.main.MainActivity
import kotlin.random.Random

class WriteTodoActivity : BaseActivity() {
    private val btn_add: Button by lazy { findViewById(R.id.btn_add_write) }
    private val et_title : EditText by lazy { findViewById(R.id.et_title_write) }
    private val et_content : EditText by lazy { findViewById(R.id.et_content_write) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_todo)

        setToolBarTitle(findViewById(R.id.layout_toolbar_write), "할일", true)
        addButtonListener()
    }


    // 툴바 뒤로가기버튼 눌렀을때 동작
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 내용추가버튼
    private fun addButtonListener() {
        btn_add.setOnClickListener {
            if(isEditTextEmpty()) {
                toastMsg("제목 혹은 내용을 입력해주셔야 합니다.")
            } else {
                TodoData.setTodoList(
                    Todo(
                        title = et_title.text.toString(),
                        contents = et_content.text.toString(),
                        image = getRandomImage(),
                    )
                )
                intentChangeActivity(MainActivity::class.java)
                finish()
            }
        }
    }

    // EditText Empty 체크
    private fun isEditTextEmpty() : Boolean = et_title.text.isEmpty() || et_content.text.isEmpty()

    private fun getRandomImage() : Drawable? {
        val charList = listOf(
            getDrawable(R.drawable.character_ddolgi),
            getDrawable(R.drawable.character_ddunge),
            getDrawable(R.drawable.character_hochi),
            getDrawable(R.drawable.character_saechomi),
            getDrawable(R.drawable.character_drago),
            getDrawable(R.drawable.character_yoreungi),
            getDrawable(R.drawable.character_macho),
            getDrawable(R.drawable.character_mimi),
            getDrawable(R.drawable.character_mungchi),
            getDrawable(R.drawable.character_kiki),
            getDrawable(R.drawable.character_gangdari),
            getDrawable(R.drawable.character_zhingzhing),
        )
        val randNum = Random.nextInt(0,12)
        return charList[randNum]
    }
}