package com.example.hackproj

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PreparationScreen(navController: NavController) {
    var selectedOption by remember { mutableStateOf<String?>(null) }

    val options = listOf("GATE", "CAT", "GRE", "SAT")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "What are you preparing for?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 32.dp)
        )

        // Options
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            options.forEach { option ->
                OptionItem(option, isSelected = selectedOption == option) {
                    selectedOption = option
                }
            }
        }

        // Get Started Button
        Button(
            onClick = { navController.navigate("home") },
            enabled = selectedOption != null,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedOption != null) Color.Blue else Color.Gray,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            Text(text = "Get Started")
        }
    }
}

@Composable
fun OptionItem(option: String, isSelected: Boolean, onClick: () -> Unit) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(
                    color = if (isSelected) Color.DarkGray else Color.LightGray,
                    shape = CircleShape
                )
                .clickable { onClick() }
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = option,
                fontSize = 18.sp,
                color = if (isSelected) Color.Blue else Color.Black,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }

    Spacer(modifier = Modifier.height(10.dp))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun showUio(){
    var navController= rememberNavController()
    PreparationScreen(navController)
}