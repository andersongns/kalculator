package com.example.kalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var txtCalcText: TextView? = null
    private var txtHistoryCalcText: TextView? = null

    var lastNumeric: Boolean = false
    var lastDot: Boolean = false
    private var calculator: Calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtCalcText = findViewById(R.id.txtCalcText)
        txtHistoryCalcText = findViewById(R.id.txtHistoryCalcText)
    }

    fun onDigit(view: View) {
        txtCalcText?.append((view as Button).text)
    }

    fun onClear(view: View) {
        calculator.clr()
        txtCalcText?.text = ""
        txtHistoryCalcText?.text = ""
    }

    fun onDecimalPoint(view: View) {
        val value = txtCalcText?.text.toString()
        if (!value.contains(".")) {
            txtCalcText?.text = value.plus(".")
        } else {
            txtCalcText?.text = value
        }
    }

    fun onOperator(view: View) {
        val value = txtCalcText?.text.toString()
        val operator = (view as Button).text.toString()

        if (value.isEmpty() && calculator.isLastNumberOperator()) {
            calculator.changeLastOperator(operator)
        } else {
            calculator.setNumber(value)
            calculator.setOperator(operator)
            txtCalcText?.text = ""
            txtHistoryCalcText?.text = calculator.getDigitHistory()
        }
    }

    fun onEqual(view: View) {
        try {
            calculator.setNumber(txtCalcText?.text.toString())
            txtCalcText?.text = calculator.result()
            txtHistoryCalcText?.text = calculator.getDigitHistory()
            calculator.clr()
        } catch (e: ArithmeticException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
