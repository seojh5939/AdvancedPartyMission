package bootcamp.sparta.advencedpartymission1.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.databinding.FragmentBookmarkBinding

class BookmarkFragment : Fragment() {
    private var _binding : FragmentBookmarkBinding? = null
    val binding get() : FragmentBookmarkBinding = _binding!!

    companion object {
        fun getInstance() = BookmarkFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }
}