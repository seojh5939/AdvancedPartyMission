package bootcamp.sparta.advencedpartymission1.bookmark

import java.util.UUID

data class BookmarkModel(
    val id: UUID,
    val title: String,
    val content: String,
    val bookmark: Boolean = false,
)
