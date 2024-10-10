package com.example.tr0_andoridapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
data class Pregunta(
    val id: Int,
    val pregunta: String,
    val respostes: List<Resposta>,
    val resposta_correcta: Boolean
)

data class Resposta(
    val id: Int,
    val res: String
)

fun getPreguntas(urlString: String): String {
    val url = try {
        URL(urlString)
    } catch (e: Exception) {
        throw IllegalArgumentException("URL no válida: $urlString", e)
    }

    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"

    return try {
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            connection.inputStream.bufferedReader().use { it.readText() }
        } else {
            "Error: ${connection.responseCode}"
        }
    } finally {
        connection.disconnect()
    }
}
fun getJson(url: String): Array<Pregunta> {
    val json = getPreguntas(url)
    val gson = Gson()
    return gson.fromJson(json, Array<Pregunta>::class.java)
}

fun main() {
    val url = "http://192.168.0.176:21211/getPreguntesAndoridApp"
    val json = getJson(url)
    println(json.contentToString())
}

@Composable
fun QuizScreen() {
    var preguntas by remember { mutableStateOf(emptyArray<Pregunta>()) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val url = "http://192.168.0.176:21211/getPreguntesAndoridApp"
        try {
            preguntas = withContext(Dispatchers.IO) { getJson(url) }
        } catch (e: Exception) {
            errorMessage = e.message ?: "Error desconocido"
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage)
        } else if (preguntas.isNotEmpty()) {
            val currentPregunta = preguntas[0]

            // Muestra la pregunta
            Text(
                text = currentPregunta.pregunta,
                modifier = Modifier.padding(15.dp)
            )

            // Muestra las respuestas
            currentPregunta.respostes.forEach { resposta ->
                // Solo mostramos respuestas válidas
                val respuestaTexto = resposta.res
                if (respuestaTexto.isNotBlank()) {
                    Text(
                        text = respuestaTexto,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                }
            }
        } else {
            Text(text = "Cargando preguntas...")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    QuizScreen() // Previsualiza la pantalla del quizz
}


