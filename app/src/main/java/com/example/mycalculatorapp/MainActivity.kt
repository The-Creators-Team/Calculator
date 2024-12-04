package com.example.mycalculatorapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.mycalculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    private lateinit var calcuViewModel:CalcuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        calcuViewModel=ViewModelProvider(this).get(CalcuViewModel::class.java)

        val binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



        binding.apply {
            updateUi()

            btn0.setOnClickListener{calcuViewModel.addValue("0").also { updateUi() }}
            btn1.setOnClickListener{calcuViewModel.addValue("1").also { updateUi() }}
            btn2.setOnClickListener{calcuViewModel.addValue("2").also { updateUi() }}
            btn3.setOnClickListener{calcuViewModel.addValue("3").also { updateUi() }}
            btn4.setOnClickListener{calcuViewModel.addValue("4").also { updateUi() }}
            btn5.setOnClickListener{calcuViewModel.addValue("5").also { updateUi() }}
            btn6.setOnClickListener{calcuViewModel.addValue("6").also { updateUi() }}
            btn7.setOnClickListener{calcuViewModel.addValue("7").also { updateUi() }}
            btn8.setOnClickListener{calcuViewModel.addValue("8").also { updateUi() }}
            btn9.setOnClickListener{calcuViewModel.addValue("9").also { updateUi() }}

            btnDot.setOnClickListener{calcuViewModel.addOperator(".").also { updateUi() }}

            btnPlus.setOnClickListener{calcuViewModel.addOperator("+").also { updateUi() }}
            btnMinus.setOnClickListener{calcuViewModel.addOperator("-").also { updateUi() }}
            btnDivide.setOnClickListener{calcuViewModel.addOperator("/").also { updateUi() }}
            btnMultiply.setOnClickListener{calcuViewModel.addOperator("*").also { updateUi() }}
            btnModules.setOnClickListener{calcuViewModel.addOperator("%").also { updateUi() }}







            btnEqualTo.setOnClickListener{
                calcuViewModel.onEqual().also { updateUi() }
            }
            btnAC.setOnClickListener{
                calcuViewModel.allClear().also { updateUi() }
            }

            btnClear.setOnClickListener{
                calcuViewModel.delLast().also { updateUi() }
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun ActivityMainBinding.updateUi() {
        inputValues.setText(calcuViewModel.operation.value)
        displayResult.setText(calcuViewModel.result.value?:"")
    }
}