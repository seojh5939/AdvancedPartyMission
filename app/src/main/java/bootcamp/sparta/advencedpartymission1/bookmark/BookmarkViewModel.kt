package bootcamp.sparta.advencedpartymission1.bookmark

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bootcamp.sparta.advencedpartymission1.todo.TodoModel
import java.util.UUID

class BookmarkViewModel: ViewModel() {
    private val _liveData = MutableLiveData<List<BookmarkModel>>()
    val liveData get() = _liveData

    fun addBookmarkItem(item: BookmarkModel) {
        val newList = _liveData.value?.toMutableList() ?: mutableListOf()
        newList.add(item)
        liveData.value = newList
    }

    fun removeBookmarkItem(item: BookmarkModel) {
        val newList = _liveData.value?.toMutableList() ?: mutableListOf()
        val removeData = newList.find { it.id == item.id }
        removeData?.let {
            newList.remove(removeData)
            _liveData.value = newList
        }
    }

    fun modifyBookmarkItem(position: Int, item: BookmarkModel) {
//        val list = liveData.value?.toMutableList()
//        list?.let{
//            it.set(position, item)
//            _liveData.value = list!!
//        }
    }
}