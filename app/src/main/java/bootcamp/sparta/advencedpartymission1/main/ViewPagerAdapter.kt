package bootcamp.sparta.advencedpartymission1.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import bootcamp.sparta.advencedpartymission1.bookmark.BookmarkFragment
import bootcamp.sparta.advencedpartymission1.todo.TodoFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle){
    private val fragmentsList = ArrayList<MainTabs>()

    fun getTodoFragment() : TodoFragment = fragmentsList[fragments.Todo.num].fragment as TodoFragment
    fun getBookmarkFragment() : BookmarkFragment = fragmentsList[fragments.Bookmark.num].fragment as BookmarkFragment

    fun addTabs(tabs: MainTabs) {
        fragmentsList.add(tabs)
    }

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

enum class fragments(val num: Int) {
    Todo(0),
    Bookmark(1),
}