package com.example.tracklyapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tracklyapp.ui.theme.Purple80

@Composable
fun TasksEditScreen(onSaveClick: (String, Int?) -> Unit) {
    var nameText by remember { mutableStateOf("") }
    var timeValue by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            )
        {
            Text("New activity", color = Color.White, fontSize = 32.sp)

            Text("Fill in the details below", color = Color.LightGray, fontSize = 16.sp)

            Spacer(modifier = Modifier.height(12.dp))

            Text("Activity name", color = Color.Gray, fontSize = 16.sp)

            OutlinedTextField(
                value = nameText,
                onValueChange = { nameText = it },
                label = { Text("e.g. Read book") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.DarkGray,
                    unfocusedTextColor = Color.Gray,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("Weekly goal (min)", color = Color.Gray, fontSize = 16.sp)

            OutlinedTextField(
                value = timeValue,
                onValueChange = { timeValue = it },
                label = { Text("e.g. 120 ") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.DarkGray,
                    unfocusedTextColor = Color.Gray,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                )
            )

            Text("Optional – leave empty ro skip", color = Color.Gray, fontSize = 16.sp)

            Spacer(modifier = Modifier.weight(1f))


            Button(
                onClick = { onSaveClick(nameText, timeValue.toIntOrNull()) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple80,
                    contentColor = Color.White
                )
            ) {
                Text("Save activity")
            }
        }
    }
}