package bootcamp.sparta.advencedpartymission1.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.advencedpartymission1.databinding.ItemRecyclerviewFragmentBinding

class BookmarkListAdapter(private val list: ArrayList<BookmarkModel>) : RecyclerView.Adapter<BookmarkListAdapter.Holder>(){
    private val mList = list

    class Holder(private val binding: ItemRecyclerviewFragmentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BookmarkModel)= with(binding) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkListAdapter.Holder {
        val view = ItemRecyclerviewFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: BookmarkListAdapter.Holder, position: Int) {
        val item = mList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}