package bootcamp.sparta.advencedpartymission1.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.databinding.BookmarkFragmentBinding

class BookmarkFragment : Fragment() {
    private var _binding : BookmarkFragmentBinding? = null
    private val binding get() : BookmarkFragmentBinding = _binding!!
    private val bookmarkListAdapter by lazy {
        BookmarkListAdapter()
    }

    companion object {
        fun getInstance() = BookmarkFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookmarkFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView()=with(binding) {
        bookmarkRecyclerView.adapter = bookmarkListAdapter
    }

    fun addBookmarkItem(item: BookmarkModel) {
        bookmarkListAdapter.addItem(item)
    }

    fun removeBookmarkItem(item: BookmarkModel) {
        bookmarkListAdapter.deleteItem(item)
    }
}