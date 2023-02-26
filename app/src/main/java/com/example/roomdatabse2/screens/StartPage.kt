package com.example.roomdatabse2.screens

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.roomdatabse2.R
import com.example.roomdatabse2.data.User
import com.example.roomdatabse2.data.UserViewModel

@Composable
fun StartPage(navigateBack: () -> Unit){
    val myBlueColor = Color(0xFF009688)

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Column(
            modifier = Modifier.height(340 .dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            //            Animation
            val repeatableAnimation = rememberInfiniteTransition()
            val imageAngle by repeatableAnimation.animateFloat(
                initialValue = 10F,
                targetValue = -7F,
                animationSpec = infiniteRepeatable(
                    animation = tween(2000)
                )
            )

            Column(modifier = Modifier.height(120 .dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Image(painter = painterResource(R.drawable.bird_happy),
                    contentDescription = "logo",
                    modifier = Modifier
                        .height(80.dp)
                        .rotate(imageAngle))
                Text(text = stringResource(R.string.start_page_ready),
                    color = myBlueColor,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.height(20 .dp)
                )
//                Text(text = usersData.toString())
            }

            Button(onClick = navigateBack,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = myBlueColor,
                )) {
                Text(text = stringResource(R.string.logout), color = Color.White)
            }
        }
    }
}