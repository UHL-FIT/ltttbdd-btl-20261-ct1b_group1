package com.example.dattuadulich

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

class SignInActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SignupScreen()
        }
    }
}
@Composable
fun signupAndPasswordField(
    label: String,
    value: String,
    onValueChange:(String) ->Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier= Modifier){
    TextField(
        label = {Text(text = label)},
        singleLine =true,
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}
@Composable
fun SignupButton(){
    Button(onClick = {}) {
        Text(text = stringResource(R.string.btn_dangky))
    }

}
@Composable
fun SignupScreen() {
    var tk by remember { mutableStateOf("") }
    var mk by remember { mutableStateOf("") }
    var remk by remember { mutableStateOf("") }
    Box{
        Image(
            painter = painterResource(R.drawable.background_app),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .blur(10.dp)
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        signupAndPasswordField(
            label = stringResource(R.string.txtf_login),
            value = tk,
            onValueChange ={tk = it},
            keyboardOptions = KeyboardOptions.Default
        )
        Spacer(modifier = Modifier.height(15.dp))
        signupAndPasswordField(
            label = stringResource(R.string.txtf_password),
            value = mk,
            onValueChange = {mk = it},
            keyboardOptions = KeyboardOptions.Default
        )
        Spacer(modifier = Modifier.height(15.dp))
        signupAndPasswordField(
            label = stringResource(R.string.txtf_repassword),
            value = remk,
            onValueChange = {remk = it},
            keyboardOptions = KeyboardOptions.Default
        )
        Spacer(modifier = Modifier.height(15.dp))
        SignupButton()

    }
}
@Preview(showBackground = true)
@Composable
fun SignupPreview(){
    SignupScreen()
}