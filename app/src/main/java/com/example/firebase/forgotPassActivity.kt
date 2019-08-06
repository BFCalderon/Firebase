package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_pass.*

class forgotPassActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        auth = FirebaseAuth.getInstance()
    }

    fun restarUser(view: View){
        progresVarRestar.visibility = View.VISIBLE
        if(!TextUtils.isEmpty(email.text)){
            auth.sendPasswordResetEmail(email.text.toString())
                .addOnCompleteListener {Task ->
                    if(Task.isSuccessful){
                        Toast.makeText(this, "EMAIL DE RECUPERACION ENVIADO", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "ERROR AL ENVIAR EMAIL, VERIFIQUE Y VUELVA A INTENTARLO", Toast.LENGTH_LONG).show()
                    }
                    progresVarRestar.visibility = View.INVISIBLE
                }
        }
    }
}
