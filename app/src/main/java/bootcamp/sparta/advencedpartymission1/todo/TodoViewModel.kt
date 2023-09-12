package bootcamp.sparta.advencedpartymission1.todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.UUID

class TodoViewModel : ViewModel() {
    private val _liveData: MutableLiveData<List<TodoModel>> by lazy {
        MutableLiveData<List<TodoModel>>()
    }

    val liveData get() = _liveData

    init {
        val list = mutableListOf<TodoModel>()
        for (i in 0 until 10) {
            list.let {
                it.add(
                    TodoModel(
                        UUID.randomUUID(),
                        "title $i",
                        "description $i",
                        false
                    )
                )
                _liveData.value = it
            }
        }
    }

    fun addTodoItem(item: TodoModel) {
        val newList = _liveData.value?.toMutableList() ?: mutableListOf()
        newList.add(item)
        _liveData.value = newList
    }

    fun removeTodoItem(position: Int) {
        val newList = _liveData.value?.toMutableList() ?: mutableListOf()
        if(newList.size > 0 && position != -1) {
            newList.removeAt(position)
            _liveData.value = newList
        }
    }

    fun modifyTodoItem(position: Int, item: TodoModel?) {
        item?.let {
            val newList = _liveData.value?.toMutableList() ?: mutableListOf()
            newList[position] = it
            _liveData.value = newList
        }
    }
}