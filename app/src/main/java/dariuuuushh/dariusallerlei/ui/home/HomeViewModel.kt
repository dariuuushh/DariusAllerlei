import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dariuuuushh.dariusallerlei.data.TodoDatabase
import dariuuuushh.dariusallerlei.data.TodoRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository
    private val todos: LiveData<List<TodoItemEntity>>

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        todos = repository.allTodos
    }

    fun addTodoItem(description: String, isChecked: Boolean) {
        viewModelScope.launch {
            val todoItem = TodoItemEntity(description = description, isChecked = isChecked)
            repository.insert(todoItem)
        }
    }

    fun updateTodoItem(todoItem: TodoItemEntity) {
        viewModelScope.launch {
            repository.update(todoItem)
        }
    }
}
