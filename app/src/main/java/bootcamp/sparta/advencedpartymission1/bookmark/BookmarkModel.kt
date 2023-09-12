package bootcamp.sparta.advencedpartymission1.bookmark

import android.os.Parcelable
import bootcamp.sparta.advencedpartymission1.todo.TodoModel
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class BookmarkModel(
    val id: UUID,
    val title: String,
    val content: String,
    val bookmark: Boolean
): Parcelable {
    fun toTodoModel(item: BookmarkModel): TodoModel {
        return TodoModel(
            item.id,
            item.title,
            item.content,
            item.bookmark
        )
    }
}