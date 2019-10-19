package com.systemtron.finalapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.systemtron.finalapp.R
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnOTP.setOnClickListener{
            val otpIntent = Intent(applicationContext,OTPActivity::class.java)
            startActivity(otpIntent)
        }


        btnGoogle.setOnClickListener {
            val googleIntent = Intent(applicationContext,GoogleActivity::class.java)
            startActivity(googleIntent)
        }
    }
}
