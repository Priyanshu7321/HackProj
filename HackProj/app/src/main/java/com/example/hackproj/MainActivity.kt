package com.example.hackproj

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hackproj.ui.theme.HackProjTheme



@SuppressLint("ResourceType")
@Composable
fun Home(navController: NavController) {
    val selectedIndex = remember { mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            BottomNavBar(
                selectedIndex = selectedIndex.value,
                onItemSelected = { index -> selectedIndex.value = index }
            )
        }
    ) { paddingValues ->
        when (selectedIndex.value) {
            0 -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(WindowInsets.systemBars.asPaddingValues())
                    .padding(start = 10.dp, end = 10.dp, top = 5.dp)
            ) {
                Row(modifier = Modifier
                    .padding(start = 5.dp)
                    .height(60.dp)) {
                    Image(
                        painter = painterResource(R.drawable.boy),
                        contentDescription = "",
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(modifier=Modifier.size(10.dp))
                    Column {
                        Text("Welcome back", style = TextStyle(color = Color.Gray))
                        Text("Priyanshu", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Card(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.loupe),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(5.dp))
                }
                Card (modifier= Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFBBDEFB)
                    )
                ){
                    Box (modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)){
                        Row {
                            Column (modifier = Modifier.weight(1f).fillMaxHeight(), verticalArrangement = Arrangement.Center){
                                Text("Let's Practice with quiz", style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold), )
                                ElevatedButton(modifier = Modifier.width(100.dp), onClick = {navController.navigate("quiz") } , ) { Text("Start") }
                            }
                            Image(
                                painter = painterResource(R.drawable.trophy),
                                contentDescription = "",
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                Text("Featured Categories", style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 25.sp))
                MasterEntranceCategories()
                Text("Recent Result", style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 25.sp))
                CategoryResults()
            }
            1 -> LeaderboardScreen(paddingValues)
        }
    }

}
@Composable
fun MasterEntranceCategories() {
    val categories = listOf(
        Category("Mathematics", R.drawable.calculating),
        Category("Quantitative aptitude", R.drawable.quantitative),
        Category("Verbal Ability", R.drawable.reading),
        Category("Logical Reasoning", R.drawable.brain)
    )

    Column(
        modifier = Modifier
            .height(120.dp)
            .padding(16.dp)
    ) {

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories.size) { index ->
                CategoryCard(category = categories[index])
            }
        }
    }
}

@Composable
fun CategoryCard(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .size(60.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(category.iconRes),
                    contentDescription = category.name,
                    modifier = Modifier.size(35.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category.name,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}

data class Category(val name: String, val iconRes: Int)
@Composable
fun CategoryResults() {
    val categoryResults = listOf(
        CategoryResult("Mathematics", 85, 40, 35, "Hard","60 min"),
        CategoryResult("Physics", 75, 50, 45, "Moderate","120 min"),
        CategoryResult("Chemistry", 90, 60, 55, "Easy","70 min"),
        CategoryResult("Biology", 65, 30, 20, "Moderate","50 min")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categoryResults.size) { index ->
            ResultCard(categoryResult = categoryResults[index])
        }
    }
}

@Composable
fun ResultCard(categoryResult: CategoryResult) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularProgressBar(
                    progress = categoryResult.score / 100f,
                    score = categoryResult.score
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = categoryResult.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Attempted: ${categoryResult.attemptedQuestions}",
                            fontSize = 12.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = "Answered: ${categoryResult.answeredQuestions}",
                            fontSize = 12.sp,
                            color = Color.DarkGray
                        )

                    }
                    Text(
                        text = "Difficulty: ${categoryResult.difficultyLevel}",
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Time: ${categoryResult.time}",
                            fontSize = 12.sp,
                            color = Color.DarkGray
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(12.dp))



        }
    }
}

@Composable
fun CircularProgressBar(progress: Float, score: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(80.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {

            drawArc(
                color = Color.LightGray,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round)
            )
            // Foreground progress
            drawArc(
                color = Color(0xFF64B5F6),
                startAngle = -90f,
                sweepAngle = progress * 360f,
                useCenter = false,
                style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        // Score in the center
        Text(
            text = "$score%",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

data class CategoryResult(
    val name: String,
    val score: Int,
    val attemptedQuestions: Int,
    val answeredQuestions: Int,
    val difficultyLevel: String,
    val time:String
)
@Composable
fun BottomNavBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = selectedIndex == 0,
            onClick = { onItemSelected(0) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Menu, contentDescription = "Leaderboards") },
            label = { Text("Leaderboards") },
            selected = selectedIndex == 1,
            onClick = { onItemSelected(1) }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    HackProjTheme {
        var navController:NavController= rememberNavController()
        Home(navController)
    }
}