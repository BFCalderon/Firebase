package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
    }
    fun signin(view: View){
        loguinUser()
    }

    fun forgotPass(view: View){
        startActivity(Intent(this, forgotPassActivity::class.java))
    }

    fun register(view: View){
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    fun offlineButton(view: View){
        popUpMessage()
    }

    private fun popUpMessage() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("OFFLINE")
        alertDialog.setMessage("Los datos no se enviaran al servidor. ¿Desea continuar?")

        alertDialog.setPositiveButton("SI") { _, _ ->
            startActivity(Intent(this, ExpandableRecyclerYearMonth::class.java))
        }
        alertDialog.setNegativeButton("NO") { _, _ ->
            Toast.makeText(this, "SI NO TIENE UN CUENTA POR FAVOR REGISTRESE!", Toast.LENGTH_LONG).show()
        }
        alertDialog.show()
    }

    private fun loguinUser(){
        if (!TextUtils.isEmpty(password.text) && !TextUtils.isEmpty(email.text)){
            progresVarLoguin.visibility = View.VISIBLE
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) {task ->
                    if(task.isSuccessful) {
                        action()
                    } else {
                        Toast.makeText(this, "ERROR DE AUTENTICACION", Toast.LENGTH_SHORT).show()
                        progresVarLoguin.visibility = View.INVISIBLE
                    }
                    progresVarLoguin.visibility = View.INVISIBLE
                }
        }else{
            Toast.makeText(this, "DATOS INCOMPLETOS", Toast.LENGTH_SHORT).show()
        }
    }
    private fun action(){
        startActivity(Intent(this, BluetoothActivity::class.java))
        password.text.clear()
        email.text.clear()
    }
}


