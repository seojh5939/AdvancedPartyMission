package bootcamp.sparta.advencedpartymission1.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.advencedpartymission1.bookmark.BookmarkModel
import bootcamp.sparta.advencedpartymission1.bookmark.BookmarkViewModel
import bootcamp.sparta.advencedpartymission1.databinding.ItemTodoRecyclerviewFragmentBinding
import bootcamp.sparta.advencedpartymission1.main.MainActivity
import java.util.UUID

class TodoListAdapter(
    fragmentActivity: FragmentActivity,
    private val onClickItem: (Int, TodoModel) -> Unit
) :
    ListAdapter<TodoModel, TodoListAdapter.TodoViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TodoModel>() {
            override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    val todoViewModel by lazy {
        ViewModelProvider(fragmentActivity)[TodoViewModel::class.java]
    }

    private val bookmarkViewModel by lazy {
        ViewModelProvider(fragmentActivity)[BookmarkViewModel::class.java]
    }

    inner class TodoViewHolder(
        private val binding: ItemTodoRecyclerviewFragmentBinding,
        private val onClickItem: (Int, TodoModel) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodoModel) = with(binding) {
            itemTodoTitle.text = item.title
            itemTodoContent.text = item.content
            itemTodoSwitch.isChecked = item.bookmark

            // container 클릭
            todoContainer.setOnClickListener {
                onClickItem(
                    bindingAdapterPosition,
                    item
                )
            }

            // switch
            itemTodoSwitch.setOnClickListener {
                val list = todoViewModel.liveData.value?.toMutableList()
                val position = bindingAdapterPosition

                if (itemTodoSwitch.isChecked) {
                    list?.let {
                        it[position] = item.copy(bookmark = true)
                        bookmarkViewModel.addBookmarkItem(TodoModel.toBookmarkModel(it[position]))
                        todoViewModel.modifyTodoItem(position, it[position])
                    }
                } else {
                    list?.let {
                        it[position] = item.copy(bookmark = false)
                        bookmarkViewModel.removeBookmarkItem(TodoModel.toBookmarkModel(list[position]))
                        todoViewModel.modifyTodoItem(position, it[position])
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoViewHolder {
        val list = currentList.toMutableList()
        return TodoViewHolder(
            ItemTodoRecyclerviewFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickItem
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}