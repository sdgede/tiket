package com.example.tiket.Activities.Dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiket.Activities.SearchResult.SearchResultActivity
import com.example.tiket.Activities.Splash.GradientButton
import com.example.tiket.Activities.Splash.StatusTopBarColor
import com.example.tiket.Domain.LocationModel
import com.example.tiket.R
import com.example.tiket.ViewModel.MainViewModel

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MainScreen()
        }
    }
}

@Composable
@Preview
fun MainScreen() {
    val locations = remember { mutableStateListOf<LocationModel>() }
    val viewModel: MainViewModel = viewModel() // Gunakan viewModel() delegate
    var showLocationLoading by remember { mutableStateOf(true) }

    var from:String =""
    var to:String =""
    var classes:String =""
    var adultPassenger: String = ""
    var childPassenger: String = ""
    val context = LocalContext.current


    StatusTopBarColor()

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = Observer<MutableList<LocationModel>> { locationList ->
            locationList?.let {
                locations.clear()
                locations.addAll(it)
                showLocationLoading = false
            }
        }

        viewModel.loadLocation().observe(lifecycleOwner, observer)

        onDispose {
            viewModel.loadLocation().removeObserver(observer)
        }
    }

    Scaffold(
        bottomBar = { MyBottomBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.darkPurple2))
                .padding(paddingValues = paddingValues)
        ) {
            item { TopBar() }
            item {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .background(
                            colorResource(R.color.darkPurple),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .fillMaxSize()
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                ) {
                    // from Selection
                    YellowTitle("From")
                    val locationNames: List<String> = locations.map { it.Name }
                    DropDownList(
                        items = locationNames,
                        loadingIcon = painterResource(R.drawable.from_ic),
                        hint = "Select Origin",
                        showLocationLoading = showLocationLoading
                    ) { selectedItem ->
                        from = selectedItem
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    // To Selection
                    YellowTitle("To")
                    DropDownList(
                        items = locationNames,
                        loadingIcon = painterResource(R.drawable.from_ic),
                        hint = "Select Destination",
                        showLocationLoading = showLocationLoading
                    ) { selectedItem ->
                        to = selectedItem
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // Passenger Count
                    YellowTitle("Passenger")
                    Row (modifier = Modifier.fillMaxWidth()){
                        PassengerCounter(
                            title = "Adult",
                            modifier = Modifier.weight(1f),
                            onIntentSelected = {
                                adultPassenger = it
                            }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        PassengerCounter(
                            title = "Child",
                            modifier = Modifier.weight(1f),
                            onIntentSelected = {
                                childPassenger = it
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    // Calender
                    Row{
                        YellowTitle("Departure Date", Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(20.dp))
                        YellowTitle("Return Date", Modifier.weight(1f))
                    }
                    DatePickerScreen(Modifier.weight(1f))
                    Spacer(modifier = Modifier.height(16.dp))

                    // Classes Selection
                    YellowTitle("Class")
                    val classesList = listOf("Economy Class", "Business Class", "First Class")
                    DropDownList(
                        items = classesList,
                        loadingIcon = painterResource(R.drawable.seat_black_ic),
                        hint = "Select Class",
                        showLocationLoading = showLocationLoading
                    ) { selectedItem ->
                        classes = selectedItem
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    // Search Button
                    GradientButton(
                        onClick = {
                            val intent = Intent(context, SearchResultActivity::class.java).apply {
                                putExtra("from", from)
                                putExtra("to", to)
                                putExtra("numPassengers", adultPassenger + childPassenger)
                            }
                            startActivity(context,intent, null)
                        },
                        text = "Search",
                    )
                }
            }
        }
    }
}

@Composable
fun YellowTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        color = colorResource(R.color.orange),
        modifier = modifier
    )
}