package com.kamil.bak.webshop;

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kamil.bak.webshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val todoList = mutableListOf<Task>()
    private val completedList = mutableListOf<Task>()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskAdapter = TaskAdapter(this, todoList, completedList)

        binding.btnAddTask.setOnClickListener {
            val taskName = binding.etTask.text.toString()

            if (taskName.isNotEmpty()) {
                val newTask = Task(taskName)
                taskAdapter.addTask(newTask)
                taskAdapter.updateUI()
                binding.etTask.text.clear()
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateTaskStatus(task: Task) {
        if (task.isCompleted) {
            todoList.remove(task)
            completedList.add(task)
        } else {
            completedList.remove(task)
            todoList.add(task)
        }
        taskAdapter.updateUI()
    }
}