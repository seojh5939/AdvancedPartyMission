package bootcamp.sparta.advencedpartymission1.data

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

private var idNumber : Long = -1

data class Todo(
    val id: Long = increment(),
    val title: String,
    val image: Drawable?,
    val contents: String
)

private fun increment() : Long = ++idNumber