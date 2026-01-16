package com.example.tiket.Activities.TiketDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
fun TiketDetailContent(
    flight: FlightModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(24.dp)
            .background(
                shape = RoundedCornerShape(20.dp),
                color = colorResource(R.color.lightPurple)
            )
            .padding(16.dp)
    ) {
        // Header with logo and flight route
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            val (logo, arrivalText, lineImg, fromTxt, fromShortTxt, toTxt, toShortTxt) = createRefs()

            AsyncImage(
                model = flight.airlineLogo,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp, 50.dp)
                    .constrainAs(logo) {
                        top.linkTo(parent.top, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Fit
            )

            Text(
                text = flight.time,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.darkPurple),
                modifier = Modifier
                    .constrainAs(arrivalText) {
                        top.linkTo(logo.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Image(
                painter = painterResource(R.drawable.line_airple_blue),
                contentDescription = null,
                modifier = Modifier.constrainAs(lineImg) {
                    top.linkTo(arrivalText.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Text(
                text = "from",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.darkPurple),
                modifier = Modifier
                    .constrainAs(fromTxt) {
                        bottom.linkTo(lineImg.top)
                        start.linkTo(parent.start)
                        end.linkTo(lineImg.start)
                    }
            )

            Text(
                text = flight.fromShort,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .constrainAs(fromShortTxt) {
                        top.linkTo(lineImg.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(lineImg.start)
                    }
            )

            Text(
                text = "To",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.darkPurple),
                modifier = Modifier
                    .constrainAs(toTxt) {
                        bottom.linkTo(lineImg.top)
                        start.linkTo(lineImg.end)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = flight.toShort,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .constrainAs(toShortTxt) {
                        top.linkTo(lineImg.bottom)
                        start.linkTo(lineImg.end)
                        end.linkTo(parent.end)
                    }
            )
        }

        Spacer(Modifier.height(16.dp))

        // Flight details section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(text = "From", color = Color.Black)
                Text(text = flight.from, color = Color.Black, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                Text(text = "Date", color = Color.Black)
                Text(text = flight.date, color = Color.Black, fontWeight = FontWeight.Bold)
            }

            Column(
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(text = "To", color = Color.Black)
                Text(text = flight.to, color = Color.Black, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                Text(text = "Time", color = Color.Black)
                Text(text = flight.time, color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(Modifier.height(16.dp))

        Image(
            painter = painterResource(R.drawable.dash_line),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // Class and price section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(text = "Class", color = Color.Black)
                Text(text = flight.classSeat, color = Color.Black, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                Text(text = "Seats", color = Color.Black)
                Text(text = flight.reservedSeats, color = Color.Black, fontWeight = FontWeight.Bold)
            }

            Column(
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(text = "AirLines", color = Color.Black)
                Text(text = flight.airlineName, color = Color.Black, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                Text(text = "Price", color = Color.Black)
                Text(
                    text = "$${String.format("%.2f", flight.price)}",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Image(
            painter = painterResource(R.drawable.dash_line),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // QR Code
        Image(
            painter = painterResource(R.drawable.qrcode),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        Image(
            painter = painterResource(R.drawable.dash_line),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        // Barcode
        Image(
            painter = painterResource(R.drawable.barcode),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}