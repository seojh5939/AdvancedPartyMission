package bootcamp.sparta.advencedpartymission1.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.advencedpartymission1.databinding.ItemRecyclerviewFragmentBinding

class RecyclerViewAdapter(private val list : ArrayList<TodoModel>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val mList: ArrayList<TodoModel> get() = list

    class ViewHolder(private val binding: ItemRecyclerviewFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodoModel) = with(binding) {
            itemTitle.text = item.title
            itemContent.text = item.content
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemRecyclerviewFragmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}