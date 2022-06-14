package com.example.assignment10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    var receiver: BroadcastReceiver? = null
    var pluggedIn = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val action = intent.action
                val int: Int
                if (Intent.ACTION_HEADSET_PLUG == action) {
                    int = intent.getIntExtra("state", -1)
                    if (int == 0) {
                        pluggedIn = false
                        Toast.makeText(applicationContext, "Headphones (AUX) is plugged out", Toast.LENGTH_LONG).show()
                    }
                    if (int == 1) {
                        pluggedIn = true
                        Toast.makeText(applicationContext, "Headphones (AUX) is plugged in", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        val receiverFilter = IntentFilter(Intent.ACTION_HEADSET_PLUG)
        registerReceiver(receiver, receiverFilter)
    }
}