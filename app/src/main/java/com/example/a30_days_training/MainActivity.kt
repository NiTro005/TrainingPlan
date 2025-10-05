package com.example.a30_days_training

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a30_days_training.data.trainings
import com.example.a30_days_training.model.Training
import com.example.compose.TrainingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrainingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TrainingApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingTopAppBar(modifier: Modifier = Modifier ) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.dumbbells),
                    contentDescription = stringResource(R.string.title),
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = stringResource(R.string.title),
                    style = MaterialTheme.typography.displayLarge,
                    fontSize = 30.sp
                )
            }
        }
    )
}

@Composable
fun TrainingApp() {
    Scaffold(
        topBar = { TrainingTopAppBar() }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(trainings) {
                TrainingItem(it, it.index, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun TrainingItem(training: Training, id: Int, modifier: Modifier = Modifier) {
    var torch by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        if(!torch) {
            MaterialTheme.colorScheme.surfaceContainerHigh
        } else {
            MaterialTheme.colorScheme.secondaryContainer
        }
    )
    Card(modifier = modifier
        .clickable {
            torch = !torch
        },
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
            ) {
            Text(
                text = stringResource(R.string.day, id + 1),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(8.dp).align(Alignment.Start)
            )
            TrainingImage(training.image, training.title)
            AnimatedVisibility(
                visible = torch,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                TrainingTechnique(training.description, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun TrainingImage(@DrawableRes image: Int, @StringRes title: Int, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .offset(y = (-24).dp)
            .padding(start = 200.dp)
            .align(Alignment.BottomEnd)
        )
            {
            Image(
                painter = painterResource(R.drawable.footer_lodyas),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.7f,
                modifier = Modifier.clip(MaterialTheme.shapes.medium)
            )
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.labelSmall,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Composable
fun TrainingTechnique(@StringRes technique: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(technique),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun TrainingItemPreview() {
    TrainingTheme {
        TrainingItem(trainings[0], 0)
    }
}

@Preview
@Composable
fun TrainingAppPreview() {
    TrainingTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            TrainingApp()
        }
    }
}