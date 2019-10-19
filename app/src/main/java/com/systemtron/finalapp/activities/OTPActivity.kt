package com.systemtron.finalapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.systemtron.finalapp.R
import kotlinx.android.synthetic.main.activity_otp.*
import java.util.concurrent.TimeUnit

class OTPActivity : AppCompatActivity() {

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        val callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Toast.makeText(this@OTPActivity, "Verification Successful!", Toast.LENGTH_LONG)
                    .show()
                tvOTP.text = p0.smsCode
                signInWithPhone(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(this@OTPActivity, "Verification Failed", Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                Toast.makeText(this@OTPActivity, "Code Sent to ${etPhone.text}", Toast.LENGTH_LONG)
                    .show()
            }
        }


        btnVerify.setOnClickListener {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91${etPhone.text}",
                60,
                TimeUnit.SECONDS,
                this,
                callback
            )
        }
    }

    private fun signInWithPhone(p0: PhoneAuthCredential) {
        auth.signInWithCredential(p0).addOnCompleteListener { }.addOnSuccessListener { }
            .addOnFailureListener { }

    }
}
