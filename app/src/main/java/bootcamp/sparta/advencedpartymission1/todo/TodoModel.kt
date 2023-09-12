package bootcamp.sparta.advencedpartymission1.todo

import android.os.Parcelable
import bootcamp.sparta.advencedpartymission1.bookmark.BookmarkModel
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class TodoModel(
   val id: UUID = UUID.randomUUID(),
   val title: String,
   val content: String,
   val bookmark: Boolean,
) : Parcelable {
   companion object {
      fun toBookmarkModel(todoModel: TodoModel): BookmarkModel {
         return BookmarkModel(
            todoModel.id,
            todoModel.title,
            todoModel.content,
            todoModel.bookmark
         )
      }
   }
}