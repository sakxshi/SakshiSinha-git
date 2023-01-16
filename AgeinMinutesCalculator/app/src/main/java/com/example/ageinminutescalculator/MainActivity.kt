package com.example.ageinminutescalculator     //the id by which playstore will recognise your app

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    //every app starts with a sequence of method calls as follows:-

    override fun onCreate(savedInstanceState: Bundle?) {    //called when the activity first starts up
                                                            //Bundle class is used to store the data of activity
        super.onCreate(savedInstanceState)    //regenerates activity when the activity is destroyed and then restarted
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener(){
            clickDatePicker()
        }
    }

     fun clickDatePicker() {
         val myCalendar = Calendar.getInstance()   //myCalendar object created
         val year =  myCalendar.get(Calendar.YEAR)   //current year
         val month = myCalendar.get(Calendar.MONTH)  //current month
         val day = myCalendar.get(Calendar.DAY_OF_MONTH)   //current day

         //all these will be passed in the DatePickerDialog so that the first date displayed is the current date

         val tvSelectedDate : TextView = findViewById(R.id.tvSelectedDate)
         val tvSelectedDateInMinutes : TextView = findViewById(R.id.tvSelectedDateInMinutes)

         val dpd = DatePickerDialog(this, R.style.MyDatePickerStyle, {_,selectedYear, selectedMonth, selectedDayOfMonth ->


             val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"   //+1 because months start from 0
             tvSelectedDate.text = selectedDate   //the selected date will be displayed on the textview

             val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

             val theDate = sdf.parse(selectedDate)

             theDate?.let{    //to avoid null pointer exception

             val selectedDateInMinutes = theDate.time/60_000    //dividing by 60_000 because we're getting time in milli seconds

             val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

             currentDate?.let{

             val currentDateInMinutes = currentDate.time /60_000

             val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

             tvSelectedDateInMinutes.text = differenceInMinutes.toString()}}


         }, year, month, day)   //the date displayed in the DataPickerDialog will be the current date

         dpd.datePicker.maxDate = System.currentTimeMillis() - 86_400_000    //so that the dates ahead of current date can't be selected
         dpd.show()

     }


}