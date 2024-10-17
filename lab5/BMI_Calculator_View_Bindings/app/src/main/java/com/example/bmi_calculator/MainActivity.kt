package com.example.bmi_calculator
// Declares the package name for the application.

import androidx.appcompat.app.AppCompatActivity
// Imports the AppCompatActivity class for compatibility support with older Android versions.

import android.os.Bundle
// Imports the Bundle class used for saving and restoring state information.

import com.example.bmi_calculator.databinding.ActivityMainBinding
// Imports the binding class generated from your activity's XML layout for type-safe view binding.

class MainActivity : AppCompatActivity() {
// Defines the MainActivity class, inheriting from AppCompatActivity.

    lateinit var binding: ActivityMainBinding
    // Declares a late-initialized variable 'binding' for view binding.

    override fun onCreate(savedInstanceState: Bundle?) {
        // Overrides the onCreate method, which is the entry point of the activity.

        super.onCreate(savedInstanceState)
        // Calls the superclass implementation of onCreate.

        binding = ActivityMainBinding.inflate(layoutInflater)
        // Initializes 'binding' by inflating the layout with the layout inflater.

        setContentView(binding.root)
        // Sets the activity's content view to the root view of the inflated layout.

        binding.button.setOnClickListener {
            // Sets an OnClickListener on the button to handle click events.

            try {
                // Starts a try block to catch exceptions that may occur during input parsing.

                val weight = binding.editTextWeight.text.toString().toFloat()
                // Retrieves the text from 'editTextWeight', converts it to a String, then to a Float.

                val height = binding.editTextHeight.text.toString().toFloat()
                // Retrieves the text from 'editTextHeight', converts it to a String, then to a Float.

                if (weight > 0 && height > 0) {
                    // Checks if both weight and height are greater than zero.

                    val bmi = weight / (height * height)
                    // Calculates the BMI using the formula BMI = weight / (height^2).

                    binding.textViewResult.text = "Your BMI is $bmi"
                    // Updates 'textViewResult' with the calculated BMI.

                } else {
                    // Executes if weight or height is zero or negative.

                    binding.textViewResult.text = "Your weight and height can't be zero"
                    // Displays an error message indicating invalid input.

                }
            } catch(e: NumberFormatException) {
                // Catches a NumberFormatException if the input cannot be converted to a Float.

                binding.textViewResult.text = "Please fill in both text fields"
                // Displays an error message asking the user to fill in both text fields.

            }
        }
    }
}
// Closes the MainActivity class.
