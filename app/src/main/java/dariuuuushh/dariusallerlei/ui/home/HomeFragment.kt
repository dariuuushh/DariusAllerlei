package dariuuuushh.dariusallerlei.ui.home

import HomeViewModel
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import dariuuuushh.dariusallerlei.R
import dariuuuushh.dariusallerlei.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        val toDoTable: TableLayout = binding.toDoList

//        homeViewModel.date.observe(viewLifecycleOwner) { newDate ->
//            val combinedText = "ToDo-Liste - $newDate"
//            textView.text = combinedText
//        }

//        homeViewModel.todos.observe(viewLifecycleOwner) { todoItems ->
//            toDoTable.removeAllViews()
//            if (todoItems.isNotEmpty()) {
//                todoItems.forEach { todoItem ->
//                    val inflaterT = LayoutInflater.from(requireContext())
//                    val newRow = inflaterT.inflate(R.layout.todo_table_row, toDoTable, false) as TableRow
//                    val todoTextInputLayout = newRow.findViewById<TextInputLayout>(R.id.todo_input_layout)
//
//                    todoTextInputLayout.editText?.setText(todoItem.description)
//                    todoTextInputLayout.isEndIconVisible = todoItem.isChecked
//
//                    todoTextInputLayout.editText?.addTextChangedListener {
//                        todoItem.description = it.toString()
//                        homeViewModel.updateTodoItem(todoItem)
//                    }
//                    todoTextInputLayout.setStartIconOnClickListener {
//                        todoItem.isChecked = !todoItem.isChecked
//                        setupIconListeners(todoTextInputLayout)
//                        homeViewModel.updateTodoItem(todoItem)
//                    }
//
//                    toDoTable.addView(newRow)
//                }
//            }
//        }
//
        val addTableRow = binding.addTableRow
        val todoAddButton = binding.todoAddButton
        todoAddButton.setOnClickListener{
            addNewTodoItem()
            val index = toDoTable.indexOfChild(addTableRow)
            if (index < toDoTable.childCount - 1) {
                toDoTable.removeViewAt(index)
                toDoTable.addView(addTableRow, index + 1)
            }
        }
        return root
    }

    private fun addNewTodoItem() {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        val toDoTable: TableLayout = binding.toDoList

        val inflater = LayoutInflater.from(requireContext())
        val newRow = inflater.inflate(R.layout.todo_table_row, toDoTable, false) as TableRow

        val todoTextInputLayout = newRow.findViewById<TextInputLayout>(R.id.todo_input_layout)

        todoTextInputLayout.startIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_start_icon)

        val description = todoTextInputLayout.editText?.text.toString()
        if (description.isNotEmpty()) {
            homeViewModel.addTodoItem(description, isChecked = todoTextInputLayout.isEndIconVisible)
            todoTextInputLayout.editText?.text?.clear()
        }

        toDoTable.addView(newRow)

    }

    private fun setupIconListeners(todoTextInputLayout: TextInputLayout) {
        todoTextInputLayout.setStartIconOnClickListener {
            toggleToEndIcon(todoTextInputLayout)
        }
    }

    private fun toggleToStartIcon(todoTextInputLayout: TextInputLayout) {
        todoTextInputLayout.endIconMode = TextInputLayout.END_ICON_NONE
        todoTextInputLayout.startIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_start_icon)
        todoTextInputLayout.setStartIconOnClickListener {
            toggleToEndIcon(todoTextInputLayout)
        }
    }

    private fun toggleToEndIcon(todoTextInputLayout: TextInputLayout) {
        todoTextInputLayout.startIconDrawable = null
        todoTextInputLayout.endIconMode = TextInputLayout.END_ICON_CUSTOM
        todoTextInputLayout.endIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_end_icon)
        todoTextInputLayout.setEndIconOnClickListener {
            toggleToStartIcon(todoTextInputLayout)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}