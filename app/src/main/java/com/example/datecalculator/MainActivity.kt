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
    private var timeTextD : TextView? = null
    private var timeTextS : TextView? = null
    private var timeTextM : TextView? = null
    private var timeTextH : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dateView = findViewById(R.id.date)
        timeTextD = findViewById(R.id.timeTextD)
        timeTextH = findViewById(R.id.timeTextH)
        timeTextM = findViewById(R.id.timeTextM)
        timeTextS = findViewById(R.id.timeTextS)

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
                    val dTime = theDate.time / 86400000

                    val mTime = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis())) // 현재 날짜 구하기

                    currentDate.let {
                        val cDTime = currentDate.time/86400000
                        val cHTime = currentDate.time/3600000
                        val cMTime = currentDate.time/60000
                        val cSTime = currentDate.time/1000

                        val endDTime = cDTime - dTime

                        val endMTime = cMTime - mTime


                        timeTextD?.text = endDTime.toString()+" Day"

                        timeTextM?.text = endMTime.toString()+" Minute"

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