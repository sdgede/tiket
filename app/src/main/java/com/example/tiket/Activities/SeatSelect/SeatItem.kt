package com.example.tiket.Activities.SeatSelect

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiket.R

@Composable
fun SeatItem(
    seat: Seat,
    onSeatClick: () -> Unit
){
    val backgroundColor = when (seat.status) {
        SeatStatus.AVAILABLE -> colorResource(R.color.green)
        SeatStatus.SELECTED -> colorResource(R.color.orange)
        SeatStatus.UNVAILABLE -> colorResource(R.color.grey)
        SeatStatus.EMPTY -> Color.Transparent
    }
    val textColor = when(seat.status){
        SeatStatus.AVAILABLE -> Color.White
        SeatStatus.SELECTED -> Color.Black
        SeatStatus.UNVAILABLE -> Color.Gray
        SeatStatus.EMPTY -> Color.Transparent
    }
    val clickableEnable = seat.status== SeatStatus.AVAILABLE || seat.status== SeatStatus.SELECTED

    Box(
        modifier = Modifier
            .size(28.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(backgroundColor)
            .clickable(enabled = clickableEnable) { onSeatClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = seat.name,
            color = textColor,
            fontSize = 12.sp
        )
    }
}