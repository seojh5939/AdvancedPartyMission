package bootcamp.sparta.advencedpartymission1.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.bookmark.BookmarkFragment
import bootcamp.sparta.advencedpartymission1.todo.TodoFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    private val fragmentsList = ArrayList<MainTabs>()

    init {
        fragmentsList.add(
            MainTabs(
                TodoFragment.getInstance(),
                R.string.tab_todo_fragment
            )
        )
        fragmentsList.add(
            MainTabs(
                BookmarkFragment.getInstance(),
                R.string.tab_bookmark_fragment
            )
        )
    }

    fun getFragment(position: Int) : Fragment = fragmentsList[position].fragment
    fun getTodoFragment() : TodoFragment = fragmentsList.find { it.fragment is TodoFragment }?.fragment as TodoFragment
    fun getBookmarkFragment() : BookmarkFragment = fragmentsList.find { it.fragment is BookmarkFragment }?.fragment as BookmarkFragment

    fun getTitleRes(position: Int) : Int {
        return fragmentsList[position].tabTitleRes
    }

    override fun getItemCount(): Int {
        return fragmentsList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentsList[position].fragment
    }
}