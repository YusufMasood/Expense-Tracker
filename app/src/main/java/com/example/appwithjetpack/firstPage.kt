// PaymentAppUI.kt
package com.example.appwithjetpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreHoriz // Keep this import if used elsewhere, but not for PayPal card
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext // Still needed for general context if not for navigation
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val LightGrayBackground = Color(0xFFF2F4F7)
val DarkBlueCard = Color(0xFF1A2A3A)
val GreenCard = Color(0xFF99FF00)
val TextGray = Color(0xFF6B7280)
val PayPalYellow = Color(0xFFFDD000) // Added for PayPal card consistency

// Dummy data for transactions
data class Transaction(val icon: ImageVector, val description: String, val amount: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentAppUI(onNavigateToActivityScreen: () -> Unit) { // Accepts navigation lambda
    val context = LocalContext.current // Retained for general context usage if any

    Scaffold(
        containerColor = LightGrayBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Header Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Hi, YUSUF", color = TextGray, fontSize = 16.sp)
                    Text(text = "Welcome Back!", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                }
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color(0xFFBFFF00),
                                    Color(0xFF00FF00)
                                )
                            )
                        )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Card Stack Section - STATIC LAYOUT REINSTATED
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp) // Adjust height to accommodate overlapping cards
            ) {
                // Add new card (Dark Blue)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = 0.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkBlueCard)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Add new card",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Card",
                            tint = Color.White,
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFF3B4A5B))
                                .padding(8.dp)
                        )
                    }
                }

                // PayPal Card (Yellow) - NO MoreHoriz Icon
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = 20.dp) // Overlap
                        .padding(horizontal = 16.dp), // Smaller horizontal padding
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = PayPalYellow)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "PayPal",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = DarkBlueCard
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        // Icon(imageVector = Icons.Default.MoreHoriz, ...) - REMOVED AS REQUESTED
                    }
                }

                // VISA Card (Green)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp) // Slightly taller
                        .align(Alignment.BottomCenter) // Aligned to bottom
                        .offset(y = 10.dp), // Overlap more
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = GreenCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "VISA",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 28.sp,
                                color = Color.Black
                            )
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.Black.copy(alpha = 0.1f))
                            )
                        }
                        Text(
                            text = "*** *** ***9743",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "Holder",
                                    fontSize = 12.sp,
                                    color = Color.Black.copy(alpha = 0.7f)
                                )
                                Text(
                                    text = "Yusuf Masood",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "Exp Date",
                                    fontSize = 12.sp,
                                    color = Color.Black.copy(alpha = 0.7f)
                                )
                                Text(
                                    text = "02/02",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Action Buttons Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ActionButton(
                    painter = painterResource(R.drawable.deposit),
                    text = "Deposit",
                    onClick = { /* TODO: Handle Deposit click */ }
                )

                ActionButton(
                    painter = painterResource(R.drawable.transfer),
                    text = "Transfer",
                    onClick = { /* TODO: Handle Transfer click */ }
                )

                ActionButton(
                    painter = painterResource(R.drawable.withdraw),
                    text = "Withdraw",
                    onClick = { /* TODO: Handle Withdraw click */ }
                )

                ActionButton(
                    painter = painterResource(R.drawable.activity),
                    text = "Activity",
                    onClick = onNavigateToActivityScreen // This calls the lambda from MainActivity
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // All Transactions Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "All Transactions", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Today", color = TextGray, fontSize = 14.sp)
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Filter",
                        tint = TextGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Transaction List
            val transactions = listOf(
                Transaction(Icons.Default.Send, "Salman Khan", "- ₹128.08"),
                Transaction(Icons.Default.Add, "Gym", "- ₹30.08")
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(transactions) { transaction ->
                    TransactionItem(transaction = transaction)
                }
            }
        }
    }
}

@Composable
fun ActionButton(painter: Painter, text: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painter,
                contentDescription = text,
                tint = DarkBlueCard,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = text, fontSize = 14.sp, color = TextGray)
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(LightGrayBackground),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = transaction.icon,
                        contentDescription = null,
                        tint = DarkBlueCard,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = transaction.description,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    Text(
                        text = if (transaction.description == "Salman Khan") "Sent" else "Payment",
                        color = TextGray,
                        fontSize = 12.sp
                    )
                }
            }
            Text(
                text = transaction.amount,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentAppUIPreview() {
    PaymentAppUI(onNavigateToActivityScreen = { })
}
