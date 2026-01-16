package com.example.tiket.Activities.TiketDetail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tiket.Activities.Splash.StatusTopBarColor
import com.example.tiket.Domain.FlightModel
import com.example.tiket.R

class TiketDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val flight = intent.getSerializableExtra("flight") as FlightModel

        setContent {
            StatusTopBarColor()


            TiketDetailScreen(
                flight = flight,
                onBackClick = {
                    finish()
                },
                onDownloadClick = {

                }
            )
        }
    }
}