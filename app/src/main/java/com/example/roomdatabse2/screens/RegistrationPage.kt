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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.example.roomdatabse2.R
import com.example.roomdatabse2.data.User
import com.example.roomdatabse2.data.UserViewModel

@Composable
fun RegistrationPage(navigateBack : () -> Unit, navigateToStartPage : () -> Unit, usersData : List<User>,  myUserViewModel : UserViewModel) {
    var enteredAccountName = remember { mutableStateOf(TextFieldValue()) }
    var enteredPassword = remember { mutableStateOf(TextFieldValue()) }
    var enteredPasswordSecond = remember { mutableStateOf(TextFieldValue()) }

    var context = LocalContext.current
    val myBlueColor = Color(0xFF009688)

    fun checkEmailValue(enteredValue : String): Boolean {
        for(user in usersData){
            if(user.email == enteredValue)
                return true
        }
        return false
    }

    fun validateAccount(enteredEmailValue : String, enteredPasswordValue : String, enteredPasswordSecondValue : String){
        if(enteredEmailValue.isEmpty() || enteredPasswordValue.isEmpty()){
            Toast.makeText(context, context.getText(R.string.enter_email_and_password), Toast.LENGTH_LONG).show()
        } else if(checkEmailValue(enteredEmailValue)){
            Toast.makeText(context, context.getText(R.string.already_registered_email), Toast.LENGTH_LONG).show()
        } else if(enteredPasswordValue != enteredPasswordSecondValue) {
            Toast.makeText(context, context.getText(R.string.passwords_do_not_match), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, context.getText(R.string.successful_registration), Toast.LENGTH_LONG).show()
//            updateUsersData(User(enteredEmailValue, enteredPasswordValue))
//            myUserViewModel.addUser(User(enteredEmailValue, enteredEmailValue, enteredPasswordValue))
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

        Column(
            modifier = Modifier
                .padding(20.dp)
                .border(width = 3.dp, color = Color.LightGray, shape = RoundedCornerShape(5))
                .width(320.dp)
                .height(440.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,

            ) {

            Text(text = stringResource(R.string.join_now),
                fontSize = 15 .sp,
                fontWeight = FontWeight.Bold,
                color = myBlueColor,
                modifier = Modifier.padding(10 .dp),
                fontFamily = FontFamily.SansSerif
            )

            OutlinedTextField(
                value = enteredAccountName.value,
                onValueChange = {enteredAccountName.value = it},
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
                    imeAction = ImeAction.Done
                ),
            )

            OutlinedTextField(
                value = enteredPassword.value,
                onValueChange = { enteredPassword.value = it },
                label = {
                    Text(text = stringResource(R.string.password))
                },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Lock, contentDescription = stringResource(R.string.password))
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
            )
            OutlinedTextField(
                value = enteredPasswordSecond.value,
                onValueChange = {enteredPasswordSecond.value = it},
                label = {
                    Text(text = stringResource(R.string.password_again))
                },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Lock, contentDescription = stringResource(R.string.password_again))
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
            )

            Button(onClick = { validateAccount(enteredAccountName.value.text, enteredPassword.value.text, enteredPasswordSecond.value.text ) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = myBlueColor,
                ),
                modifier = Modifier.width(281 .dp)
            ) {
                Text(text = stringResource(R.string.confirm_registration), color = Color.White)
            }
        }
    }
}