package bootcamp.sparta.advencedpartymission1.todo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.advencedpartymission1.databinding.TodoRecyclerviewItemBinding
import bootcamp.sparta.advencedpartymission1.todo.add.TodoContentActivity
import bootcamp.sparta.advencedpartymission1.todo.add.TodoContentType


class TodoRecyclerViewAdapter() : RecyclerView.Adapter<TodoRecyclerViewAdapter.Holder>() {

    private val list = ArrayList<TodoModel>()

    fun modifyItem(origin: TodoModel, modify: TodoModel) {
        val position = list.indexOf(origin)
        if (position >= 0) {
            list[position] = modify
            notifyItemChanged(position)
        }
    }

    fun addItem(item: TodoModel) {
        list.add(item)
        notifyItemChanged(list.size - 1)
    }

    fun removeItem(item: TodoModel) {
        val position = list.indexOf(item)
        Log.d(
            "RVAdapter",
            "removeItem original: ${list[0]} return: ${item} isEquals: ${list[0].equals(item)}"
        )
        if (position >= 0) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    class Holder(val binding: TodoRecyclerviewItemBinding, val list: ArrayList<TodoModel>) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: TodoModel) = with(binding) {
            rvItemTitle.text = item.title
            rvItemContent.text = item.content
        }

        override fun onClick(p0: View?) {
            val intent = TodoContentActivity.newIntentForEdit(itemView.context).apply {
                putExtra(TodoFragment.EXTRA_MODIFY, list[layoutPosition])
            }
            itemView.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoRecyclerViewAdapter.Holder {
        return Holder(
            TodoRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context)),
            list
        )
    }

    override fun onBindViewHolder(holder: TodoRecyclerViewAdapter.Holder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}