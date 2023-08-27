package com.divyansh.devconnectX.UI

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.divyansh.devconnectX.R
import com.divyansh.devconnectX.databinding.ActivityCreateCardBinding
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private lateinit var binding : ActivityCreateCardBinding
class CreateCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.submit.setOnClickListener {
            var name = binding.Name.text.toString()
            var number = binding.phoneNumber.text.toString()
            var bio = binding.BIO.text.toString()
            var email = binding.mail.text.toString()
            if(name.isBlank()){
                Toast.makeText(this , "NAME FIELD CANNOT BE EMPTY" , Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            saveData(name , number , bio , email)

            var includedLayout : View = findViewById(R.id.successcardDisplay)
            includedLayout.visibility = View.VISIBLE

            binding.scrollCreate.visibility = View.INVISIBLE
            binding.cardHeading.visibility = View.INVISIBLE
            binding.lottieAnimationView.visibility = View.INVISIBLE

            lifecycleScope.launch {
                var intent : Intent = Intent(applicationContext , ShareCardActivity::class.java)
                delay(2000)
                startActivity(intent)
                finish()
            }
        }

    }
    private fun saveData(name : String , number : String , bio : String , email : String){
        var preference : SharedPreferences = getSharedPreferences("user-data" , MODE_PRIVATE)
        var editor = preference.edit()
        editor.putString("name" , name)
        if(!number.isBlank()){
            editor.putString("number" , number)
        }
        if(!bio.isBlank()){
            editor.putString("bio" , bio)
        }
        if(email.isNotBlank()){
            editor.putString("email" , email)
        }
        editor.apply()
    }
}