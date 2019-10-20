package com.systemtron.finalapp.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.systemtron.finalapp.R
import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity : AppCompatActivity() {

    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val alreadyLoggedIn: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        if (alreadyLoggedIn != null) {
            Toast.makeText(this, "Already Logged In", Toast.LENGTH_LONG).show()
            onLoggedIn(alreadyLoggedIn)
        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        sign_in_button.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, 101)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                101 -> try {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                    val account = task.getResult(ApiException::class.java)
                    onLoggedIn(account)
                } catch (e: ApiException) {

                }
            }
        }
    }

    private fun onLoggedIn(account: GoogleSignInAccount?) {
        val loginIntent = Intent(this, SearchActivity::class.java)
        startActivity(loginIntent)
        finish()
    }

}
