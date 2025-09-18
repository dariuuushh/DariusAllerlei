package dariuuuushh.dariusallerlei.data

import TodoDao
import TodoItemEntity
import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {

    val allTodos: LiveData<List<TodoItemEntity>> = todoDao.getAllTodos()

    suspend fun insert(todo: TodoItemEntity) {
        todoDao.insert(todo)
    }

    suspend fun update(todo: TodoItemEntity) {
        todoDao.update(todo)
    }

    suspend fun delete(todo: TodoItemEntity) {
        todoDao.delete(todo)
    }
}
