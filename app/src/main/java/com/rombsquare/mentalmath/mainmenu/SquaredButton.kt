package com.rombsquare.mentalmath.mainmenu

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SquaredButton(
    modifier: Modifier = Modifier,
    symbol: String,
    size: Int,
    color: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape((size/4).dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        modifier = modifier
            .size(size.dp)
    ) {
        Text(
            text=symbol,
            fontSize=(size / 1.5).sp,
            color= Color.Black,
        )
    }
}