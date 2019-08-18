package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.math.MathUtility
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity() {
    private val años = listOf("2019")
    private val meses = listOf("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")
    //private val dias = listOf("Lunea", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo")
    private val dias = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30)
    private val horas = listOf(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)

    private lateinit var auth: FirebaseAuth

    private lateinit var myRef: DatabaseReference
    private lateinit var database: FirebaseDatabase

    private var isLoguin = false

    private var mathUtility: MathUtility? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        isLoguing()

        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("USUARIOS").child("APELLIDO")
        myRef//.child("APELLIDO")
        /*val city = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA"
        )

        db.collection("USUARIOS").document("LA")
            .set(city)
            .addOnSuccessListener { Toast.makeText(this, "DATOS GUARDADOS", Toast.LENGTH_SHORT).show()}
            .addOnFailureListener { Toast.makeText(this, "ERROR SUBIENDO", Toast.LENGTH_SHORT).show()}*/
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
            startActivity(Intent(this, RecyclerExpandable::class.java))
        }
        alertDialog.setNegativeButton("NO") { _, _ ->
            Toast.makeText(this, "SI NO TIENE UN CUENTA POR FAVOR REGISTRESE!", Toast.LENGTH_LONG).show()
        }
        alertDialog.show()
    }

    private fun isLoguing(){
        if(isLoguin){
            startActivity(Intent(this, BluetoothActivity::class.java))
        }
    }

    private fun loguinUser(){
        if (!TextUtils.isEmpty(password.text) && !TextUtils.isEmpty(email.text)){
            progresVarLoguin.visibility = View.VISIBLE
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) {task ->
                    if(task.isSuccessful) {
                        isLoguin = true
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

        val user = auth.currentUser

        /*años.forEach{años->
            val userBDmesAños = database.getReference(años)
            userBDmesAños.child(años)
            meses.forEach { meses->
                val userBDmes = database.getReference(meses)
                userBDmes.child(meses)
                dias.forEach {dias->
                    val userBDdias = database.getReference(años).child(meses)
                    userBDdias.child(dias).setValue(dias)
                }
            }
        }*/

        fun Random.nextInt(range: IntRange): Int {
            return range.start + nextInt(range.last - range.start)
        }

        años.forEach{años->
            val userBDmesAños = database.getReference(años)
            userBDmesAños.child(años)
            meses.forEach { meses->
                val userBDmes = database.getReference(meses)
                userBDmes.child(meses)
                dias.forEach {dias->
                    val userBDdias = database.getReference(años).child(meses)
                    userBDdias.child(dias.toString())
                    horas.forEach {horas->
                        val userBDHoras = database.getReference(años).child(meses).child(dias.toString())
                        userBDHoras.child(horas.toString()).setValue(horas)
                        val userBDInformationByHours = database.getReference(años).child(meses).child(dias.toString()).child(horas.toString())
                        userBDInformationByHours.child("POTENCIA").setValue(Random().nextInt(0..100).toFloat())
                        userBDInformationByHours.child("EFICIENCIA").setValue(Random().nextInt(0..1000).toFloat())
                    }
                }
            }
        }

        /*meses.forEach {mes->
            val userBDmes = database.getReference("AÑOS").child("MESES")
            userBDmes.child(mes).setValue(mes)
            dias.forEach {dia->
                val userBDdia = database.getReference("AÑOS").child("MESES").child("DIA")
                userBDdia.child(dia).setValue(dia)
            }
        }*/
        /*val userBD = database.getReference("AÑOS")
        userBD.child("NOMBRE").setValue(name)
        userBD.child("APELLIDO").setValue(lastName)*/
    }
}


