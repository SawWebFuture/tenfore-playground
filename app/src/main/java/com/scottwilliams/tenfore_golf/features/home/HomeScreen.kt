package com.scottwilliams.tenfore_golf.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.Locale

// --- Model ---
data class GolfItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Double
)

// --- Public Composable (state hoisted) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    items: List<GolfItem>,
    onBuy: (GolfItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("TenFore Golf") })
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items, key = { it.id }) { item ->
                GolfItemRow(item = item, onBuy = onBuy)
            }
        }
    }
}

// --- Row UI ---
@Composable
private fun GolfItemRow(
    item: GolfItem,
    onBuy: (GolfItem) -> Unit
) {
    Card {
        Column(Modifier.padding(16.dp)) {
            Text(item.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(4.dp))
            Text(item.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(item.price.toCurrency(), style = MaterialTheme.typography.titleSmall)
                Button(onClick = { onBuy(item) }) {
                    Text("Buy")
                }
            }
        }
    }
}

// --- Util ---
private fun Double.toCurrency(): String =
    NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this)

// --- Preview / Example usage ---
@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    val demo = listOf(
        GolfItem(id = "1", name = "Pro V1 Golf Balls", description = "Tour-level performance, 12 pack", price = 54.99),
        GolfItem(id = "2", name = "Blade Putter", description = "Classic feel and control", price = 179.00),
        GolfItem(id = "3", name = "Stand Bag", description = "Lightweight, 5-way top, dual strap", price = 139.95)
    )
    // For preview, just show a snackbar when "buy" is clicked
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        HomeScreen(
            items = demo,
            onBuy = { /* no-op in preview */ },
            modifier = Modifier.padding(padding)
        )
    }
}
