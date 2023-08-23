package bootcamp.sparta.advencedpartymission1.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.advencedpartymission1.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {
    private var _binding : FragmentTodoBinding? = null
    private val binding : FragmentTodoBinding get() = _binding!!

    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter

    companion object {
        fun getInstance() = TodoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initRecyclerView() {
        recyclerview = binding.rvTodo
        adapter = RecyclerViewAdapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}