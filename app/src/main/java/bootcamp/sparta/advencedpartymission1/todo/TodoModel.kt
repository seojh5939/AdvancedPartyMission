package bootcamp.sparta.advencedpartymission1.todo

import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

private var idNumber : Long = -1

private fun increment() : Long = ++idNumber
@Parcelize
data class TodoModel(
   val id: Long = increment(),
   val title: String,
   val image: Int,
   val contents: String
) : Parcelable