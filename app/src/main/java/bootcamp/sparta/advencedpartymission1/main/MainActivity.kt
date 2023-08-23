package bootcamp.sparta.advencedpartymission1.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.abstract.BaseActivity
import bootcamp.sparta.advencedpartymission1.databinding.ActivityMainBinding
import bootcamp.sparta.advencedpartymission1.todo.WriteTodoActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val tabTitleArray = arrayOf("Todo", "Bookmark")
    private val fab: FloatingActionButton by lazy { binding.fabMain }
    private val viewPager: ViewPager2 by lazy { binding.vpMain }
    private val tabLayout: TabLayout by lazy { binding. tlMain }

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setToolBarTitle(findViewById(R.id.layout_toolbar_main), "Camp", false)
        initViewPager()
        setViewPagerChangeListener()
        attachTabLayout()
        setFabClickListener()
    }

    private fun initViewPager() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = viewPagerAdapter
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
            tab.setText(tabTitleArray[position])
        }.attach()
    }

    private fun setFabClickListener() {
        fab.setOnClickListener { intentChangeActivity(WriteTodoActivity::class.java) }
    }
}