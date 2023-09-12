package bootcamp.sparta.advencedpartymission1.bookmark

import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.advencedpartymission1.databinding.ItemBookmarkRecyclerviewFragmentBinding
import bootcamp.sparta.advencedpartymission1.main.MainActivity
import bootcamp.sparta.advencedpartymission1.todo.TodoModel
import java.util.UUID

class BookmarkViewHolder(private val binding: ItemBookmarkRecyclerviewFragmentBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: BookmarkModel) = with(binding) {
        itemBookmarkTitle.text = item.title
        itemBookmarkContent.text = item.content
        itemBookmarkSwitch.isChecked = item.bookmark

        itemBookmarkSwitch.setOnClickListener {
            if (!itemBookmarkSwitch.isChecked) {
                MainActivity.instance.getTodoFragment().modifyTodoItem(
                    bindingAdapterPosition,
                    TodoModel(
                        title = item.title,
                        content = item.content,
                        bookmark = false,
                    )
                )

                MainActivity.instance.getBookmarkFragment()
                    .removeBookmarkItem(item)
            }
        }
    }
}