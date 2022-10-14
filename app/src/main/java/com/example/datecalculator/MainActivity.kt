package com.example.datecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var dateView : TextView? = null
    private var timeText : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dateView = findViewById(R.id.date)
        timeText = findViewById(R.id.timeText)

        val dateBtn = findViewById<Button>(R.id.dateBtn)
        dateBtn.setOnClickListener { view ->
            view
            datePicker()

        }

    }
    fun datePicker(){

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth->

                dateView?.text = "$year/${month + 1}/$dayOfMonth"

                val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA)
                val theDate = sdf.parse("$year/${month + 1}/$dayOfMonth")

                theDate.let { // let 으로 오류 방지
                    val sMTime = theDate.time / 60000 // 선택한 날짜 구하기
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis())) // 현재 날짜 구하기

                    currentDate.let {
                        val cMTime = currentDate.time/60000
                        val endTime = cMTime - sMTime
                        timeText?.text = endTime.toString()
                    }
                }

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000 //과거 시간만 설정할 수 있게 변경
        dpd.show()
    }
}