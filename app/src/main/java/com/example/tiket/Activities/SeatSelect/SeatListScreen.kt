package com.example.tiket.Activities.SeatSelect

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tiket.Domain.FlightModel
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.os.unregisterForAllProfilingResults
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

    fun updatePriceAndCount(){
        seatCount = selectedSeatNames.size
        totalPrice = seatCount * fligh.price.toInt()
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.darkPurple2))
    ){
        val (topSection, middleSection, bottomSection) = createRefs()
        TopSelection(
            modifier = Modifier
                .constrainAs(topSection){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onBackClick = onBackClick
        )
        ConstraintLayout(
            modifier = Modifier
                .padding(top = 100.dp)
                .constrainAs(middleSection) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ){
            val (airplane, seatGrid) = createRefs()
            Image(
                painter = painterResource(R.drawable.airple_seat),
                contentDescription = null,
                modifier = Modifier.constrainAs(airplane){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier
                    .padding(top = 240.dp)
                    .padding(horizontal = 64.dp)
                    .constrainAs(seatGrid) {
                        top.linkTo(parent.top)
                        start.linkTo(airplane.start)
                        end.linkTo(airplane.end)
                    }
            ) {
                items(seatList.size){index ->
                    val seat = seatList[index]
                    SeatItem(
                        seat=seat,
                        onSeatClick = {
                            when(seat.status){
                                SeatStatus.AVAILABLE -> {
                                    seat.status = SeatStatus.SELECTED
                                    selectedSeatNames.add(seat.name)

                                }
                                SeatStatus.SELECTED -> {
                                    seat.status = SeatStatus.AVAILABLE
                                    selectedSeatNames.remove(seat.name)
                                }
                                else ->{
                                    updatePriceAndCount()
                                }
                            }
                        }
                    )
                }
            }
        }
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