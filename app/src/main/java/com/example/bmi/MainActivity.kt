package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //linking of UI and code
        val imageViewBMI: ImageView = findViewById(R.id.imageViewBMI)
        val bmi: TextView = findViewById(R.id.textViewBMI)
        val statusView: TextView = findViewById(R.id.textViewStatus)

        val height: EditText = findViewById(R.id.editTextHeight)
        val weight: EditText = findViewById(R.id.editTextWeight)

        val buttonCal: Button = findViewById(R.id.buttonCalculate)
        val buttonReset: Button = findViewById(R.id.buttonReset)

        buttonCal.setOnClickListener {
            //BMI = weight / height in meters power of 2

            if(weight.text.isEmpty()){
                weight.setError(getString(R.string.value_required))
                return@setOnClickListener // terminate program execution
            }
            if(height.text.isEmpty()){
                height.setError(getString(R.string.value_required))
                return@setOnClickListener // terminate program execution
            }

            val weightInput = weight.text.toString().toFloat()
            val heightInput = height.text.toString().toFloat()

            var bmiCal = weightInput/(heightInput/100).pow(2)


            if (bmiCal < 18.5){
                imageViewBMI.setImageResource(R.drawable.under)
                bmi.text = String.format("%s: %.2f", getString(R.string.bmi), bmiCal)
                statusView.text = String.format("%s: %s", getString(R.string.status), getString(R.string.under))
            }
            else if (bmiCal in 18.5 .. 24.9){
                imageViewBMI.setImageResource(R.drawable.normal)
                bmi.text = String.format("%s: %.2f", getString(R.string.bmi), bmiCal)
                statusView.text = String.format("%s: %s", getString(R.string.status), getString(R.string.normal))
            }
            else {
                imageViewBMI.setImageResource(R.drawable.over)
                bmi.text = String.format("%s: %.2f", getString(R.string.bmi), bmiCal)
                statusView.text = String.format("%s: %s", getString(R.string.status), getString(R.string.over))
            }
        }

        buttonReset.setOnClickListener{
            imageViewBMI.setImageResource(R.drawable.empty)
            bmi.text = String.format("%s: %.2f", getString(R.string.bmi))
            statusView.text = String.format("%s: %s", getString(R.string.status))
            weight.text.clear()
            height.text.clear()
        }

    }
}