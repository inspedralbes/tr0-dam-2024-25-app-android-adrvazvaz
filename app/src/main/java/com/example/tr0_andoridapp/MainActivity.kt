package com.example.tr0_andoridapp

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
import com.example.tr0_andoridapp.ui.theme.green

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tr0_AndoridAppTheme {
                // Surface container usando el color 'background' del tema
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MyApp() // Llama a la función MyApp que manejará la navegación
                }
            }
        }

    }
}

// Configura la navegación
@Composable
fun MyApp() {
    val navController = rememberNavController() // Crea el NavController

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { Greeting(navController) } // Pantalla principal
        composable("quiz") { QuizScreen() } // Pantalla del quizz
    }
}

@Composable
fun Greeting(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texto centrado en la parte superior
        Text(
            text = "Benvingut al Quizz!",
            modifier = Modifier.padding(top = 200.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("quiz") // Navega a la pantalla del quizz
            },
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Start",
                color = green
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Tr0_AndoridAppTheme {
        MyApp()
    }
}
