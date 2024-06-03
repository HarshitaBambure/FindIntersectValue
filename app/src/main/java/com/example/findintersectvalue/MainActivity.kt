package com.example.findintersectvalue

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.findintersectvalue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

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

        binding.btnCalc.setOnClickListener {
            if (validateEmpty()) {
                val arr1 = binding.editTextText.text.toString().split(",").map { it.toInt() }
                val arr2 = binding.editTextText2.text.toString().split(",").map { it.toInt() }
                val arr3 = binding.editTextText3.text.toString().split(",").map { it.toInt() }
                val intersectValue = getCalculateIntersectValue(arr1, arr2, arr3)
                val unionValue = getUnionOfArray(arr1, arr2, arr3)
                val getMaxValue = getMaxValue(arr1, arr2, arr3)

                val printingData = StringBuilder()
                printingData.append("The intersect of those number: ${intersectValue} \n")
                printingData.append("The union of those number: ${unionValue.sorted()} \n")
                printingData.append("The highest of those number: ${getMaxValue} \n")
                binding.txtResult.setText(printingData)
            }
        }
    }

    private fun getMaxValue(arr1: List<Int>, arr2: List<Int>, arr3: List<Int>): Int {
        val listof = mutableListOf<Int>()
        listof.addAll(arr1)
        listof.addAll(arr2)
        listof.addAll(arr3)
        return listof.max()
    }

    private fun validateEmpty(): Boolean {
        var isValid = false
        if (binding.editTextText.text.toString().isEmpty()) {
            binding.editTextText.error = "Please enter a value"
        } else if (binding.editTextText2.text.toString().isEmpty()) {
            binding.editTextText2.error = "Please enter a value"
        } else if (binding.editTextText3.text.toString().isEmpty()) {
            binding.editTextText3.error = "Please enter a value"
        } else {
            isValid = true
        }
        return isValid
    }

    private fun getCalculateIntersectValue(
        arr1: List<Int>,
        arr2: List<Int>,
        arr3: List<Int>
    ): Set<Int> {
        return arr1.intersect(arr2).intersect(arr3)

    }

    private fun getUnionOfArray(arr1: List<Int>, arr2: List<Int>, arr3: List<Int>): Set<Int> {
        return arr1.union(arr2).union(arr3)

    }

}