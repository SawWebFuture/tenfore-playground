package com.scottwilliams.tenfore_golf.features.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.scottwilliams.tenfore_golf.features.home.model.GolfItem
import java.text.NumberFormat
import java.util.Locale

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard

@Composable
fun GolfItemRow(
    item: GolfItem,
    onBuy: (GolfItem) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var isPurchased by remember { mutableStateOf(false) }

    Card {
        Column(Modifier.padding(16.dp)) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = "${item.name} image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(12.dp))

            Text(
                item.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(4.dp))
            Text(item.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(item.price.toCurrency(), style = MaterialTheme.typography.titleSmall)

                Button(
                    onClick = { showDialog = true },
                    enabled = !isPurchased, // ✅ Disable after purchase
                ) {
                    Text(if (isPurchased) "Purchased" else "Buy") // ✅ Update label
                }
            }
        }
    }

    // ✅ Purchase Modal
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            icon = { Icon(Icons.Outlined.CreditCard, contentDescription = null) },
            title = { Text("Purchase") },
            text = {
                Text("Buy \"${item.name}\" for ${item.price.toCurrency()}?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        isPurchased = true
                        onBuy(item)
                    }
                ) { Text("Purchase") }
            },
            dismissButton = {
                OutlinedButton(onClick = { showDialog = false }) {
                    Text("Close")
                }
            }
        )
    }
}

private fun Double.toCurrency(): String =
    NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this)
