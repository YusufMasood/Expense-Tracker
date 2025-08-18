
package com.example.appwithjetpack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


private const val PREFS_NAME = "MyPaymentAppPrefs"
private const val KEY_FIRST_LAUNCH = "is_first_launch"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isFirstLaunch = sharedPrefs.getBoolean(KEY_FIRST_LAUNCH, true)

        if (isFirstLaunch) {

            val intent = Intent(this, WelcomeScreen::class.java)
            startActivity(intent)
            finish()
        } else {

            setContent {
                MaterialTheme {

                    val navController = rememberNavController();
                    NavHost(navController = navController, startDestination = "firstPage"){


                        composable("firstPage"){
                            PaymentAppUI(
                                onNavigateToActivityScreen = {
                                    navController.navigate("ActivityScreen")
                                }
                            )

                        }

                        composable("ActivityScreen"){
                            ActivityScreenContent(
                                onNavigateBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun MainActivityPreview() {

    MaterialTheme {
        PaymentAppUI(onNavigateToActivityScreen = {})
    }
}
