package com.example.bmicalculator

import android.animation.ArgbEvaluator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class BMIResult : AppCompatActivity() {

    private lateinit var tvBmi: TextView
    private lateinit var tvBodyFat: TextView
    private lateinit var tvBmiCategory: TextView
    private lateinit var tvNormalBMI: TextView
    private lateinit var bntBack: Button

    var bmi=0.0
    var bmiCategory=""
    var bodyFat=0.0

    var age=1
    var height=1.0
    var weight=1.0
    override fun onCreate(savedInstanceState: Bundle?) {
//For  Remove Title Bar From the Activity
        getSupportActionBar()?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiresult)

        tvBmi=findViewById(R.id.tvBmi)
        tvBodyFat=findViewById(R.id.tvBodyFat)
        tvBmiCategory=findViewById(R.id.tvBmiCategory)
        tvNormalBMI=findViewById(R.id.tvNormalBMI)

//        Toast.makeText(applicationContext,"Hiii, This is BMI Result", Toast.LENGTH_SHORT).show()
        val bundle = intent.extras
        if (bundle != null){
            val ageTemp = bundle.getString("age")
            val heightTemp = bundle.getString("height")
            val weightTemp = bundle.getString("weight")
            if(!(ageTemp.isNullOrBlank()))
                age=ageTemp.toInt()
            if(!(heightTemp.isNullOrBlank()))
                height=heightTemp.toDouble()
            if(!(weightTemp.isNullOrBlank()))
                weight=weightTemp.toDouble()
        }
        bmi=(weight/ Math.pow((height / 100), 2.0))
        bodyFat=(1.20*bmi)+(0.23*age)-5.4
        if(age>=20) {
            bmiCategory = when (bmi) {
                in 0.0..16.0 -> "Severe Thinness"
                in 16.0..18.0 -> "Moderate Thinness"
                in 18.0..18.5 -> "Mild Thinness"
                in 18.5..25.0 -> "Normal"
                in 25.0..30.0 -> "Overweight"
                in 30.0..35.0 -> "Obese Class I"
                in 35.0..40.0 -> "Obese Class II"
                else -> "Obese Class III"
            }
            tvNormalBMI.text="18.5  <=  25.0"
        }
        else {
            bmiCategory = when (bodyFat) {
                in 0.0..5.0 -> "Underweight"
                in 5.0..85.0 -> "Healthy weight"
                in 85.0..95.0 -> "At risk of overweight"
                else -> "Overweight"
            }
            tvNormalBMI.text="5.0  <=  85.0"
        }
        tvBmi.text="%.2f".format(bmi)
        tvBodyFat.text="%.2f".format(bodyFat)+"%"
        tvBmiCategory.text=bmiCategory

        if(bmi<18.6 && age>=20){
            var color = ContextCompat.getColor(this, R.color.color_LowWeight) as Int
            tvBmiCategory.setTextColor(color)
        }
        else if(bmi<25.1 && age>=20){
            var color = ContextCompat.getColor(this, R.color.color_Healthy) as Int
            tvBmiCategory.setTextColor(color)
        }
        else if(bmi<30.1 && age>=20) {
            var color = ContextCompat.getColor(this, R.color.color_Fat) as Int
            tvBmiCategory.setTextColor(color)
        }
        else if(bmi>30.1 && age>=20) {
            var color = ContextCompat.getColor(this, R.color.color_OverWeight) as Int
            tvBmiCategory.setTextColor(color)
        }

        else if(bodyFat<=5.0){
            var color = ContextCompat.getColor(this, R.color.color_LowWeight) as Int
            tvBmiCategory.setTextColor(color)
        }
        else if(bodyFat<=85.0){
            var color = ContextCompat.getColor(this, R.color.color_Healthy) as Int
            tvBmiCategory.setTextColor(color)
        }
        else if(bodyFat<=95.0){
            var color = ContextCompat.getColor(this, R.color.color_Fat) as Int
            tvBmiCategory.setTextColor(color)
        }
        else{
            var color = ContextCompat.getColor(this, R.color.color_OverWeight) as Int
            tvBmiCategory.setTextColor(color)
        }
        val bntBack=findViewById<Button>(R.id.bntBack)
        bntBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}