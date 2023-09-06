package com.divyansh.devconnectX.UI

import android.R.attr
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.divyansh.devconnectX.R
import com.divyansh.devconnectX.databinding.ActivityShareCardBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.qrcode.QRCodeWriter


class ShareCardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityShareCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShareCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var preference : SharedPreferences = getSharedPreferences("user-data" , MODE_PRIVATE)
        binding.nameShare.setText(preference.getString("name" , ""))
        binding.bioShare.setText(preference.getString("bio" , ""))
        binding.number.setText(preference.getString("number" , ""))
        binding.mailShare.setText(preference.getString("email" , ""))

        binding.mailShare.setOnClickListener {
             var intent = Intent(Intent.ACTION_VIEW , Uri.parse("mailto:${preference.getString("email" , "")}"))
            startActivity(intent)
        }
        binding.number.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW , Uri.parse("tel:${preference.getString("number" , "")}"))
            startActivity(intent)
        }

        var editButton : FloatingActionButton = findViewById(R.id.editButton)

        editButton.setOnClickListener {
            val intent = Intent(this , CreateCardActivity::class.java)
            startActivity(intent)
        }
        val data = preference.getString("name" , "") + "," + preference.getString("bio" , "") + "," + preference.getString("number" , "") + "," + preference.getString("email" , "")


        binding.connect.setOnClickListener {
            binding.shareScrollView.visibility = View.GONE
            binding.QrCard.visibility = View.VISIBLE
            binding.Qrcode.setImageBitmap(generateQr(data))
        }
        binding.scannerlottie.setOnClickListener {
            startScanner()
        }

        binding.contactsbutton.setOnClickListener {
            val intent = Intent(this , ContactActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
         val intent = Intent(this , SplashActivity::class.java)
        startActivity(intent)
    }
    private fun generateQr(data : String):Bitmap {
        val qrCodeWriter = QRCodeWriter()
        val bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 400, 400)
        val bitmap = Bitmap.createBitmap(400,400,Bitmap.Config.ARGB_8888)
        val width = bitMatrix.width
        val height = bitMatrix.height

        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
            }
        }
        return bitmap
    }
    private fun startScanner(){
        var  integrator : IntentIntegrator =  IntentIntegrator(this)
        integrator.setPrompt("scan OR")
        integrator.setOrientationLocked(false)
        integrator.initiateScan()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null && result.contents != null) {
            saveData(result.contents)
            val intent = Intent(this , ContactActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveData(contents: String) {
        var preference : SharedPreferences = getSharedPreferences("Networks" , MODE_PRIVATE)
        var editor = preference.edit()
        var counter : Int;

        if(preference.getInt("counter" , -10) == -10){
            counter = 1
            editor.putInt("counter" , 1)
            editor.apply()
        }
        else{
            counter = preference.getInt("counter" , 0)+1

        }
        editor.putInt("counter" , counter)
        editor.putString("user$counter" , contents )
        editor.apply()
    }
}