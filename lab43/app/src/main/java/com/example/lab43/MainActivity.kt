package com.example.lab43

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MrPotatoHeadApp()
        }
    }
}

@Composable
fun MrPotatoHeadApp() {
    // State to manage visibility of each body part
    var showHat by remember { mutableStateOf(false) }
    var showEyes by remember { mutableStateOf(false) }
    var showEyebrows by remember { mutableStateOf(false) }
    var showGlasses by remember { mutableStateOf(false) }
    var showNose by remember { mutableStateOf(false) }
    var showMouth by remember { mutableStateOf(false) }
    var showMustache by remember { mutableStateOf(false) }
    var showEars by remember { mutableStateOf(false) }
    var showArms by remember { mutableStateOf(false) }
    var showShoes by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Main body parts layout with stacking images
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp), // Adjust the height to fit all body parts
            contentAlignment = Alignment.TopCenter // Center the body parts vertically
        ) {
            // Body image
            Image(
                painter = painterResource(id = R.drawable.body),
                contentDescription = "Body",
                modifier = Modifier
                    .size(300.dp), // Adjust the size of the body
                contentScale = ContentScale.FillBounds
            )
            if (showHat) {
                Image(
                    painter = painterResource(id = R.drawable.hat),
                    contentDescription = "Hat",
                    //modifier = Modifier.fillMaxSize(), or we can use like this
                    modifier = Modifier
                        .size(120.dp) // Adjust the size of the hat
                        .offset(y = (-50).dp), // Position the hat above the body
                    contentScale = ContentScale.FillBounds
                )
            }
            if (showEyes) {
                Image(
                    painter = painterResource(id = R.drawable.eyes),
                    contentDescription = "Eyes",
                    modifier = Modifier
                        .size(70.dp) // Adjust the size of the eyes
                        .offset(y = (50).dp), // Position the eyes relative to the body
                    contentScale = ContentScale.FillBounds
                )
            }
            if (showEyebrows) {
                Image(
                    painter = painterResource(id = R.drawable.eyebrows),
                    contentDescription = "Eyebrows",
                    modifier = Modifier
                        .size(60.dp) // Adjust the size of the eyebrows
                        .offset(y = (-70).dp), // Position the eyebrows above the eyes
                    contentScale = ContentScale.FillBounds
                )
            }
            if (showGlasses) {
                Image(
                    painter = painterResource(id = R.drawable.glasses),
                    contentDescription = "Glasses",
                    modifier = Modifier
                        .size(100.dp) // Adjust the size of the glasses
                        .offset(y = (50).dp), // Position the glasses over the eyes
                    contentScale = ContentScale.FillBounds
                )
            }
            if (showNose) {
                Image(
                    painter = painterResource(id = R.drawable.nose),
                    contentDescription = "Nose",
                    modifier = Modifier
                        .size(40.dp) // Adjust the size of the nose
                        .offset(y = (-30).dp), // Position the nose
                    contentScale = ContentScale.FillBounds
                )
            }
            if (showMouth) {
                Image(
                    painter = painterResource(id = R.drawable.mouth),
                    contentDescription = "Mouth",
                    modifier = Modifier
                        .size(50.dp) // Adjust the size of the mouth
                        .offset(y = (0).dp), // Position the mouth
                    contentScale = ContentScale.FillBounds
                )
            }
            if (showMustache) {
                Image(
                    painter = painterResource(id = R.drawable.moustache),
                    contentDescription = "Mustache",
                    modifier = Modifier
                        .size(60.dp) // Adjust the size of the mustache
                        .offset(y = (-10).dp), // Position the mustache
                    contentScale = ContentScale.FillBounds
                )
            }
            if (showEars) {
                Image(
                    painter = painterResource(id = R.drawable.ears),
                    contentDescription = "Ears",
                    modifier = Modifier
                        .size(50.dp) // Adjust the size of the ears
                        .offset(x = (-80).dp, y = (-20).dp), // Position the ears
                    contentScale = ContentScale.FillBounds
                )
            }
            if (showArms) {
                Image(
                    painter = painterResource(id = R.drawable.arms),
                    contentDescription = "Arms",
                    modifier = Modifier
                        .size(150.dp) // Adjust the size of the arms
                        .offset(y = (50).dp), // Position the arms
                    contentScale = ContentScale.FillBounds
                )
            }
            if (showShoes) {
                Image(
                    painter = painterResource(id = R.drawable.shoes),
                    contentDescription = "Shoes",
                    modifier = Modifier
                        .size(100.dp) // Adjust the size of the shoes
                        .offset(y = (130).dp), // Position the shoes at the bottom
                    contentScale = ContentScale.FillBounds
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Checkbox grid to control visibility of body parts
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // 3 checkboxes per row
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item { CheckboxWithLabel("Hat", showHat) { showHat = it } }
            item { CheckboxWithLabel("Eyes", showEyes) { showEyes = it } }
            item { CheckboxWithLabel("Eyebrows", showEyebrows) { showEyebrows = it } }
            item { CheckboxWithLabel("Glasses", showGlasses) { showGlasses = it } }
            item { CheckboxWithLabel("Nose", showNose) { showNose = it } }
            item { CheckboxWithLabel("Mouth", showMouth) { showMouth = it } }
            item { CheckboxWithLabel("Mustache", showMustache) { showMustache = it } }
            item { CheckboxWithLabel("Ears", showEars) { showEars = it } }
            item { CheckboxWithLabel("Arms", showArms) { showArms = it } }
            item { CheckboxWithLabel("Shoes", showShoes) { showShoes = it } }
        }
    }
}

@Composable
fun CheckboxWithLabel(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(text = label)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMrPotatoHeadApp() {
    MrPotatoHeadApp()
}
