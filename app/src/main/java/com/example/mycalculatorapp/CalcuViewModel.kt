package com.example.mycalculatorapp

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder

class CalcuViewModel:ViewModel() {

    private val _operations=MutableLiveData<String>().apply {
        value=""
    }
    val operation:LiveData<String> = _operations

    private val _result=MutableLiveData<String>().apply{
        value=""
    }

    val result:LiveData<String> = _result

    private var newOperator=false
    private var operator=""


    fun addValue(num: String) {
        _operations.value +=num
        newOperator=true
        getResult()
    }


    fun addOperator(oper: String) {
        if(newOperator) {
                _operations.value += oper
                newOperator=false
                operator=oper
        }
    }



    fun getResult(){
        val expString = _operations.value ?: return

        if (expString.contains("/0")) {
            _result.value = "Cannot divide by zero"
            return
        }
        try {

            val expression = ExpressionBuilder(expString).build()
            _result.value = expression.evaluate().toString()
            newOperator=true
        } catch (e: Exception) {

            _result.value = ""
        }
    }

    fun allClear(){
        _operations.value = ""
        _result.value = ""
        newOperator = false
        operator = ""
    }

    fun onEqual(){

        _operations.value=_result.value
        newOperator=true

    }

    fun delLast() {
        val currentExp = _operations.value ?: return
        if (currentExp.isNotEmpty()) {
            _operations.value = currentExp.substring(0, currentExp.length - 1)
            getResult()
        }
    }
}





