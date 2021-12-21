package at.app.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var lastNumeric : Boolean = false       // Adding digit iteration bool
    var lastDot : Boolean = false           // Adding dot iteration bool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onDigit(view: View)
    {
       tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear (view: View)
    {
        tvInput.setText("")
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View)
    {
        if (lastNumeric && !lastDot)
        {
            tvInput.append(".")
            lastDot = true
            lastNumeric = false
        }
    }



    }
}