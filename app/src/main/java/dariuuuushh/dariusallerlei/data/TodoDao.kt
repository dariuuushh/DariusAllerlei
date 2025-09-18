package dariuuuushh.dariusallerlei.data
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_items ORDER BY date DESC")
    fun getAllTodos(): LiveData<List<TodoItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: TodoItemEntity)

    @Update
    suspend fun update(todo: TodoItemEntity)

    @Delete
    suspend fun delete(todo: TodoItemEntity)
}
