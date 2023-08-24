package bootcamp.sparta.advencedpartymission1.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.abstract.BaseActivity
import bootcamp.sparta.advencedpartymission1.bookmark.BookmarkFragment
import bootcamp.sparta.advencedpartymission1.databinding.ActivityMainBinding
import bootcamp.sparta.advencedpartymission1.todo.TodoFragment
import bootcamp.sparta.advencedpartymission1.todo.TodoModel
import bootcamp.sparta.advencedpartymission1.todo.WriteTodoActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val fab: FloatingActionButton by lazy { binding.fabMain }
    private val viewPager: ViewPager2 by lazy { binding.vpMain }
    private val tabLayout: TabLayout by lazy { binding.tlMain }

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setToolBarTitle(findViewById(R.id.layout_toolbar_main), "Camp", false)
        initViewPager()
        setViewPagerChangeListener()
        attachTabLayout()
        setFabClickListener()
        registActivityResult()
    }

    private fun initViewPager() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = viewPagerAdapter
        addTabs()
    }

    private fun addTabs() {
        viewPagerAdapter.addTabs(
            MainTabs(
                TodoFragment.getInstance(),
                R.string.fragment_todo_tab_title
            )
        )
        viewPagerAdapter.addTabs(
            MainTabs(
                BookmarkFragment.getInstance(),
                R.string.fragment_bookmark_tab_title
            )
        )
    }

    // ViewPager BookMark에서 fab 숨기기
    private fun setViewPagerChangeListener() {
        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 1) {
                    fab.hide()
                } else {
                    fab.show()
                }
            }
        })
    }

    // TabLayout
    private fun attachTabLayout() {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setText(viewPagerAdapter.getTitleRes(position))
        }.attach()
    }

    private fun setFabClickListener() {
        fab.setOnClickListener {
            val intent = WriteTodoActivity.newIntent(this)
            resultLauncher.launch(intent)
        }
    }

    private fun registActivityResult() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        val res = result.data?.getParcelableExtra(
                            getString(R.string.intent_todoModel),
                            TodoModel::class.java
                        )!!
                        updateTodoFragment(res)
                    } else {
                        val res =
                            result.data?.getParcelableExtra<TodoModel>(getString(R.string.intent_todoModel))!!
                        updateTodoFragment(res)
                    }
                }
            }
    }

    private fun updateTodoFragment(todoData: TodoModel) {
        val todoFragment = viewPagerAdapter.getTodoFragment()
        todoFragment.setTodoLists(todoData)
        val position = todoFragment.getTodoDataPosition()
        todoFragment.adapter.notifyItemInserted(position)
    }
}