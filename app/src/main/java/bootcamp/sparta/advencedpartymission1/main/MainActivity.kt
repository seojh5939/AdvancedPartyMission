package bootcamp.sparta.advencedpartymission1.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import bootcamp.sparta.advencedpartymission1.bookmark.BookmarkFragment
import bootcamp.sparta.advencedpartymission1.bookmark.BookmarkModel
import bootcamp.sparta.advencedpartymission1.databinding.MainActivityBinding
import bootcamp.sparta.advencedpartymission1.todo.TodoFragment
import bootcamp.sparta.advencedpartymission1.todo.TodoModel
import bootcamp.sparta.advencedpartymission1.todo.TodoViewModel
import bootcamp.sparta.advencedpartymission1.todo.content.TodoContentActivity
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding : MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }
    private val viewPagerAdapter : ViewPagerAdapter by lazy {
        ViewPagerAdapter(this@MainActivity)
    }
    private val todoViewModel by lazy {
        ViewModelProvider(this)[TodoViewModel::class.java]
    }

    private val addTodoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == RESULT_OK) {
            val modelItem = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra(TodoContentActivity.EXTRA_TODO_MODEL, TodoModel::class.java)
            } else {
                result.data?.getParcelableExtra(TodoContentActivity.EXTRA_TODO_MODEL)
            }
            modelItem?.let{ todoViewModel.addTodoItem(it) }
        }
    }

    init {
        _instance = this
    }

    companion object {
        private var _instance: MainActivity? = null
        val instance get() = _instance!!

        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        // ViewPager + TabLayout
        mainViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(mainTabLayout, mainViewPager) { tab, position ->
            tab.setText(viewPagerAdapter.getTitleRes(position))
        }.attach()

        mainViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(viewPagerAdapter.getFragment(position) is TodoFragment) {
                    mainAddTodo.show()
                } else {
                    mainAddTodo.hide()
                }
            }
        })

        mainAddTodo.setOnClickListener {
            addTodoLauncher.launch(TodoContentActivity.newIntentForAdd(this@MainActivity))
        }
    }
}