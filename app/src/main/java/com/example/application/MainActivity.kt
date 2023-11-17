package com.example.application

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var ageTextInputLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSave: Button = findViewById(R.id.btn_update)
        ageTextInputLayout = findViewById(R.id.age)

        ageTextInputLayout.setOnClickListener {
            showDatePicker()
        }

        btnSave.setOnClickListener(View.OnClickListener {
            // Display a toast message when the button is clicked
            showToast("Save button clicked!")
        })

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * The function to show the DatePicker Dialog and select the due date.
     */
    private fun showDatePicker() {

        /**
         * This Gets a calendar using the default time zone and locale.
         * The calender returned is based on the current time
         * in the default time zone with the default.
         */

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        /**
         * Creates a new date picker dialog for the specified date using the parent
         * context's default date picker dialog theme.
         */

        val dpd = DatePickerDialog(
            this, { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/$monthOfYear/$year"
                ageTextInputLayout.editText?.setText(calculateAge(selectedDate))
            },
            year,
            month,
            day
        )
        dpd.show()  // It is used to show the datePicker Dialog.
    }

    private fun calculateAge(selectedDate: String): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val birthDate = sdf.parse(selectedDate)
        val currentDate = Calendar.getInstance().time

        // Calculate age in years
        val ageInMillis = currentDate.time - birthDate.time
        val ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365)

        return ageInYears.toInt().toString()
    }

}