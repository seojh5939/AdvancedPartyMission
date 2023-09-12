package bootcamp.sparta.advencedpartymission1.todo

import android.app.Activity.RESULT_OK
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bootcamp.sparta.advencedpartymission1.databinding.TodoFragmentBinding
import bootcamp.sparta.advencedpartymission1.todo.content.TodoContentActivity
import bootcamp.sparta.advencedpartymission1.todo.content.TodoContentType

class TodoFragment : Fragment() {
    companion object {
        fun getInstance() = TodoFragment()
    }

    private var _binding: TodoFragmentBinding? = null
    private val binding: TodoFragmentBinding get() = _binding!!

    private val editTodoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val entryType =
                    result.data?.getStringExtra(TodoContentActivity.EXTRA_TODO_ENTRY_TYPE)
                val position =
                    result.data?.getIntExtra(TodoContentActivity.EXTRA_TODO_POSITION, -1) ?: -1
                val item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getParcelableExtra(
                        TodoContentActivity.EXTRA_TODO_MODEL,
                        TodoModel::class.java
                    )
                } else {
                    result.data?.getParcelableExtra(TodoContentActivity.EXTRA_TODO_MODEL)
                }

                when(TodoContentType.from(entryType)) {
                    TodoContentType.EDIT -> modifyTodoItem(position, item)
                    TodoContentType.REMOVE -> removeTodoItem(position)
                    else -> Unit
                }
            }
        }


    private val recyclerAdapter: TodoListAdapter by lazy {
        TodoListAdapter(requireActivity()) { position, item ->
            editTodoLauncher.launch(
                TodoContentActivity.newIntentForEdit(
                    requireContext(),
                    position,
                    item
                )
            )
        }
    }

    private val todoViewModel by lazy {
        ViewModelProvider(requireActivity())[TodoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TodoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        todoViewModel.liveData.observe(requireActivity()) {
                list -> recyclerAdapter.submitList(list)
            todoRecyclerView.adapter = recyclerAdapter
        }
    }

    private fun modifyTodoItem(position: Int, item: TodoModel?) {
        recyclerAdapter.todoViewModel.modifyTodoItem(position, item)
    }

    private fun removeTodoItem(position: Int) {
        recyclerAdapter.todoViewModel.removeTodoItem(position)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}