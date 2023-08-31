package bootcamp.sparta.advencedpartymission1.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.advencedpartymission1.databinding.TodoRecyclerviewItemBinding

class TodoRecyclerViewAdapter : RecyclerView.Adapter<TodoRecyclerViewAdapter.Holder>(){

    private val list = ArrayList<TodoModel>()

    fun addItem(item: TodoModel) {
        list.add(item)
        notifyItemChanged(list.size - 1)
    }

    class Holder(val binding: TodoRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodoModel) = with(binding) {
            rvItemTitle.text = item.title
            rvItemContent.text = item.content
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoRecyclerViewAdapter.Holder {
        return Holder(
            TodoRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context))
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