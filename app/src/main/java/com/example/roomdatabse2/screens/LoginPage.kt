package com.example.roomdatabse2.screens


import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.example.roomdatabse2.R
import com.example.roomdatabse2.data.User
import com.example.roomdatabse2.data.UserViewModel

@Composable
fun LoginPage(navigateBack : () -> Unit, navigateToStartPage : () -> Unit, usersData : List<User>) {

    var enteredEmail = remember { mutableStateOf(TextFieldValue()) }
    var enteredPassword = remember { mutableStateOf(TextFieldValue()) }

    var context = LocalContext.current
    val myBlueColor = Color(0xFF009688)

    fun checkEmailAndPasswordValue(enteredEmailValue : String, enteredPasswordValue : String): Boolean {
//        for(user in usersData){
//            if(user.email == enteredEmailValue && user.password == enteredPasswordValue)
//                return true
//        }
        return false
    }

    fun verifyAccount(enteredEmailValue : String, enteredPasswordValue : String){
        if(enteredEmailValue.isEmpty() || enteredPasswordValue.isEmpty()){
            Toast.makeText(context, context.getText(R.string.enter_email_and_password) , Toast.LENGTH_LONG).show()
        } else if(!checkEmailAndPasswordValue(enteredEmailValue, enteredPasswordValue)){
            Toast.makeText(context, context.getText(R.string.incorrect_email_or_password), Toast.LENGTH_LONG).show()
        } else {
            navigateToStartPage()
        }
    }

    Button(onClick = navigateBack,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = myBlueColor
        ),
        modifier = Modifier.padding(30 .dp).width(40 .dp)
    ) {
        Text(text = "<", color = Color.White)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {

        Text(text = stringResource(R.string.welcome_back),
            fontSize = 17 .sp,
            fontWeight = FontWeight.Bold,
            color = myBlueColor,
            fontFamily = FontFamily.SansSerif
        )

        Column(
            modifier = Modifier
                .padding(30 .dp)
                .border(width = 3.dp, color = Color.LightGray, shape = RoundedCornerShape(5))
                .width(320 .dp)
                .height(270 .dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,

            ) {

            OutlinedTextField(
                value = enteredEmail.value,
                onValueChange = {enteredEmail.value = it},
                label = {
                    Text(text = stringResource(R.string.email))
                },
                singleLine = true,
                leadingIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.AccountBox, contentDescription = stringResource(R.string.email))
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
            )

            OutlinedTextField(
                value = enteredPassword.value,
                onValueChange = {enteredPassword.value = it },
                label = {
                    Text(text = stringResource(R.string.password))
                },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    IconButton(onClick = { /*TO DO*/ }) {
                        Icon(imageVector = Icons.Filled.Lock, contentDescription = stringResource(R.string.password))
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
            )
            Button(onClick = { verifyAccount(enteredEmail.value.text, enteredPassword.value.text) },
                colors = ButtonDefaults.buttonColors(backgroundColor = myBlueColor),
                modifier = Modifier.width(281 .dp)
            ) {
                Text(text = stringResource(R.string.login), color = Color.White)
            }
        }
    }
}
