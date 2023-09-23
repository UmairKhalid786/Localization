@file:OptIn(ExperimentalMaterial3Api::class)

package com.techlads.localization101

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.techlads.localization101.ui.theme.Localization101Theme

@Composable
fun EditableExposedDropdownMenuSample() {
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = selectedOptionText,
            onValueChange = { selectedOptionText = it },
            label = { Text("Search language") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        // filter options based on text field value
        val filteringOptions by remember {
            derivedStateOf {
                options.filter {
                    it.contains(selectedOptionText, ignoreCase = true).also { result ->
                        if (result) {
                            expanded = true
                        }
                    }
                }
            }
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.fillMaxSize()
        ) {
            filteringOptions.forEach { selectionOption ->
                DropdownMenuItem(
                    modifier = Modifier.fillMaxSize(),
                    text = { Text(text = selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    }
                )
            }
        }

        if (filteringOptions.isNotEmpty()) {

        }
    }
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            modifier = Modifier
//                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
//        ) {
//            suggestions.filter { it.contains(selectedText, ignoreCase = true) }.forEach { label ->
//                DropdownMenuItem(onClick = {
//                    selectedText = label
//                }, text = { Text(text = label) })
//            }
//        }

}

@Composable
fun MiniDropDown() {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = { /* Handle refresh! */ },
                text = { Text("Refresh") })
            DropdownMenuItem(onClick = { /* Handle settings! */ }, text = {
                Text("Settings")
            })
            Divider()
            DropdownMenuItem(onClick = { /* Handle send feedback! */ }, text = {
                Text("Send Feedback")
            })
        }
    }
}

@Preview
@Composable
fun EditableExposedDropdownMenuSamplePreview() {
    Localization101Theme {
        EditableExposedDropdownMenuSample()
    }
}