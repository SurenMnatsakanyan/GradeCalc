package com.mobiledev.gradecalc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    input: String,
    label: String,
    isValid: Boolean,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            modifier = Modifier
                .height(60.dp)
                .then(modifier),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                autoCorrect = true
            ),
            label = { Text(text = label) },
            value = input,
            onValueChange = { value -> onValueChange(value) }
        )

        Text(
            text = when (isValid) {
                true -> ""
                false -> "Input must be a number between 0 and 100"
            },
            color = Color.Red,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}