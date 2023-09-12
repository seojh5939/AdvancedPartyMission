package bootcamp.sparta.advencedpartymission1.todo

import android.app.Activity.RESULT_OK
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import bootcamp.sparta.advencedpartymission1.databinding.TodoFragmentBinding
import bootcamp.sparta.advencedpartymission1.todo.content.TodoContentActivity

class TodoFragment : Fragment() {
    private var _binding: TodoFragmentBinding? = null
    private val binding: TodoFragmentBinding get() = _binding!!
    private val editTodoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
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

                item?.let {
                    modifyTodoItem(position, item)
                }
            }
        }

    private val recyclerAdapter: TodoListAdapter by lazy {
        TodoListAdapter { position, item ->
            editTodoLauncher.launch(
                TodoContentActivity.newIntentForEdit(
                    requireContext(),
                    position,
                    item
                )
            )
        }
    }

    companion object {
        fun getInstance() = TodoFragment()
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
        todoRecyclerView.adapter = recyclerAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setTodoItem(item: TodoModel) {
        recyclerAdapter.addItem(item)
    }

    fun modifyTodoItem(position: Int, item: TodoModel) {
        recyclerAdapter.modifyItem(position, item)
    }
}