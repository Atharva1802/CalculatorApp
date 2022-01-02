package at.app.calculatorapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import at.app.calculatorapp.databinding.ActivityMainBinding

import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var lastNumeric : Boolean = false       // Adding digit iteration bool
    var lastDot : Boolean = false           // Adding dot iteration bool
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun onDigit(view: View)
    {
       binding.tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear (view: View)
    {
        binding.tvInput.setText("")
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View)
    {
        if (lastNumeric && !lastDot)
        {
            binding.tvInput.append(".")
            lastDot = true
            lastNumeric = false
        }
    }

    fun onEqual(view: View){
        if (lastNumeric) {
            var tvValue = binding.tvInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix ="-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains('-')) {
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]     // Split to two before and after the minus

                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }

                    binding.tvInput.text = (one.toDouble() - two.toDouble()).toString()
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }
    fun onOperator(view: View){
        if (lastNumeric &&!isOperatorAdded(binding.tvInput.text.toString())){
            binding.tvInput.append((view as Button).text)
            lastDot = false
            lastNumeric = false
        }
    }

    private fun isOperatorAdded(value: String) : Boolean{
        return  if (value.startsWith("-")){
            false
        }
        else{
            value.contains("/") || value.contains("*") || value.contains("+")
                    || value.contains("-")
        }
    }
}