package com.barabad.qr

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

import androidx.activity.enableEdgeToEdge
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class QrScanner : ComponentActivity() {

    //variables for qr scanner
    lateinit var scanResultTV: TextView
    lateinit var scanBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        // actual code of scanner
        //this is just for the text of the result (change for the database entry or soemthing)
        scanResultTV = findViewById(R.id.scanResultTv)
        //this is just for the button
        scanBtn = findViewById(R.id.scanBtn)

        scanBtn.setOnClickListener {
            val options = ScanOptions()
            options.setPrompt("Scan a QR Code")
            options.setBeepEnabled(true)
            options.setOrientationLocked(true)
            options.setCaptureActivity(CaptureActivity::class.java)
            barcodeLauncher.launch(options)
        }

    }

    //the result is in "result" and it just shows it raw so just format it nalang when needed
    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            scanResultTV.text = "Scanned result: ${result}"
        } else {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}

