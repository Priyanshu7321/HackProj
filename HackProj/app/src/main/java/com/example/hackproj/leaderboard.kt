package com.example.hackproj

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LeaderboardScreen(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp)
        ){
            TopThreeUsers()
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Leaderboard",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            UserList(
                users = listOf(
                    User("Alice", "Rank 4", R.drawable.boy),
                    User("Bob", "Rank 5", R.drawable.boy),
                    User("Charlie", "Rank 6", R.drawable.boy),
                    User("David", "Rank 7", R.drawable.boy),
                    User("Eve", "Rank 8", R.drawable.boy),
                    User("Charlie", "Rank 9", R.drawable.boy),
                    User("David", "Rank 10", R.drawable.boy),
                    User("Charlie", "Rank 11", R.drawable.boy),
                    User("David", "Rank 12", R.drawable.boy),
                    User("Eve", "Rank 13", R.drawable.boy),
                    User("Charlie", "Rank 14", R.drawable.boy),
                    User("David", "Rank 15", R.drawable.boy),
                )
            )
        }

    }
}

@Composable
fun TopThreeUsers() {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        val topUsers = listOf(
            User("User 1", "1st", R.drawable.boy),
            User("User 2", "2nd", R.drawable.boy),
            User("User 3", "3rd", R.drawable.boy)
        )

        topUsers.forEach { user ->
            CircularUserCard(user = user)
        }
    }
}

@Composable
fun CircularUserCard(user: User) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier.size(80.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA))
        ) {
            Image(
                painter = painterResource(id = user.profileImage),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = user.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = user.rank,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun UserList(users: List<User>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(users.size) { index ->
            UserRow(user = users[index])
        }
    }
}

@Composable
fun UserRow(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = user.profileImage),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = user.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = user.rank,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}

data class User(val name: String, val rank: String, val profileImage: Int)
