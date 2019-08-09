package com.example.firebase

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_device_list.*
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class BluetoothActivity : AppCompatActivity() {
    // Debugging for LOGCAT
    private val TAG = "DeviceListActivity"
    // EXTRA string to send on to mainactivity
    private val EXTRA_DEVICE_ADDRESS = "device_address"
    // Member fields
    private lateinit var mBtAdapter: BluetoothAdapter
    private lateinit var mPairedDevicesArrayAdapter: ArrayAdapter<String>
    private var btSocket: BluetoothSocket ?= null
    // SPP UUID service - this should work for most devices
    private val BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    private var mConnectedThread: ConnectedThread ?= ConnectedThread()
    internal lateinit var bluetoothIn: Handler
    internal val handlerState = 0                         //used to identify handler message
    private var firstTime = true

    private val recDataString = StringBuilder()

    inner class ConnectedThread : Thread(){
        private var mmInStream: InputStream? = null
        private var mmOutStream: OutputStream? = null

        //creation of the connect thread

        fun connectedThread(socket: BluetoothSocket) {
            var tmpIn: InputStream? = null
            var tmpOut: OutputStream? = null
            try {
                //Create I/O streams for connection
                tmpIn = socket.inputStream
                tmpOut = socket.outputStream
            } catch (ignored: IOException) {}
            mmInStream = tmpIn
            mmOutStream = tmpOut
        }
        override fun run() {
            val buffer = ByteArray(256)
            var bytes: Int

            // Keep looping to listen for received messages
            while (true) {
                try {
                    bytes = mmInStream!!.read(buffer)            //read bytes from input buffer
                    val readMessage = String(buffer, 0, bytes)
                    // Send the obtained bytes to the UI Activity via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget()
                } catch (e: IOException) {
                    break
                }
            }
        }
        //write method
        fun write(input: String) {
            val msgBuffer = input.toByteArray()           //converts entered String into bytes
            try {
                mmOutStream!!.write(msgBuffer)                //write bytes over BT connection via outstream
            } catch (e: IOException) {
                //if you cannot write, close the application
                Toast.makeText(baseContext, "La Conexión fallo", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
 
    //override fun

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)
        linearConecting.visibility = View.VISIBLE

        sendBtn.setOnClickListener {
            if(!TextUtils.isEmpty(editSendBT.text)){
                mConnectedThread!!.write("${editSendBT.text}")
                editSendBT.text.clear()
            }else{
                Toast.makeText(this, "CAMPO VACIO", Toast.LENGTH_SHORT).show()
            }
        }
        reconectar.setOnClickListener {
            conectBluetoothManager()
        }
        buttonOn.setOnClickListener {
            mConnectedThread!!.write("12345")
            Toast.makeText(baseContext, "12345", Toast.LENGTH_SHORT).show()
        }
        buttonOff.setOnClickListener {
            mConnectedThread!!.write("BRAYAN")
            Toast.makeText(baseContext, "BRAYAN", Toast.LENGTH_SHORT).show()
        }

        bluetoothIn =
            @SuppressLint("HandlerLeak")
            object : Handler() {
                override fun handleMessage(msg: android.os.Message) {
                    if (msg.what == handlerState) {                        //if message is what we want
                        val readMessage = msg.obj as String                // msg.arg1 = bytes from connect thread
                        recDataString.append(readMessage)                  //keep appending to string until ~
                        val bar = recDataString.toString().split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        if (bar.size >= 11 && bar[10].length > 13) {
                            if(firstTime) {
                                relativeCharging.visibility = View.GONE
                                relativeInformation.visibility = View.VISIBLE
                                firstTime = false
                            }
                            adc1.text = bar[0]
                            adc2.text = bar[1]
                            adc3.text = bar[2]
                            adc4.text = bar[3]
                            adc5.text = bar[4]
                            adc6.text = bar[5]
                            adc7.text = bar[6]
                            adc8.text = bar[7]
                            eeprom.text = bar[8]
                            rtcHour.text = bar[9]
                            if (rtcDate.text.toString() != bar[10]) {
                                rtcDate.text = bar[10]
                            }
                        }else if(bar.size == 1 && bar[0] != "" && bar[0] != "\r"){
                            Toast.makeText(this@BluetoothActivity, bar[0], Toast.LENGTH_SHORT).show()
                        }
                        val endOfLineIndex = recDataString.indexOf("~")                    // determine the end-of-line
                        if (endOfLineIndex > 0) {                                           // make sure there data before ~
                            val dataInPrint = recDataString.substring(0, endOfLineIndex)// extract string
                            val dataLength = dataInPrint.length                            //get length of data received
                            testView1.text = "Tamaño del String = $dataLength"
                            recDataString.delete(0, recDataString.length)                //clear all string data
                        }
                    }
                }
            }
    }

    override fun onPause() {
        super.onPause()
        closeBluetooth()
    }

    override fun onDestroy() {
        super.onDestroy()
        closeBluetooth()
    }

    private fun closeBluetooth(){
        try {
            //Don't leave Bluetooth sockets open when leaving activity
            mConnectedThread!!.interrupt()
            btSocket!!.close()
            Toast.makeText(this@BluetoothActivity, "BT DESCONECTADO", Toast.LENGTH_SHORT).show()
        } catch (e2: IOException) {
        }
    }

    override fun onStart() {
        super.onStart()
        conectBluetoothManager()
    }

    private fun conectBluetoothManager(){
        var conection = false
        do {
            try {
                Handler().postDelayed({conectBluetooth()},1000)
                conection = true
            } catch ( e: IOException) {
                Toast.makeText(this, "LA CREACION DEL SOCKED FALLÓ", Toast.LENGTH_LONG).show()
                try {
                    mConnectedThread!!.interrupt()
                } catch (e2: IOException) {}
                try {
                    btSocket?.close()
                }catch (e: IOException){}
            }
        } while (!conection)
    }

    private fun conectBluetooth(){
        try {
            checkBTState()
            mBtAdapter = BluetoothAdapter.getDefaultAdapter()
            val device: BluetoothDevice = mBtAdapter.getRemoteDevice("98:D3:71:FD:6A:54")
            btSocket = device.createRfcommSocketToServiceRecord(BTMODULEUUID)
            btSocket!!.connect()
            mConnectedThread!!.connectedThread(btSocket!!)
            mConnectedThread!!.start()
        } catch (e: IOException){}
    }

    //Checks that the Android device Bluetooth is available and prompts to be turned on if off
    private fun checkBTState(){
        // Check device has Bluetooth and that it is turned on
        mBtAdapter = BluetoothAdapter.getDefaultAdapter()// CHECK THIS OUT THAT IT WORKS!!!
        if (mBtAdapter.isEnabled) {
            Toast.makeText(this, "BLUETOOTH ACTIVADO", Toast.LENGTH_SHORT).show()
        } else {
            //Prompt user to turn on Bluetooth
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, 1)
        }
    }
    fun txtString(view: View){

    }
}