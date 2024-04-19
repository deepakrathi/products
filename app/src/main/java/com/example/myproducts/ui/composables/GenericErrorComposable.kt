package com.example.myproducts.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myproducts.models.Cta

@Composable
fun ErrorComposable(
    errorTitle: String = "Something went wrong",
    errorSubtitle: String = "We are facing some issue at the moment",
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.Red, shape = RoundedCornerShape(20.dp))
            .padding(16.dp),
    ) {
        Text(text = errorTitle, color = Color.White, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = errorSubtitle, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            colors = ButtonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
                disabledContainerColor = Color.Black,
                disabledContentColor = Color.White
            ),
            onClick = {
                onClick.invoke()
            }) {
            Text(text = Cta.Retry.name)
        }

    }

}