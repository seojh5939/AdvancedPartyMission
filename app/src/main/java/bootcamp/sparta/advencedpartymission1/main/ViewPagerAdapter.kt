package bootcamp.sparta.advencedpartymission1.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import bootcamp.sparta.advencedpartymission1.bookmark.BookmarkFragment
import bootcamp.sparta.advencedpartymission1.todo.TodoFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle){
    private val fragmentList = listOf<Fragment>(TodoFragment(), BookmarkFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}