package bootcamp.sparta.advencedpartymission1.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.data.Todo
import bootcamp.sparta.advencedpartymission1.databinding.FragmentItemRecyclerviewBinding

class RecyclerViewAdapter(list: List<Todo>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    private val mList : List<Todo>

    init {
        mList = list
    }

    class ViewHolder(private val binding: FragmentItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.ivItemImage
        val title = binding.tvItemTitle
        val content = binding.tvItemContent
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = FragmentItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = mList[position].title
        mList[position].image?.let { holder.image.setImageDrawable(it) }
        holder.content.text = mList[position].contents
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}