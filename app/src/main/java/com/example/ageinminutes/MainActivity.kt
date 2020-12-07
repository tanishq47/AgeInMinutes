package com.example.ageinminutes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }

    }
    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "Chosen Year is $year, the month is $month and the day is $selectedDayOfMonth"
                , Toast.LENGTH_LONG).show()
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.setText(selectedDate)
//sdf-> simple date format it's basically a class with predeifned patterns
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            //conversion of selectedDate from string to date type:-
            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate!!.time/60000//converting milliseconds to minutes
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMinutes = currentDate!!.time/60000
            val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
            tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

            }
            ,year
            ,month
            ,day)
        dpd.datePicker.setMaxDate(Date().time-86400000)
        dpd.show()
    }

}
