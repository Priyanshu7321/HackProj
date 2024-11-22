import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatBotPage()
        }
    }
}

@Composable
fun ChatBotPage() {
    var userInput by remember { mutableStateOf("") }
    var chatResponse by remember { mutableStateOf("Ask me anything!") }
    val coroutineScope = rememberCoroutineScope()

    // Function to handle API call to Gemini
    fun fetchChatbotResponse(prompt: String) {
        coroutineScope.launch {
            val generativeModel = GenerativeModel(
                modelName = "gemini-1.5-flash",
                apiKey = "YOUR_API_KEY" // Replace with your valid API key
            )

            // Make the API request to get a response from the chatbot
            val response = withContext(Dispatchers.IO) {
                try {
                    generativeModel.generateContent(prompt)
                } catch (e: Exception) {
                    "Error: Unable to get response"
                }
            }
            chatResponse = response
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Chatbot", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Input field for user query
        BasicTextField(
            value = userInput,
            onValueChange = { userInput = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to submit the user query
        Button(
            onClick = { fetchChatbotResponse(userInput) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Ask")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display chatbot's response
        Text(
            text = chatResponse,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatBotPage()
}

// Corrected GenerativeModel class
class GenerativeModel(
    private val modelName: String,
    private val apiKey: String
) {
    suspend fun generateContent(prompt: String): String {
        // You should call the actual API here to get the response from Gemini

        // For now, we return a mock response for the given prompt
        return "This is a response to: \"$prompt\""
    }
}
