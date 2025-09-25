package com.example.calculator

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var utils = Utils()
    private var hasOperand: Boolean = false
    private var actAdd: Boolean = false
    private var actSubtract: Boolean = false
    private var actMultiply: Boolean = false
    private var actDivide: Boolean = false
    private var actModulo: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onButtonPress(v: View) {
        val input = binding.viewResult.text.toString()
        val setNumberToView: (String) -> Unit = { s ->
            if (binding.viewResult.text != "0") {
                binding.viewResult.text = binding.viewResult.text.toString().plus(s)
            } else {
                binding.viewResult.text = s
            }
        }

        when (v.id) {
            R.id.button0 -> setNumberToView("0")
            R.id.button1 -> setNumberToView("1")
            R.id.button2 -> setNumberToView("2")
            R.id.button3 -> setNumberToView("3")
            R.id.button4 -> setNumberToView("4")
            R.id.button5 -> setNumberToView("5")
            R.id.button6 -> setNumberToView("6")
            R.id.button7 -> setNumberToView("7")
            R.id.button8 -> setNumberToView("8")
            R.id.button9 -> setNumberToView("9")
            R.id.buttonComma -> binding.viewResult.text = input.plus(".")
            R.id.buttonPercent -> binding.viewResult.text = (input.toDouble() / 100.0).toString()

            R.id.buttonDel -> {
                if (input[input.length - 2] == '.') {
                    binding.viewResult.text = input.toDouble().toLong().toString()
                }
                else if (input.length == 1) {
                    binding.viewResult.text = "0"
                }
                else if (input.isNotEmpty()) {
                    binding.viewResult.text = input.dropLast(1)
                }
            }

            R.id.buttonAC -> {
                binding.viewResult.text = "0"
                binding.viewCache.text = ""
            }

            R.id.buttonAdd -> {
                if (hasOperand) {
                    calculateToView(true)
                    actAdd = true
                    actDivide = false
                    actSubtract = false
                    actMultiply = false
                    actModulo = false
                }
                else {
                    binding.viewCache.text = binding.viewResult.text.toString()
                    binding.viewResult.text = ""
                    hasOperand = true
                    actAdd = true
                }
            }

            R.id.buttonSub -> {
                if (hasOperand) {
                    calculateToView(true)
                    actAdd = false
                    actDivide = false
                    actSubtract = true
                    actMultiply = false
                    actModulo = false
                }
                else {
                    binding.viewCache.text = binding.viewResult.text.toString()
                    binding.viewResult.text = ""
                    hasOperand = true
                    actSubtract = true
                }
            }

            R.id.buttonMult -> {
                if (hasOperand) {
                    calculateToView(true)
                    actAdd = false
                    actDivide = false
                    actSubtract = false
                    actMultiply = true
                    actModulo = false
                }
                else {
                    binding.viewCache.text = binding.viewResult.text.toString()
                    binding.viewResult.text = ""
                    hasOperand = true
                    actMultiply = true
                }
            }

            R.id.buttonDiv -> {
                if (hasOperand) {
                    calculateToView(true)
                    actAdd = false
                    actDivide = true
                    actSubtract = false
                    actMultiply = false
                    actModulo = false
                }
                else {
                    binding.viewCache.text = binding.viewResult.text.toString()
                    binding.viewResult.text = ""
                    hasOperand = true
                    actDivide = true
                }
            }

            R.id.buttonMod -> {
                if (hasOperand) {
                    calculateToView(true)
                    actAdd = false
                    actDivide = false
                    actSubtract = false
                    actMultiply = false
                    actModulo = true
                }
                else {
                    binding.viewCache.text = binding.viewResult.text.toString()
                    binding.viewResult.text = ""
                    hasOperand = true
                    actModulo = true
                }
            }

            R.id.buttonEqual -> {
                calculateToView(false)
                hasOperand = false
            }
        }
    }

    fun calculateToView(toCache: Boolean) {
        val input = binding.viewResult.text.toString().ifEmpty { "0" }.toDouble()
        val cache = binding.viewCache.text.toString().ifEmpty { "0" }.toDouble()
        var result = "Err"

        if (actAdd) {
            result = utils.formatNumber(cache + input)
            actAdd = false
        }

        if (actSubtract) {
            result = utils.formatNumber(cache - input)
            actSubtract = false
        }

        if (actMultiply) {
            result = utils.formatNumber(cache * input)
            actMultiply = false
        }

        if (actDivide) {
            if (input == 0.0) {
                result = "Err"
            } else {
                result = utils.formatNumber(cache / input)
            }

            actDivide = false
        }

        if (actModulo) {
            if (input == 0.0) {
                result = "Err"
            } else {
                result = utils.formatNumber(cache % input)
            }

            actModulo = false
        }

        if (toCache) {
            binding.viewResult.text = "0"
            binding.viewCache.text = result
        } else {
            binding.viewResult.text = result
            binding.viewCache.text = ""
        }
    }
}