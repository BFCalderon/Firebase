package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.firebase.valueobjects.DateInformationVO
import com.example.firebase.viewmodel.TreeInformationViewModel
import com.facebook.stetho.Stetho
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class LoginActivity : AppCompatActivity() {

    private lateinit var treeInformationViewModel: TreeInformationViewModel

    private val años = listOf("2019", "2020","2021")
    //private val meses = listOf("Enero", "Febrero", "Marzo"/*, "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"*/)
    //private val dias = listOf("Lunea", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo")
    private val meses = listOf(1,2,3/*,4,5,6,7,8,9,10,11,12*/)
    private val dias = listOf(1,2,3/*,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30*/)
    private val horas = listOf(0,1,2/*,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23*/)

    private var yearsFromFirebase: ArrayList<DateInformationVO> = ArrayList()

    private lateinit var auth: FirebaseAuth

    private lateinit var myRef: DatabaseReference
    private lateinit var database: FirebaseDatabase

    private var isLoguin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        isLoguing()
        database = FirebaseDatabase.getInstance()

        Stetho.initializeWithDefaults(this)
        treeInformationViewModel = run {
            ViewModelProviders.of(this).get(TreeInformationViewModel::class.java)
        }
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
                        //updateDataInFirebase()
                        readDataFromFirebase()
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
    private fun updateDataInFirebase(){
        fun Random.nextInt(range: IntRange): Float {return range.start + nextInt(range.last - range.start).toFloat()}

        años.forEach{años->
            val userBDmesAños = database.getReference("ARBOL ENERGETICO").child(años)
            userBDmesAños.child(años)

            meses.forEach { mes->
                val userBDmes = database.getReference("ARBOL ENERGETICO").child(años).child(mes.toString())
                userBDmes.child(mes.toString())
                dias.forEach {dias->
                    val userBDdias = database.getReference("ARBOL ENERGETICO").child(años).child(mes.toString()).child(dias.toString())
                    userBDdias.child(dias.toString())
                    horas.forEach {horas->
                        val userBDHoras = database.getReference("ARBOL ENERGETICO").child(años).child(mes.toString()).child(dias.toString())
                        userBDHoras.child(horas.toString()).setValue(horas)
                        val userBDInformationByHours = database.getReference("ARBOL ENERGETICO").child(años).child(mes.toString()).child(dias.toString()).child(horas.toString())
                        userBDInformationByHours.child("POTENCIA").setValue(0.654f + Random().nextInt(0..100))
                        userBDInformationByHours.child("EFICIENCIA").setValue(0.123f + Random().nextInt(0..1000))
                    }
                }
            }
        }

        startActivity(Intent(this, BluetoothActivity::class.java))
        password.text.clear()
        email.text.clear()
    }

    private fun readDataFromFirebase() {
        myRef = database.getReference("ARBOL ENERGETICO")//.child("2019")//.child("Abril")//.child("1")//.child("0")//.child("POTENCIA")

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val t = object : GenericTypeIndicator<Any>() {}
                val jsonTreeInformation = dataSnapshot.getValue(t)
                jsonTreeInformation as HashMap<*,*>
                jsonTreeInformation.toString()

                val yearsInFirebase: ArrayList<DateInformationVO> = ArrayList()
                val monthsInFirebase: ArrayList<DateInformationVO> = ArrayList()
                val daysInFirebase: ArrayList<DateInformationVO> = ArrayList()
                val hourInFirebase: ArrayList<DateInformationVO> = ArrayList()
                    var yearIterator = 0
                //jsonTreeInformation as ArrayList<ArrayList<ArrayList<HashMap<*, *>>>>
                val sorted = jsonTreeInformation.toList() .sortedBy { (key, value) -> key.toString() }.toMap()
                for(keyYear in sorted){
                    yearIterator++
                    yearsInFirebase.add(DateInformationVO(date = keyYear.key.toString().toInt(), efficiency = 1.3f,power = 1.5f))
                    val year = keyYear.value as ArrayList<*>
                    var monthIterator = 0
                    year.removeAt(0)
                    year.forEach {months->
                        monthIterator++
                        monthsInFirebase.add(DateInformationVO(foreingKey = yearIterator, date = monthIterator, power = 2.6f,efficiency = 2.8f))
                            val month = months as ArrayList<ArrayList<HashMap<*, *>>>
                            var daysIterator = 0
                            month.removeAt(0)//Como los dias siempre son numeros mayores a 1, firebase retorna la posicion cero nula
                            month.forEach { days ->
                                    daysIterator++
                                    daysInFirebase.add(
                                        DateInformationVO(
                                            foreingKey = monthIterator,
                                            foreingKey1 = yearIterator,
                                            date = daysIterator,
                                            efficiency = 3.9f,
                                            power = 3.3f
                                        )
                                    )
                                    var hourIterator = 0
                                    days.forEach { hour ->
                                        hourIterator++
                                        hourInFirebase.add(
                                            DateInformationVO(
                                                foreingKey = daysIterator,
                                                foreingKey1 = monthIterator,
                                                foreingKey2 = yearIterator,
                                                date = hourIterator,
                                                power = hour.get("POTENCIA").toString().toFloat(),
                                                efficiency = hour.get("EFICIENCIA").toString().toFloat()
                                            )
                                        )
                                        for (keyHour in hour) {
                                            keyHour.key.toString()
                                            keyHour.value.toString()
                                        }
                                    }
                                //}
                            }
                        //}
                    }
                }
                /*yearsInFirebase
                yearsInFirebase.sortBy { DateInformationVO -> DateInformationVO.date }
                yearsInFirebase*/

                yearsInFirebase.forEach {
                    treeInformationViewModel.saveYearInformation(it)
                    //treeInformationViewModel.deleteYearInformation(2019)

                }
                monthsInFirebase.forEach {
                    treeInformationViewModel.saveMonthInformation(it)
                }
                daysInFirebase.forEach {
                    treeInformationViewModel.saveDaysInformation(it)
                }
                hourInFirebase.forEach {
                    treeInformationViewModel.saveHoursInformation(it)
                }
            }
            override fun onCancelled(error: DatabaseError){}
        })
    }
}


