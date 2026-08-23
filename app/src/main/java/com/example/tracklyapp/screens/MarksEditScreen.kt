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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.res.stringResource
import com.example.tracklyapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarksEditScreen(
    onSaveClick: (Int?, String) -> Unit,
    onBackClick: () -> Unit
) {
    var markText by remember { mutableStateOf("") }
    var durationTime by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.new_mark)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.fill_in_the_details),
                    color = Color.LightGray,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    stringResource(R.string.mark_text),
                    color = Color.Gray,
                    fontSize = 16.sp
                )

                OutlinedTextField(
                    value = markText,
                    onValueChange = { markText = it },
                    label = { Text(stringResource(R.string.e_g__Book_red_hat)) },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.DarkGray,
                        unfocusedTextColor = Color.Gray,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    stringResource(R.string.time_duration),
                    color = Color.Gray,
                    fontSize = 16.sp
                )

                OutlinedTextField(
                    value = durationTime,
                    onValueChange = { durationTime = it },
                    label = { Text(stringResource(R.string.e_g_120)) },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.DarkGray,
                        unfocusedTextColor = Color.Gray,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        onSaveClick(
                            durationTime.toIntOrNull(),
                            markText
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Purple80,
                        contentColor = Color.White
                    )
                ) {
                    Text(stringResource(R.string.save_mark))
                }
            }
        }
    }
}