package com.divyansh.devconnectX.UI

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.divyansh.devconnectX.Data.Adapter
import com.divyansh.devconnectX.R
import com.divyansh.devconnectX.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    lateinit var data  :MutableList<Modal>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var preference : SharedPreferences = getSharedPreferences("Networks" , MODE_PRIVATE)
        var editor = preference.edit()

        data = mutableListOf<Modal>()
        var counter = preference.getInt("counter" , 0)
        if(counter!=0){
            for(i in 1..counter){
                preference.getString("user$i" , null)?.let { decompiler(it, preference) }
            }
            Log.d("dps" , data.toString())
        }

        if (counter == 0){
            Toast.makeText(applicationContext , "No Contacts Found" , Toast.LENGTH_LONG).show()
        }

        // Recycler view code
        val recyclerView = binding.recyclerview
        val linearLayoutManager = LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = Adapter(this , data)

    }

    private fun decompiler( content:String , preferences: SharedPreferences) {
        val inputString  = content.split(",")
        if(preferences.getInt("counter" , 0) !=0 ){
            data.add(Modal(inputString[0] , inputString[1] ,
                inputString[2] , inputString[3]  ))
        }
    }
}