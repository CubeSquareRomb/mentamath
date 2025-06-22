package com.rombsquare.mentalmath.mainmenu

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rombsquare.mentalmath.Operation
import com.rombsquare.mentalmath.ui.theme.MulColor
import com.rombsquare.mentalmath.ui.theme.SubColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MenuButtons(
    modifier: Modifier = Modifier,
    onClick: (Operation) -> Unit
) {
    FlowRow(
        modifier,
        maxItemsInEachRow = 2,
    ) {
        SquaredButton(
            modifier = Modifier.padding(4.dp),
            symbol = "+",
            size = 100,
            color = Color.Green,
            onClick = { onClick(Operation.Add) }
        )

        SquaredButton(
            modifier = Modifier.padding(4.dp),
            symbol = "-",
            size = 100,
            color = SubColor,
            onClick = { onClick(Operation.Sub) }
        )

        SquaredButton(
            modifier = Modifier.padding(4.dp),
            symbol = "×",
            size = 100,
            color = MulColor,
            onClick = { onClick(Operation.Mul) }
        )

        SquaredButton(
            modifier = Modifier.padding(4.dp),
            symbol = "÷",
            size = 100,
            color = Color.Yellow,
            onClick = { onClick(Operation.Div) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSquaredButton() {
    MaterialTheme {
        MenuButtons {}
    }
}