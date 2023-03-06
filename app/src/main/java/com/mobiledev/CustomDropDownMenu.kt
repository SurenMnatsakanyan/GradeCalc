package com.mobiledev

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DropDownMenu(
    onTextChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var index by remember { mutableStateOf(1) }
    val text = "Homework $index"
    onTextChange(text)

    Box(
        modifier = Modifier
            .width(120.dp)
            .wrapContentSize(Alignment.TopEnd)
            .clickable {
                expanded = !expanded
            }
    ) {

        Row {
            Text(text = text)
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "More"
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                repeat(5) {
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            index = it + 1
                        }
                    ) {
                        Text("Homework ${it + 1}")
                    }
                }
            }
        }
    }
}