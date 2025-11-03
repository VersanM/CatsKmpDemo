package com.example.catskmpdemo.feature.dashboard.presentation.cat_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.catskmpdemo.feature.dashboard.domain.Cat

@Composable
fun CatListScreenRoot(
    viewModel: CatListViewModel,
    onCatClick: (Cat) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onAction(CatListAction.OnScreenStarted)
    }

    CatListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is CatListAction.OnCatClick -> onCatClick(action.cat)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun CatListScreen(
    state: CatListState,
    onAction: (CatListAction) -> Unit,
) {
    Box(
        Modifier.fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        Column(Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Cats", style = MaterialTheme.typography.headlineSmall)
                Button(
                    onClick = { onAction(CatListAction.OnRefresh) },
                    enabled = !state.isLoading
                ) { Text("Refresh") }
            }
            if (state.isLoading && state.cats.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.cats) { cat ->
                        CatRow(
                            cat = cat,
                            onClick = { onAction(CatListAction.OnCatClick(cat)) }
                        )
                    }
                }
            }
        }
        state.errorMessage?.let { msg ->
            Box(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
            ) {
                Snackbar { Text(msg.asString()) }
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
            )
        }
    }
}

@Composable
private fun CatRow(
    cat: Cat,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = cat.url,
            contentDescription = "Cat ${cat.id}",
            modifier = Modifier
                .size(96.dp)
        )
        Column(Modifier.weight(1f)) {
            Text(text = "ID: ${cat.id}", style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Size: ${cat.width}x${cat.height}",
                style = MaterialTheme.typography.bodySmall
            )
            if (cat.breeds.isNotEmpty()) {
                Text(
                    text = "Breeds: ${cat.breeds.joinToString { it.name }}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}