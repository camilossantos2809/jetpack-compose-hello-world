package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helloworld.ui.theme.HelloWorldTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TestList()
                }
            }
        }
    }
}

@Composable
fun Alert(show: MutableState<Boolean>,text:String?){
    if(show.value) {
        AlertDialog(onDismissRequest = {
                                       show.value=false
        },
            title = { Text("Title") },
            text = { Text(text ?: "Message" ) },
            confirmButton = {
                Button(
                    onClick = { show.value=false }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = { show.value=false }) {
                    Text("Cancel")
                }
            })
    }
}

val PADDING = 16.dp
val IMAGE_HEIGHT = 180.dp

@Composable
fun NewsStory() {
    val typography = MaterialTheme.typography
    val showAlert = remember{ mutableStateOf(false)}
    val text = "A day in Shark Fin Cove"
    Alert(show = showAlert, text = text)
    Column(modifier = Modifier
        .padding(PADDING)
        .clickable { showAlert.value = !showAlert.value }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.header),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(IMAGE_HEIGHT)
                .clip(shape = RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.height(PADDING))
        Text(text, style = typography.h6)
        Text("Davenport, California")
        Text("December 2018")
    }
}

@Composable
fun TestList() {
    LazyColumn {
        items(5) {
            NewsStory()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloWorldTheme {
        TestList()
    }
}