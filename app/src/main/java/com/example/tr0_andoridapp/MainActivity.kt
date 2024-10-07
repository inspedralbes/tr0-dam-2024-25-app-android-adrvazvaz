package com.example.tr0_andoridapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tr0_andoridapp.ui.theme.Tr0_AndoridAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tr0_AndoridAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("al Quizz!")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente
    ) {
        // Texto centrado en la parte superior
        Text(
            text = "Benvingut $name!",
            modifier = Modifier.padding(top = 200.dp)
        )

        // Espaciado entre el texto y el botón
        Spacer(modifier = Modifier.height(20.dp))

        // Botón de tamaño normal
        Button(onClick = {
            // Acción del botón
        }) {
            Text(text = "Comença el Quizz")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Tr0_AndoridAppTheme {
        Greeting("al Quizz")
    }
}
