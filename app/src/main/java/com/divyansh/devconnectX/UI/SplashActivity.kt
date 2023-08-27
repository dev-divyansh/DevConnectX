package com.divyansh.devconnectX.UI

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.divyansh.devconnectX.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var  intent : Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var preference : SharedPreferences = getSharedPreferences("card-status" , MODE_PRIVATE)
        var editor = preference.edit()

        if(!preference.getBoolean("isCardCreated" , false)){
            editor.putBoolean("isCardCreated" , true)
            editor.apply()
           intent = Intent(applicationContext , CreateCardActivity::class.java)
        }
        else{
            intent = Intent(applicationContext , ShareCardActivity::class.java)
        }


        lifecycleScope.launch {
            delay(1000)
            startActivity(intent)
            finish()
        }
    }
}