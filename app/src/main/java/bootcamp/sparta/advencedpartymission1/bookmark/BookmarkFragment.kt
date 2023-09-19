package bootcamp.sparta.advencedpartymission1.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.databinding.BookmarkFragmentBinding
import bootcamp.sparta.advencedpartymission1.todo.TodoViewModel

class BookmarkFragment : Fragment() {
    private var _binding : BookmarkFragmentBinding? = null
    private val binding get() : BookmarkFragmentBinding = _binding!!

    private val bookmarkListAdapter by lazy {
        BookmarkListAdapter(requireActivity())
    }

    private val bookmarkViewModel: BookmarkViewModel by lazy {
        ViewModelProvider(requireActivity())[BookmarkViewModel::class.java]
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
        bookmarkViewModel.liveData.observe(viewLifecycleOwner) { list ->
            bookmarkListAdapter.submitList(list)
        }
    }
}