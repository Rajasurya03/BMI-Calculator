package com.example.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var etAge: EditText
    private lateinit var etHeight: EditText
    private lateinit var etWeight: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var radioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
//For  Remove Title Bar From the Activity
        getSupportActionBar()?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etAge=findViewById(R.id.etAge)
        etHeight=findViewById(R.id.etHeight)
        etWeight=findViewById(R.id.etWeight)

        rgGender=findViewById(R.id.rgGender)
        rgGender.setOnCheckedChangeListener{ group, checkedId ->
            radioButton=findViewById(checkedId)

//            if(checkedId==R.id.rbMale){
//                Toast.makeText(applicationContext,"Gender "+radioButton.text, Toast.LENGTH_SHORT).show()
//            }
//            else if(checkedId==R.id.rbFemale){
//                Toast.makeText(applicationContext,"Gender "+radioButton.text, Toast.LENGTH_SHORT).show()
//            }
        }

        val bntSubmit=findViewById<Button>(R.id.bntSubmit)
        bntSubmit.setOnClickListener {

//            Toast.makeText(applicationContext,"Button Clicked", Toast.LENGTH_SHORT).show()
            var age=0
            if(!(etAge.text.isNullOrBlank()))
                age=(etAge.text).toString().toInt()
            var height=0.0
            if(!(etHeight.text.isNullOrBlank()))
                height=(etHeight.text).toString().toDouble()
            var weight=0.0
            if(!(etWeight.text.isNullOrBlank()))
                weight=(etWeight.text).toString().toDouble()

            if(age>150 || age<1)
                Toast.makeText(applicationContext,"Invalid Age "+etAge.text, Toast.LENGTH_SHORT).show()
            else if(height>300.0 || height<1.0)
                Toast.makeText(applicationContext,"Invalid Height "+etHeight.text, Toast.LENGTH_SHORT).show()
            else if(weight>800.0 || weight<1.0)
                Toast.makeText(applicationContext,"Invalid Weight "+etWeight.text, Toast.LENGTH_SHORT).show()
            else {
                val bundle = Bundle()
                bundle.putString("age", (etAge.text).toString())
                bundle.putString("height", (etHeight.text).toString())
                bundle.putString("weight", (etWeight.text).toString())

                val intent = Intent(this, BMIResult::class.java)
                intent.putExtras(bundle)
//                overridePendingTransition(R.anim.slide_to_top,R.anim.slide_from_bottom)
                startActivity(intent)
            }
        }
    }
}