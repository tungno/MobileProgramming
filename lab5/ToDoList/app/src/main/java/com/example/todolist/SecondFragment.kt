package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.FragmentSecondBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    val todoList = listOf(
        "Stop using ChatGPT to generate ToDo Lists",
        "Finish the report for the meeting",
        "Buy groceries for the week",
        "Call the plumber to fix the leaky faucet",
        "Go for a 30-minute jog in the evening",
        "Read two chapters of the new book",
        "Prepare a list of questions for the interview",
        "Attend the project status meeting at 2 PM",
        "Pay the monthly utility bills",
        "Clean and organize the garage",
        "Schedule a dentist appointment",
        "Send birthday wishes to Sarah",
        "Research vacation destinations for the summer",
        "Another very long ToDo, to see what happens on line breaks. Maybe it would be nice to have several line breaks",
        "Attend the online coding course at 6 PM",
        "Plan a surprise dinner for anniversary",
        "Start learning a new language",
        "Organize a charity event for the community",
        "Review and update the budget spreadsheet",
        "Call mom and dad to catch up",
        "Practice playing the guitar for 30 minutes",
        "Fix the broken kitchen cabinet door"
    )

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        //Instantiate adapter and add it to recycler view
        val adapter = TodoListAdapter(todoList)
        binding.recyclerView.adapter = adapter
        //Use this if you haven't set up an a layout manager in xml
        /*val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = linearLayoutManager*/

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}