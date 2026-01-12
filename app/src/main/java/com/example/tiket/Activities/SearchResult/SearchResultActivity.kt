package com.example.tiket.Activities.SearchResult

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.tiket.Activities.Dashboard.MainScreen
import com.example.tiket.Activities.Splash.StatusTopBarColor
import com.example.tiket.ViewModel.MainViewModel

class SearchResultActivity : AppCompatActivity() {
    private val viewModel= MainViewModel()
    private var from:String =""
    private var to:String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()

        from=intent.getStringExtra("from")?:""
        to=intent.getStringExtra("to")?:""

        setContent {
            StatusTopBarColor()
            ItemListScreen(
                from = from,
                to = to,
                viewModel = viewModel,
                onBackClick = { }
            )
        }
    }
}