package com.example.hackproj

import QuizPage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hackproj.ui.theme.HackProjTheme
import kotlinx.coroutines.launch

class SignAndLogin : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackProjTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val coroutineScope = rememberCoroutineScope()
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "greeting",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("greeting") {
                                Greeting(navController)
                            }
                            composable("quiz") {
                                QuizPage(navController)
                            }
                            composable("home") {
                                Home(navController)
                            }
                            composable("prepare") {
                                PreparationScreen(navController)
                            }
                        }
                    }
                }

        }
    }
}

@Composable
fun Greeting(navController: NavController) {

    var selectedSection by remember { mutableStateOf("Sign Up") }

    Box(modifier=Modifier.background(color=Color.White)){
    Column(
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp, top = 10.dp)
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
            Row(modifier = Modifier.padding(start = 0.dp)) {

                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Edtech",
                    style = TextStyle(fontSize = 30.sp, color = Color.Blue, fontWeight = FontWeight.Bold)
                )
            }
        }

        Text(
            text = "Welcome to Edtech!",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = "Join us today and dive into a world of connection! Sign up to start your journey, or log in to keep exploring with friends.",
            style = TextStyle(color = Color.Gray, fontSize = 14.sp)
        )
        Spacer(modifier = Modifier.height(7.dp))


        CustomAppBar(selectedSection = selectedSection, onSectionSelected = { selectedSection = it })

        Spacer(modifier = Modifier.height(7.dp))

        if (selectedSection == "Sign Up") {
            Signup(navController)
        } else {
            Login(navController)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .padding(3.dp),
                shape = RoundedCornerShape(30.dp),
                border = BorderStroke(width = 1.dp, color = Color.Gray),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.google),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = " Google")
                }
            }
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .padding(3.dp),
                shape = RoundedCornerShape(30.dp),
                border = BorderStroke(width = 1.dp, color = Color.Gray),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.facebook),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = " Facebook")
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 15.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = "By using this app, you agree to our terms and conditions and acknowledge that you have read and understood them",
                style = TextStyle(color = Color.Gray)
            )
        }
    }
    }
}
@Composable
fun Signup(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFE2F3EA), Color(0xFFCDA2E0))
    )
    Text(
        text = "Name",
        style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(start = 10.dp, top = 8.dp, bottom = 4.dp)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, Color.Gray),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            TextField(
                value = name,
                onValueChange = { newText -> name = newText },
                placeholder = { Text("Enter your Name") },
                textStyle = TextStyle(fontSize = 15.sp),
                modifier = Modifier
                    .fillMaxSize(),
                colors = TextFieldDefaults.colors(
                    unfocusedLabelColor = Color.Gray,
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
    Text(
        text = "Email",
        style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(start = 10.dp, top = 8.dp, bottom = 4.dp)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, Color.Gray),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            TextField(
                value = email,
                onValueChange = { newText -> email = newText },
                placeholder = { Text("Enter your email") },
                textStyle = TextStyle(fontSize = 15.sp),
                modifier = Modifier
                    .fillMaxSize(),
                colors = TextFieldDefaults.colors(
                    unfocusedLabelColor = Color.Gray,
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
    Text(
        text = "Password",
        style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(start = 10.dp, top = 8.dp, bottom = 4.dp)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, Color.Gray),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            TextField(
                value = password,
                onValueChange = { newText -> password = newText },
                placeholder = { Text("Enter your password") },
                textStyle = TextStyle(fontSize = 15.sp),
                modifier = Modifier
                    .fillMaxSize(), // Padding inside the card
                colors = TextFieldDefaults.colors(
                    unfocusedLabelColor = Color.Gray,
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.White, // Removes underline
                    focusedIndicatorColor = Color.Transparent // Removes underline
                )
            )
        }
    }
    Spacer(Modifier.height(20.dp))
    ElevatedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 10.dp, end = 10.dp)
            .background(color = Color.Blue,RoundedCornerShape(30.dp)),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
        onClick = {navController.navigate("home")}
    ) {
        Text(text = "Sign up", style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold))
    }

    Spacer(Modifier.height(20.dp))
    Row (verticalAlignment = Alignment.CenterVertically){
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .weight(1f)
            .background(color = Color.Gray)){}
        Text(text = "Or Sign up with", style = TextStyle(color = Color.Gray))
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .weight(1f)
            .background(color = Color.Gray)){}
    }
}
@Composable
fun Login(navController: NavController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Text(
        text = "Email",
        style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(start = 10.dp, top = 8.dp, bottom = 4.dp)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, Color.Gray),  // Gray outline for the Card
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Background color for the Card
        )
    ) {
        TextField(
            value = email,
            onValueChange = { newText -> email = newText },
            label = { Text("Enter your Email") },
            modifier = Modifier
                .fillMaxSize(), // Padding inside the card
            colors = TextFieldDefaults.colors(
                unfocusedLabelColor = Color.Gray,
                unfocusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.White, // Removes underline
                focusedIndicatorColor = Color.Transparent // Removes underline
            )
        )
    }
    Text(
        text = "Password",
        style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(start = 10.dp, top = 8.dp, bottom = 4.dp)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, Color.Gray),  // Gray outline for the Card
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Background color for the Card
        )
    ) {
        TextField(
            value = password,
            onValueChange = { newText -> password = newText },
            label = { Text("Enter your password") },
            modifier = Modifier
                .fillMaxSize(),
            colors = TextFieldDefaults.colors(
                unfocusedLabelColor = Color.Gray,
                unfocusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                focusedIndicatorColor = Color.Transparent
            )
        )
    }
    Spacer(Modifier.height(20.dp))
    ElevatedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 10.dp, end = 10.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 10.dp),
        onClick = {navController.navigate("home")}
    ) {
        Text(text = "Login", style = TextStyle(fontSize = 17.sp))
    }
    Spacer(Modifier.height(20.dp))
    Row (verticalAlignment = Alignment.CenterVertically){
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .weight(1f)
            .background(color = Color.Gray)){}
        Text(text = "Or login with", style = TextStyle(color = Color.Gray))
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .weight(1f)
            .background(color = Color.Gray)){}
    }
}
@Composable
fun CustomAppBar(selectedSection: String, onSectionSelected: (String) -> Unit) {
    val activeColor = Color.White
    val backgroundColor = Color(0xFFF0F0F0)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(20.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(color = if (selectedSection == "Sign Up") activeColor else Color.Transparent)
                .clickable { onSectionSelected("Sign Up") }
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(color = if (selectedSection == "Log In") activeColor else Color.Transparent)
                .clickable { onSectionSelected("Log In") }
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Log In",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPrevie() {
    HackProjTheme {
        val navController = rememberNavController()
        Greeting(navController )
    }
}



