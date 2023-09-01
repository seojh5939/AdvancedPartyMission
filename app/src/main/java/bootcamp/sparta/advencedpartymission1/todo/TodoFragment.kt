package bootcamp.sparta.advencedpartymission1.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bootcamp.sparta.advencedpartymission1.databinding.FragmentTodoBinding
import bootcamp.sparta.advencedpartymission1.todo.add.TodoContentActivity

class TodoFragment : Fragment() {
    private var _binding : FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private val listAdapter : TodoRecyclerViewAdapter by lazy {
        TodoRecyclerViewAdapter()
    }

    companion object {
        const val EXTRA_MODIFY = "extra_modify"

        fun newInstance() = TodoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        todoRecyclerView.adapter = listAdapter
    }

    fun modifyTodoItem(origin: TodoModel, modify: TodoModel) {
        listAdapter.modifyItem(origin, modify)
    }

    fun setTodoContent(item: TodoModel) {
        listAdapter.addItem(item)
    }

    fun removeTodoContent(item: TodoModel) {
        listAdapter.removeItem(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}