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

class TodoFragment : Fragment() {
    lateinit var recyclerview: RecyclerView
    lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo, container, false)
        initRecyclerView(view)
        return view
    }

    override fun onResume() {
        super.onResume()
        // 데이터 새로고침
        adapter.notifyDataSetChanged()
    }

    private fun initRecyclerView(view: View) {
        recyclerview = view.findViewById(R.id.rv_todo)
        adapter = RecyclerViewAdapter(TodoData.getTodoList())
        recyclerview.adapter = adapter
        recyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}