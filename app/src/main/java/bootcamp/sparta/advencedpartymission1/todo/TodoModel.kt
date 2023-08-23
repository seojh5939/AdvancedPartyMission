package bootcamp.sparta.advencedpartymission1.todo

import android.graphics.drawable.Drawable

private var idNumber : Long = -1

private fun increment() : Long = ++idNumber
data class TodoModel(
    val id: Long = increment(),
    val title: String,
    val image: Drawable?,
    val contents: String
)