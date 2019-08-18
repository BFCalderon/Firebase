package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbReference = database.reference.child("APELLIDO").child("APELLIDO")
    }

    fun buttonRegister(view: View){
        createNewAcount()
    }

    private fun action(){
        startActivity(Intent(this, LoginActivity::class.java))
        progresVar.visibility = View.INVISIBLE
    }
    private fun createNewAcount(){
        val name : String = name.text.toString()
        val lastName: String = lastname.text.toString()
        val email: String = emailAddress.text.toString()
        val password: String = password.text.toString()

        if(password.length<6){
            Toast.makeText(this, "La contraseña debe tener minimo 6 caracteres", Toast.LENGTH_SHORT).show()
        }else {

            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(lastName)
                && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

                progresVar.visibility = View.VISIBLE
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                    if (task.isComplete) {
                        val user: FirebaseUser? = auth.currentUser
                        verifyEmail(user)
                        val userBD = dbReference.child(user?.uid!!)
                        userBD.child("NOMBRE").setValue(name)
                        userBD.child("APELLIDO").setValue(lastName)
                        action()
                    }
                }
            }
        }
    }
    private fun verifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()?.addOnCompleteListener(this){task ->
            if(task.isComplete){
                Toast.makeText(this, "CORREO DE CONFIRMACION ENVIADO", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "ERROR AL ENVIAR EMAIL DE VERIFICACION", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
