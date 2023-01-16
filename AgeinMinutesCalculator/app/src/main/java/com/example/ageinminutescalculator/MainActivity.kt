package com.example.ageinminutescalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener(){
            clickDatePicker()
        }
    }

     fun clickDatePicker() {
         val myCalendar = Calendar.getInstance()
         val year =  myCalendar.get(Calendar.YEAR)
         val month = myCalendar.get(Calendar.MONTH)
         val day = myCalendar.get(Calendar.DAY_OF_MONTH)

         val tvSelectedDate : TextView = findViewById(R.id.tvSelectedDate)
         val tvSelectedDateInMinutes : TextView = findViewById(R.id.tvSelectedDateInMinutes)

         val dpd = DatePickerDialog(this, R.style.MyDatePickerStyle, {_,selectedYear, selectedMonth, selectedDayOfMonth ->

         val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
             tvSelectedDate.text = selectedDate

             val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

             val theDate = sdf.parse(selectedDate)

             val selectedDateInMinutes = theDate.time/60000

             val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

             val currentDateInMinutes = currentDate.time/60000

             val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

             tvSelectedDateInMinutes.text = differenceInMinutes.toString()
         }, year, month, day)

         dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
         dpd.show()




    }


}