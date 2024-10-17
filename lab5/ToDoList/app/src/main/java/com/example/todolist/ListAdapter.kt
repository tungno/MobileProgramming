package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TodoItemBinding

class TodoListAdapter(private val list: List<String>): RecyclerView.Adapter<TodoListAdapter.ToDoViewHolder>() {
    //Class for managing a single list item
    class ToDoViewHolder(private var binding: TodoItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(text: String){
            //fill UI elements with data, add click listeners
            binding.textView.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListAdapter.ToDoViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoListAdapter.ToDoViewHolder, position: Int) {
        //Pass data at list position to the ViewHolder
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}