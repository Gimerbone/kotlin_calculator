package com.example.calculator

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.Utils

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var utils = Utils()

    private var buffer1: Double = 0.0
    private var inputDigitCount: Int = 0
    private var buffer2: Double = 0.0
    private var input1: Double = 0.0
    private var isCommaActive: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onSelect(view: View) {
        if (inputDigitCount <= 12) {
            buffer1 = buffer1 * 10
            inputDigitCount++

            when (view.id) {
                R.id.button19 -> buffer1 = buffer1 + 0.0
                R.id.button16 -> buffer1 = buffer1 + 1.0
                R.id.button15 -> buffer1 = buffer1 + 2.0
                R.id.button14 -> buffer1 = buffer1 + 3.0
                R.id.button12 -> buffer1 = buffer1 + 4.0
                R.id.button11 -> buffer1 = buffer1 + 5.0
                R.id.button10 -> buffer1 = buffer1 + 6.0
                R.id.button8 -> buffer1 = buffer1 + 7.0
                R.id.button7 -> buffer1 = buffer1 + 8.0
                R.id.button6 -> buffer1 = buffer1 + 9.0
            }
        }
    }

    fun onComma(view: View) {
        isCommaActive = true
        buffer2 = buffer1
        buffer1 = 0.0
        inputDigitCount++
    }

    fun onAdd(view: View) {
        if (isCommaActive) {
            input1 = buffer2 + utils.convertToBehindComma(buffer1)
        }
    }
}