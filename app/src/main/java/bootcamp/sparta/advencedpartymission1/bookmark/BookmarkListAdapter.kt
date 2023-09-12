package bootcamp.sparta.advencedpartymission1.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.advencedpartymission1.databinding.ItemBookmarkRecyclerviewFragmentBinding
import bootcamp.sparta.advencedpartymission1.todo.TodoModel
import bootcamp.sparta.advencedpartymission1.todo.TodoViewModel

class BookmarkListAdapter(fragmentActivity: FragmentActivity) :
    ListAdapter<BookmarkModel, BookmarkListAdapter.BookmarkViewHolder>(diffUtil) {

    private val todoViewModel by lazy {
        ViewModelProvider(fragmentActivity)[TodoViewModel::class.java]
    }

    private val bookmarkViewModel by lazy {
        ViewModelProvider(fragmentActivity)[BookmarkViewModel::class.java]
    }

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

    inner class BookmarkViewHolder(private val binding: ItemBookmarkRecyclerviewFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookmarkModel) = with(binding) {
            itemBookmarkTitle.text = item.title
            itemBookmarkContent.text = item.content
            itemBookmarkSwitch.isChecked = item.bookmark

            itemBookmarkSwitch.setOnClickListener {
                if (!itemBookmarkSwitch.isChecked) {
                   todoViewModel.liveData.value?.let {
                       val todoItem = it.find { it2 -> it2.id == item.id }

                       if(todoItem != null) {
                           val position = it.indexOf(todoItem)
                           todoViewModel.modifyTodoItem(
                               position,
                               todoItem.copy(bookmark = false)
                           )
                       }
                       bookmarkViewModel.removeBookmarkItem(item)
                   }
                }
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

//    fun addItem(item: BookmarkModel) {
//        val list = currentList.toMutableList()
//        list.add(item)
//        submitList(list)
//    }
//
//    fun deleteItem(item: BookmarkModel) {
//        val list = currentList.toMutableList()
//        list.find { it.id == item.id }?.let {
//            val position = list.indexOf(it)
//            list.removeAt(position)
//            submitList(list)
//        }
//    }
}