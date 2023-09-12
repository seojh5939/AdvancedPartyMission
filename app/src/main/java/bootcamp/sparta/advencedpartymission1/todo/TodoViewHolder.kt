package bootcamp.sparta.advencedpartymission1.todo

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.advencedpartymission1.bookmark.BookmarkModel
import bootcamp.sparta.advencedpartymission1.databinding.ItemTodoRecyclerviewFragmentBinding
import bootcamp.sparta.advencedpartymission1.main.MainActivity

class TodoViewHolder(
    private val binding: ItemTodoRecyclerviewFragmentBinding,
    private val onClickItem: (Int, TodoModel) -> Unit,
    private val list: MutableList<TodoModel>,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: TodoModel) = with(binding) {
        itemTodoTitle.text = item.title
        itemTodoContent.text = item.content
        itemTodoSwitch.isChecked = item.bookmark

        todoContainer.setOnClickListener {
            onClickItem(
                bindingAdapterPosition,
                item
            )
        }

        // switch
        itemTodoSwitch.setOnClickListener {
            if (itemTodoSwitch.isChecked) {
                list[bindingAdapterPosition] = item.copy(bookmark = true)
                MainActivity.instance.getBookmarkFragment().addBookmarkItem(
                    BookmarkModel(
                        id = list[bindingAdapterPosition].id,
                        title = list[bindingAdapterPosition].title,
                        content = list[bindingAdapterPosition].content,
                        bookmark = list[bindingAdapterPosition].bookmark
                    )
                )
            } else {
                list[bindingAdapterPosition] = item.copy(bookmark = false)
                MainActivity.instance.getBookmarkFragment().removeBookmarkItem(
                    BookmarkModel(
                        id = list[bindingAdapterPosition].id,
                        title = list[bindingAdapterPosition].title,
                        content = list[bindingAdapterPosition].content,
                        bookmark = list[bindingAdapterPosition].bookmark,
                    )
                )
            }
        }
    }
}