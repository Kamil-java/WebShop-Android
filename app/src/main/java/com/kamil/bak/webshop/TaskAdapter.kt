package com.kamil.bak.webshop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView

class TaskAdapter(
    private val context: Context,
    private val todoList: MutableList<Task>,
    private val completedList: MutableList<Task>
) {

    fun addTask(task: Task) {
        if (task.isCompleted) {
            completedList.add(task)
        } else {
            todoList.add(task)
        }
    }

    fun updateUI() {
        val todoContainer: LinearLayout =
            (context as MainActivity).findViewById(R.id.llTodoContainer)
        val completedContainer: LinearLayout = context.findViewById(R.id.llCompletedContainer)

        todoContainer.removeAllViews()
        completedContainer.removeAllViews()

        todoList.forEach { task ->
            val taskView = createTaskView(task)
            todoContainer.addView(taskView)
        }

        completedList.forEach { task ->
            val taskView = createTaskView(task)
            completedContainer.addView(taskView)
        }
    }

    private fun createTaskView(task: Task): View {
        val taskView = LayoutInflater.from(context).inflate(R.layout.task_item, null)

        val taskTextView: TextView = taskView.findViewById(R.id.tvTaskName)
        val checkBox: CheckBox = taskView.findViewById(R.id.cbTaskComplete)

        taskTextView.text = task.name
        checkBox.isChecked = task.isCompleted

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
            (context as MainActivity).updateTaskStatus(task)
        }

        return taskView
    }
}