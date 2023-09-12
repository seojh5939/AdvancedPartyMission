package bootcamp.sparta.advencedpartymission1.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import bootcamp.sparta.advencedpartymission1.databinding.ItemBookmarkRecyclerviewFragmentBinding

class BookmarkListAdapter() :
    ListAdapter<BookmarkModel, BookmarkViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<BookmarkModel>() {
            override fun areItemsTheSame(oldItem: BookmarkModel, newItem: BookmarkModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: BookmarkModel,
                newItem: BookmarkModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(
            ItemBookmarkRecyclerviewFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    fun addItem(item: BookmarkModel) {
        val list = currentList.toMutableList()
        list.add(item)
        submitList(list)
    }

    fun deleteItem(item: BookmarkModel) {
        val list = currentList.toMutableList()
        list.find { it.id == item.id }?.let {
            val position = list.indexOf(it)
            list.removeAt(position)
            submitList(list)
        }
    }
}