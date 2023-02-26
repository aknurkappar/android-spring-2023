package com.example.roomdatabse2.screens

import androidx.compose.animation.core.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomdatabse2.R

@Composable
fun WelcomePage(navigateToRegisterNow: () -> Unit, navigateToLogIn : () -> Unit){

    val myBlueColor = Color(0xFF009688)
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Column(
            modifier = Modifier.height(340 .dp),
            verticalArrangement = Arrangement.SpaceEvenly,
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

            Box(modifier = Modifier.height(80 .dp)){
                Image(painter = painterResource(R.drawable.bird_hi),
                    contentDescription = "logo", modifier = Modifier
                        .height(80 .dp).rotate(imageAngle))

            }

            Text(text = stringResource(R.string.welcome), fontSize = 40.sp)

            Button(
                onClick = navigateToRegisterNow,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = myBlueColor,
                ),
                modifier = Modifier.width(281.dp)
            ) {
                Text(text = stringResource(R.string.register), color = Color.White)
            }

            Text(text = stringResource(R.string.have_account))
            Button(
                onClick = navigateToLogIn,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = myBlueColor,
                ),
                modifier = Modifier.width(281.dp)
            ) {
                Text(text = stringResource(R.string.login), color = Color.White)
            }
        }
    }
}