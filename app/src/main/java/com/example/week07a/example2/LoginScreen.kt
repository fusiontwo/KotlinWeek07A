package com.example.week07a.example2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController: NavHostController) {

    val userid = "greenjoa"
    val userpasswd = "1234"

    var userID by remember {
        mutableStateOf("")
    }

    var userPasswd by remember {
        mutableStateOf("")
    }

    var loginresult = (userid==userID && userpasswd == userPasswd)

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {


        Text(text="Login Screen",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold)

        OutlinedTextField(value = userID,
            onValueChange = {userID =it},
            label = {Text("아이디")}
        )

        OutlinedTextField( value = userPasswd,
            onValueChange = { userPasswd = it },
            label = { Text("Enter password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(onClick = {
            if(loginresult)
                if(userID.isBlank())
                    navController.navigate(Routes.Welcome.route)
                else
                    navController.navigate(Routes.Welcome.route+"?userID=$userID")
            else
                if(!userID.isBlank())
                    navController.navigate(Routes.Register.route+"?userID=$userID&userpasswd=$userPasswd")  // BackstackEntry에 저장됨.
                else
                    navController.navigate(Routes.Register.route)
        }){
            Text(text = "로그인")
        }
    }
}