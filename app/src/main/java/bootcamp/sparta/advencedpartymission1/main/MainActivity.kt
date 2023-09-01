package bootcamp.sparta.advencedpartymission1.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import bootcamp.sparta.advencedpartymission1.databinding.MainActivityBinding
import bootcamp.sparta.advencedpartymission1.todo.TodoModel
import bootcamp.sparta.advencedpartymission1.todo.add.TodoContentActivity
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private val viewPagerAdapter by lazy {
        MainViewPagerAdapter(this@MainActivity)
    }

    private val addTodoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == RESULT_OK) {
            val todoModel = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra(TodoContentActivity.EXTRA_MODEL, TodoModel::class.java)
            } else {
                result.data?.getParcelableExtra(TodoContentActivity.EXTRA_MODEL)
            }

            viewPagerAdapter.getTodoFragment().apply {
                todoModel?.let{
                    this?.setTodoContent(it)
                }
            }
        }
    }

    init {
        instance = this
    }

    companion object {
        private var instance : MainActivity? = null

        fun getInstance() = instance
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        mainViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(mainTabLayout, mainViewPager) { tab, position ->
            tab.setText(viewPagerAdapter.getTitle(position))
        }.attach()

        mainViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (viewPagerAdapter.isTodoFragment(position)) {
                    mainFab.show()
                } else {
                    mainFab.hide()
                }
            }
        })

        mainFab.setOnClickListener {
            addTodoLauncher.launch(
                TodoContentActivity.newIntentForAdd(this@MainActivity)
            )
        }
    }

    fun modifyTodoItem(origin: TodoModel, modify: TodoModel) {
        viewPagerAdapter.getTodoFragment()?.modifyTodoItem(origin, modify)
    }

    fun removeTodoItem(item: TodoModel) {
        viewPagerAdapter.getTodoFragment()?.apply {
            removeTodoContent(item)
        }
    }
}