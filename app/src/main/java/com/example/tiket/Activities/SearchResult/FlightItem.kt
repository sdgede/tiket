package com.example.tiket.Activities.SearchResult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.tiket.Domain.FlightModel
import com.example.tiket.R

@Composable
fun FlightItem(item: FlightModel, index: Int){
    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable{

            }
            .background(
                color = colorResource(R.color.lightPurple),
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        val (logo, timeTrx, airplaneIcon, dashLine, priceTxt, seatIcon, classTxt, fromTxt, fromShortTxt, toText, toShortTxt) = createRefs()
        AsyncImage(
            model = item.airlineLogo,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp, 50.dp)
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

        )
        Text(
            text = item.arriveTime,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = colorResource(R.color.darkPurple2),
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(timeTrx) {
                    start.linkTo(parent.start)
                    top.linkTo(logo.bottom)
                    end.linkTo(parent.end)

                }
        )
        Image(
            painter = painterResource(R.drawable.line_airple_blue),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(airplaneIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(timeTrx.bottom)
                    end.linkTo(parent.end)
                }
        )
        Image(
            painter = painterResource(R.drawable.dash_line),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(dashLine) {
                    start.linkTo(parent.start)
                    top.linkTo(airplaneIcon.bottom)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = "$ ${item.price}",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = colorResource(R.color.orange),
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(priceTxt) {
                    top.linkTo(dashLine.bottom)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }

        )
        Image(
            painter = painterResource(R.drawable.seat_black_ic),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(seatIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(dashLine.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(
            text = item.classSeat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = colorResource(R.color.darkPurple2),
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(classTxt) {
                    start.linkTo(seatIcon.end)
                    top.linkTo(seatIcon.top)
                    bottom.linkTo(seatIcon.bottom)

                }
        )
        Text(
            text = item.from,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(fromTxt) {
                    top.linkTo(timeTrx.bottom)
                    start.linkTo(parent.start)


               }
        )
        Text(
            text = item.fromShort,
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(fromShortTxt) {
                    top.linkTo(fromTxt.bottom)
                    start.linkTo(fromTxt.start)
                    end.linkTo(fromTxt.end)
                }
        )
        Text(
            text = item.to,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(end = 16.dp)
                .constrainAs(toText) {
                    top.linkTo(timeTrx.bottom)
                    end.linkTo(parent.end)

                }
        )
        Text(
            text = item.toShort,
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(end = 16.dp)
                .constrainAs(toShortTxt) {
                    top.linkTo(toText.bottom)
                    start.linkTo(toText.start)
                    end.linkTo(toText.end)
                }
        )
    }
}