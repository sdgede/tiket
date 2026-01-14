package com.example.tiket.Activities.SeatSelect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.tiket.Domain.FlightModel
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.tiket.R

enum class SeatStatus{
    AVAILABLE,
    SELECTED,
    UNVAILABLE,
    EMPTY
}

data class Seat (
    var status : SeatStatus,
    var name : String
)

@Composable
fun SeatItemScreen(
    fligh: FlightModel,
    onBackClick: () -> Unit,
    onConfirmClick: (FlightModel) -> Unit,
){
    val context = LocalContext.current

    val seatList = remember { mutableStateListOf<Seat>() }
    val selectedSeatNames = remember { mutableStateListOf<String>() }

    var seatCount by remember { mutableStateOf(0) }
    var totalPrice by remember { mutableStateOf(0) }

    LaunchedEffect(fligh) {
        seatList.clear()
        seatList.addAll(generateSeatList(fligh))
        seatCount = selectedSeatNames.size
        totalPrice = seatCount * fligh.price.toInt()
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.darkPurple2))
    ){
        val (topSection, middleSection, bottomSection) = createRefs()


    }
}

fun generateSeatList(flight : FlightModel): List<Seat> {
    val seatList = mutableListOf<Seat>()
    val numberSeat = flight.numberSeat + (flight.numberSeat / 7) + 1
    val seatAlphabetMap = mapOf(
        0 to "A",
        1 to "B",
        2 to "C",
        4 to "D",
        5 to "E",
        6 to "F",
    )
    var row = 0
    for (i in 0  until numberSeat) {
        if (i % 7 == 0) {
            row++
        }
        if (i % 7 == 3){
            seatList.add(Seat(SeatStatus.EMPTY, row.toString() ))
        } else {
            val seatName = seatAlphabetMap[i % 7] + row
            val seatStatus = if (flight.reservedSeats.contains(seatName)){
                SeatStatus.UNVAILABLE
            } else {
                SeatStatus.AVAILABLE
            }
            seatList.add(Seat(seatStatus, seatName))
        }
    }
    return seatList

}