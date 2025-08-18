package com.example.appwithjetpack

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource // For drawable icons if needed

// Define custom colors based on the new screenshot
val ScreenBackground = Color(0xFFF8FBF0) // Light cream background
val HeaderIconBackground = Color(0xFFE0E6D8) // Lighter grey-green for header icons
val ChartBackground = Color(0xFF1A2A3A) // Dark blue for the chart area
val ChartBarGreen = Color(0xFF99FF00) // Vibrant green for chart bars
val ChartBarYellow = Color(0xFFFDD000) // Vibrant yellow for chart bars
val IncomeCardYellow = Color(0xFFFDD000) // Yellow for income card
val ExpenseCardGreen = Color(0xFF99FF00) // Green for expense card
val TextDark = Color(0xFF333333) // Dark text for titles
val TextLightGray = Color(0xFF6B7280) // Light gray for secondary text
val CategoryCardBackground = Color.White // White for category cards

// Dummy data for categories
data class ActivityScreen(val name: String, val amount: String, val iconResId: Int) // Using Int for drawable resource ID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityScreenContent(onNavigateBack: () -> Unit) {
    Scaffold(
        containerColor = ScreenBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back Button
                Box(
                    modifier = Modifier
                        .clickable{onNavigateBack()}
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(HeaderIconBackground),
                    contentAlignment = Alignment.Center

                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = TextDark,
                        modifier = Modifier.size(24.dp)
                    )
                }
                // Title
                Text(
                    text = "Activity",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = TextDark
                )
                // More Options Button
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(HeaderIconBackground),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz,
                        contentDescription = "More Options",
                        tint = TextDark,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Total Spending Section
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Total Spending", color = TextLightGray, fontSize = 16.sp)
                Text(text = "₹1,376.90", fontWeight = FontWeight.ExtraBold, fontSize = 32.sp, color = TextDark)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Bar Chart Placeholder
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = ChartBackground),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Month filter
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = ChartBarYellow.copy(alpha = 0.8f))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Month", color = TextDark, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Month Filter", tint = TextDark, modifier = Modifier.size(18.dp))
                            }
                        }
                    }
                    // Simplified Bar Chart (Visual Placeholder)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        // Dummy bars
                        ChartBar(height = 0.4f, color = ChartBarGreen, label = "Feb")
                        ChartBar(height = 0.6f, color = ChartBarYellow, label = "Mar")
                        ChartBar(height = 0.8f, color = ChartBarGreen, label = "Apr")
                        ChartBar(height = 0.5f, color = ChartBarYellow, label = "May")
                        ChartBar(height = 0.7f, color = ChartBarGreen, label = "Jun")
                        ChartBar(height = 0.6f, color = ChartBarYellow, label = "Jul")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Income and Expense Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                SummaryCard(
                    title = "Income",
                    amount = "₹3,607.00",
                    icon = Icons.Default.ArrowUpward,
                    backgroundColor = IncomeCardYellow,
                    iconTint = TextDark
                )
                SummaryCard(
                    title = "Expense",
                    amount = "₹1,807.00",
                    icon = Icons.Default.ArrowUpward, // Image shows upward, assuming net positive
                    backgroundColor = ExpenseCardGreen,
                    iconTint = TextDark
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Categories Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Categories", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = TextDark)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Expense", color = TextLightGray, fontSize = 14.sp)
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Category Filter", tint = TextLightGray, modifier = Modifier.size(18.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Scrollable Categories Row
            val categories = listOf(
                ActivityScreen("Investments", "₹3,607.0", R.drawable.bank),
                ActivityScreen("Travelling", "₹4,207.01", R.drawable.aeroplane),
                ActivityScreen("Food", "₹1,500.00", R.drawable.food),
                ActivityScreen("Entertainment", "₹800.00", R.drawable.subscriptions),
                ActivityScreen("Rent and Bills", "₹2,000.00", R.drawable.bills),
                ActivityScreen("Shopping", "₹750.00", R.drawable.shopping),
                ActivityScreen("Money lend", "₹1,200.00", R.drawable.lend_money)
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 4.dp) // Small padding for edges
            ) {
                items(categories) { category ->
                    CategoryCard(category = category)
                }
            }
        }
    }
}

@Composable
fun ChartBar(height: Float, color: Color, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .width(24.dp)
                .fillMaxHeight(height)
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .background(color)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, color = Color.White, fontSize = 12.sp)
    }
}

@Composable
fun SummaryCard(title: String, amount: String, icon: ImageVector, backgroundColor: Color, iconTint: Color) {
    Card(
        modifier = Modifier
            .width(160.dp) // Fixed width for summary cards
            .height(80.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = title, color = TextDark, fontSize = 14.sp)
                Text(text = amount, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = TextDark)
            }
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White.copy(alpha = 0.5f)), // Semi-transparent white background for icon
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = title, tint = iconTint, modifier = Modifier.size(20.dp))
            }
        }
    }
}


@Composable
fun CategoryCard(category: ActivityScreen) {
    Card(
        modifier = Modifier
            .width(160.dp) // Fixed width for category cards
            .height(140.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CategoryCardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(HeaderIconBackground), // Using a light background for icons
                contentAlignment = Alignment.Center
            ) {
                // Using painterResource for drawable icons.
                // Replace android.R.drawable.xxx with your actual R.drawable.your_icon_name
                Icon(
                    painter = painterResource(id = category.iconResId),
                    contentDescription = category.name,
                    tint = TextDark,
                    modifier = Modifier.size(28.dp)
                )
            }
            // Category Name
            Text(
                text = category.name,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = TextDark,
                modifier = Modifier.padding(top = 8.dp)
            )
            // Amount
            Text(
                text = category.amount,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = TextDark
            )
        }
    }
}


@Composable
fun ActivityScreenPreview() {
    ActivityScreenContent(onNavigateBack = {})
}
