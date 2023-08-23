package bootcamp.sparta.advencedpartymission1.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.data.TodoData
import bootcamp.sparta.advencedpartymission1.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {
    private var binding : FragmentTodoBinding? = null
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initRecyclerView() {
        recyclerview = binding!!.rvTodo
        adapter = RecyclerViewAdapter(TodoData.getTodoList())
        recyclerview.adapter = adapter
        recyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}