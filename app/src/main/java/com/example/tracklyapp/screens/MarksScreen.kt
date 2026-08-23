package com.example.tracklyapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tracklyapp.data.entity.Activity
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.tracklyapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarksScreen(
    activityList: List<Activity>,
    onBackClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.history)) },
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

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(activityList) { item ->
                MarkItem(item)
            }
        }
    }
}

@Composable
fun MarkItem(activity: Activity) {
    androidx.compose.material3.Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1e1e1e)
        ),
        border = BorderStroke(2.dp, Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(stringResource(R.string.duration,activity.duration))

                Spacer(modifier = Modifier.width(32.dp))

                Text(activity.date)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(activity.note, color = Color.Gray)
            }
        }
    }
}