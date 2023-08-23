package bootcamp.sparta.advencedpartymission1.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.advencedpartymission1.databinding.FragmentItemRecyclerviewBinding

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val _mTodoLists: MutableList<TodoModel> = mutableListOf()

    val todoList: MutableList<TodoModel> get() = _mTodoLists

    class ViewHolder(private val binding: FragmentItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image = binding.ivItemImage
        val title = binding.tvItemTitle
        val content = binding.tvItemContent
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = FragmentItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val holder = ViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = _mTodoLists[position].title
        _mTodoLists[position].image?.let { holder.image.setImageDrawable(it) }
        holder.content.text = _mTodoLists[position].contents
    }

    override fun getItemCount(): Int {
        return _mTodoLists.size
    }
}