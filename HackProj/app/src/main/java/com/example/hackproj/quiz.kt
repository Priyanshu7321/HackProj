import android.os.CountDownTimer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch

data class Question(val question: String, val options: List<String>)

@Composable
fun QuizPage(navController: NavController) {
    var question by remember { mutableStateOf("") }
    var options by remember { mutableStateOf(listOf<String>()) }
    var timerValue by remember { mutableStateOf(10 * 60 * 1000L) } // 10 minutes in milliseconds
    var currentQuestionIndex by remember { mutableStateOf(1) }
    val coroutineScope = rememberCoroutineScope()

    val prompt =
        "Create an MCQ question for the GATE exam of Computer Science in the Data Structures topic. Do not create a heading, only return a question and its options."

    LaunchedEffect(currentQuestionIndex) {
        val fetchedQuestion = fetchMcqQuestion(prompt)
        if (fetchedQuestion != null) {
            question = fetchedQuestion.question
            options = fetchedQuestion.options
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = formatTime(timerValue),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Question: $currentQuestionIndex",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = question,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            options.forEach { option ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.elevatedCardElevation(1.dp)
                ) {
                    Text(
                        text = option,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.navigate("home") },
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text("End Test")
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        currentQuestionIndex++
                        val fetchedQuestion = fetchMcqQuestion(prompt)
                        if (fetchedQuestion != null) {
                            question = fetchedQuestion.question
                            options = fetchedQuestion.options
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text("Next")
            }
        }
    }

    // Updated Timer
    Timer(countdownTime = timerValue) { timeRemaining ->
        timerValue = timeRemaining
        if (timeRemaining <= 0) {
            navController.navigate("home") // Navigate to Home on timer end
        }
    }
}


@Composable
fun Timer(countdownTime: Long, onTimeUpdate: (Long) -> Unit) {
    var timeLeft by remember { mutableStateOf(countdownTime) }
    val currentTimeLeft by rememberUpdatedState(timeLeft)

    LaunchedEffect(Unit) {
        while (currentTimeLeft > 0) {
            kotlinx.coroutines.delay(1000)
            timeLeft -= 1000
            onTimeUpdate(timeLeft)
        }
        onTimeUpdate(0) // Ensure timer ends cleanly
    }
}


// Format time in mm:ss
fun formatTime(milliseconds: Long): String {
    val minutes = (milliseconds / 1000) / 60
    val seconds = (milliseconds / 1000) % 60
    return "%02d:%02d".format(minutes, seconds)
}

// Fetch MCQ Question using Generative AI API
suspend fun fetchMcqQuestion(prompt: String): Question? {
    val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = "my_api_key"
    )
    val response = generativeModel.generateContent(prompt)
    val lines = response.text?.split("\n") ?: return null

    // Extract question text
    val questionText = lines.firstOrNull()?.trim() ?: "No question found"

    // Extract options, limit to 4
    val options = lines.drop(1).map { it.trim() }.filter { it.isNotBlank() }.take(4)

    // Return Question object only if we have exactly 4 options
    return if (options.size == 4) {
        Question(questionText, options)
    } else {
        null // Handle cases where the response doesn't meet the requirements
    }
}

// Home Composable
@Composable
fun Home() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Home!")
    }
}

// Navigation Preview
@Preview(showBackground = true)
@Composable
fun QuizPagePreview() {
    val navController = rememberNavController()
    QuizPage(navController = navController)
}
