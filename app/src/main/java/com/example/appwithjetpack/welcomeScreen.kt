// WelcomeActivity.kt
package com.example.appwithjetpack // Make sure this matches your project's package name

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign

// Define custom colors to match the green/yellow/black/gray theme
val MainBackgroundLightGray = Color(0xFFF2F4F7) // Light gray background
val AccentGreen = Color(0xFF99FF00) // Vibrant green for accents
val AccentYellow = Color(0xFFFDD000) // Yellow for accents
val DarkTextBlack = Color(0xFF1E1E1E) // Dark text/elements
val MediumGrayText = Color(0xFF6B7280) // Medium gray for description text
val ButtonBackgroundDark = Color(0xFF1A2A3A) // Dark blue/black for button background
val ButtonTextWhite = Color.White // White for button text

// Constants for SharedPreferences - these should be top-level or in a common object
private const val PREFS_NAME = "MyPaymentAppPrefs"
private const val KEY_FIRST_LAUNCH = "is_first_launch"

// Corrected class name from WelcomeScreen to WelcomeActivity
class WelcomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme { // Apply MaterialTheme to your welcome screen
                WelcomeScreen {
                    // When the "Get Started" button is clicked, navigate to DetailActivity
                    val sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                    // Set the flag to false here, as the welcome flow is completed
                    sharedPrefs.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()

                    // Corrected navigation target from DetailPage to DetailActivity
                    val intent = Intent(this, DetailPage::class.java)
                    startActivity(intent)
                    finish() // Finish WelcomeActivity so user can't go back to it
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(onGetStartedClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundLightGray) // Changed to light gray background
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Icon/Illustration Placeholder - styled with green/yellow/black
        Box(
            modifier = Modifier
                .size(180.dp) // Overall size of the outer circle
                .background(AccentGreen.copy(alpha = 0.6f), CircleShape), // Semi-transparent green outer circle
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(180.dp) // Overall size of the outer circle
                    .background(AccentGreen.copy(alpha = 0.6f), CircleShape), // Semi-transparent green outer circle
                contentAlignment = Alignment.Center,
            )
            {
                    // Coin icon placeholder (using Text with a coin emoji)
                    // For a more detailed coin, you'd use a drawable asset.
                    Text(
                        text = "🪙", // Coin emoji
                        fontSize = 70.sp,
                        color = DarkTextBlack // Black color for the coin
                    )
                }

        }

        Spacer(modifier = Modifier.height(64.dp)) // Increased spacing

        Text(
            text = "Welcome to E-wallet",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkTextBlack, // Dark text color
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Simple way to manage money transfer and receive quickly.",
            fontSize = 18.sp,
            color = MediumGrayText, // Medium gray text color
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 32.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(80.dp)) // Increased spacing

        Button(
            onClick = onGetStartedClick,
            modifier = Modifier
                .fillMaxWidth(0.6f) // Make button narrower
                .height(56.dp), // Slightly taller button
            shape = RoundedCornerShape(12.dp), // Slightly less rounded corners
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBackgroundDark), // Dark button background
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
        ) {
            Text(text = "Get Started", color = ButtonTextWhite, fontSize = 20.sp, fontWeight = FontWeight.SemiBold) // White button text
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen {}
}
