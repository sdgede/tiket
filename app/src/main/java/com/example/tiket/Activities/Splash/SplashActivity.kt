package com.example.tiket.Activities.Splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.WindowCompat
import com.example.tiket.Activities.Dashboard.DashboardActivity
import com.example.tiket.MainActivity
import com.example.tiket.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()

        setContent {
            SplashScreen(onGetstartedClick = {
                startActivity(Intent(this, DashboardActivity::class.java))
            })
        }
    }
}

@Composable
@Preview
fun SplashScreen(onGetstartedClick: () -> Unit = {}) {
    StatusTopBarColor()

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (backgroundImg, title, subtitle, startbtn) = createRefs()

        // Background image full screen
        Image(
            painter = painterResource(R.drawable.splash_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(backgroundImg) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxSize()
        )

        val styledText = buildAnnotatedString {
            append("Discover your\nDream")
            withStyle(style = SpanStyle(color = colorResource(R.color.orange))) {
                append(" Flight")
            }
            append("\nEasily")
        }

        Text(
            text = styledText,
            fontSize = 54.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(top = 60.dp)
                .padding(horizontal = 16.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = stringResource(R.string.subtitle_splash),
            fontSize = 16.sp,
            color = colorResource(R.color.orange),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .constrainAs(subtitle) {
                    top.linkTo(title.bottom)
                    start.linkTo(title.start)
                }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 40.dp)
                .constrainAs(startbtn) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            GradientButton(onClick = onGetstartedClick, "Get Started", 32)
        }
    }
}

@Composable
fun StatusTopBarColor() {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }
}