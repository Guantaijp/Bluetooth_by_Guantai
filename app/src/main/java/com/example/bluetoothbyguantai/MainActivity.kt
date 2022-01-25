package com.example.bluetoothbyguantai

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothStatusCodes
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Trace.isEnabled
import android.widget.Toast
import androidx.core.os.TraceCompat.isEnabled
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Compiler.disable


class MainActivity : AppCompatActivity() {
    private val REQUEST_ENABLE_BT = 1;
    private val REQUEST_DISCOVER_BT=1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Bluetooth_Adapter = BluetoothAdapter.getDefaultAdapter()
        if (Bluetooth_Adapter == null) {
            Bluetooth_Status.text = "Bluetooth is not available"
        } else {
            Bluetooth_Status.text = "Bluetooth is available"
        }

        OnButton.setOnClickListener {
            if (!Bluetooth_Adapter.isEnabled) {
                Toast.makeText(this, "Turning on Bluetooth...", Toast.LENGTH_SHORT).show()

                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

            } else {
                Toast.makeText(this, "Bluetooth is already ON", Toast.LENGTH_SHORT).show()
            }
        }

        OffButton.setOnClickListener {
         if (Bluetooth_Adapter.isEnabled)
         {
             Bluetooth_Adapter.disable()
             Toast.makeText(this,"Turning Bluetooth OFF",Toast.LENGTH_SHORT).show()
         }else{
             Toast.makeText(this,"Bluetooth is already OFF",Toast.LENGTH_SHORT).show()
         }
        }

        DiscoverableButton.setOnClickListener {
            if (!Bluetooth_Adapter.isDiscovering){
                Toast.makeText(this,"Make Your Device Discoverable",Toast.LENGTH_SHORT).show()

                val intent= Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
                startActivityForResult(intent,REQUEST_DISCOVER_BT)
            }
        }

        PairedDeviceButton.setOnClickListener {
            if(!Bluetooth_Adapter.isEnabled){
                PairedDeviceButton.text="Paired Devices"
                val devices = Bluetooth_Adapter.bondedDevices
                for (device in devices){
                    PairedDeviceTextView.append("\nDevice: "+device.name +","+device)
                }
                }else{
                    Toast.makeText(this,"Pair to a device  to get paired devices",Toast.LENGTH_SHORT).show()
            }
            }
        }



        }


