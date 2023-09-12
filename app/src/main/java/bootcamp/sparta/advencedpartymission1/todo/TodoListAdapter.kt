package bootcamp.sparta.advencedpartymission1.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import bootcamp.sparta.advencedpartymission1.databinding.ItemTodoRecyclerviewFragmentBinding
import java.util.UUID

class TodoListAdapter(
    private val onClickItem: (Int, TodoModel) -> Unit
) :
    ListAdapter<TodoModel, TodoViewHolder>(diffUtil){

    init {
        val newList = currentList.toMutableList()
        for (i in 0 until 10) {
            newList.add(
                TodoModel(
                    UUID.randomUUID(),
                    "title $i",
                    "description $i"
                )
            )
        }
        submitList(newList)
    }

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
            ), onClickItem, list
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    fun addItem(item: TodoModel) {
        val newList = currentList.toMutableList()
        newList.add(item)
        submitList(newList)
    }

    fun modifyItem(position: Int, item: TodoModel) {
        val newList = currentList.toMutableList()
        newList[position] = item
        submitList(newList)
    }
}