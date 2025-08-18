package com.example.appwithjetpack

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun spend(){

    Column(modifier = Modifier
        .fillMaxSize()

    ){
        Text(
            text = "Add your Expense",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}