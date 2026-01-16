package com.example.tiket.Activities.TiketDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.MotionLayout
import com.example.tiket.Activities.Splash.GradientButton
import com.example.tiket.Domain.FlightModel
import com.example.tiket.R

@Composable
fun TiketDetailScreen (
    flight: FlightModel,
    onBackClick: () -> Unit,
    onDownloadClick: () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkPurple2))
    ){
        Column( modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(colorResource(R.color.darkPurple2))
        ) {
            ConstraintLayout(modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.darkPurple2))
            ) {
                val (topSection, tiketDetail) = createRefs()

                TiketDetailHeader(onBackClick = onBackClick, Modifier.constrainAs(topSection) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            }
            GradientButton(onClick = {}, "Download Ticket")
        }
    }
}