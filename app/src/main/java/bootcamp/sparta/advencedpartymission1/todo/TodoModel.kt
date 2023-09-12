package bootcamp.sparta.advencedpartymission1.todo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class TodoModel(
   val id: UUID = UUID.randomUUID(),
   val title: String,
   val content: String,
   val bookmark: Boolean = false,
) : Parcelable