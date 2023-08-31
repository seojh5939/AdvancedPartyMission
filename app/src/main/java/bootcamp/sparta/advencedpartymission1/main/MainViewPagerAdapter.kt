package bootcamp.sparta.advencedpartymission1.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.bookmark.BookmarkFragment
import bootcamp.sparta.advencedpartymission1.todo.TodoFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val fragments = ArrayList<MainTabs>()

    init {
        fragments.add(
            MainTabs(
                TodoFragment.newInstance(),
                R.string.tab_Todo
            )
        )
        fragments.add(
            MainTabs(
                BookmarkFragment.newInstance(),
                R.string.tab_Bookmark
            )
        )
    }

    fun getTitle(position: Int) : Int {
        return fragments[position].titleRes
    }

    fun isTodoFragment(position: Int): Boolean {
        return fragments[position].fragment is TodoFragment
    }

    fun getTodoFragment() : TodoFragment? = fragments.find { it.fragment is TodoFragment }?.fragment as? TodoFragment

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }
}