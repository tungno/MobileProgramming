package com.example.myapplication1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myapplication1.ui.theme.MyApplication1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication1Theme {
                // A surface container using the 'background' color from the theme
                Surface ( modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("World", "Tung")
                }
                /*
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                 */
            }
        }
    }
}

@Composable
fun Greeting(name: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.andriodparty)
    Column {
        Text(
            text = "Hello $name!",
            fontSize = 100.sp,
            lineHeight = 116.sp,
        )
        Text(
            text = from,
            fontSize = 36.sp,
        )
        Image(
            painter = image,
            contentDescription= null,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplication1Theme {
        Greeting("Android", "Preview")
    }
}