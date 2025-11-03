package com.scottwilliams.tenfore_golf.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scottwilliams.tenfore_golf.features.home.components.GolfItemRow
import com.scottwilliams.tenfore_golf.features.home.model.GolfItem
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    items: List<GolfItem>,
    onBuy: (GolfItem) -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Menu",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
                Divider()
                Spacer(Modifier.height(8.dp))
                // Simple Logout button in the drawer
                Button(
                    onClick = {
                        scope.launch {
                            drawerState.close()
                            onLogout()
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    Text("Logout")
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("TenFore Golf") },
                    actions = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Open menu")
                        }
                    }
                )
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
}


private const val DEMO_IMAGE =
    "https://media.istockphoto.com/id/1462918815/photo/golf-clubs-and-golf-balls-on-a-green-lawn-in-a-beautiful-golf-course-with-morning-sunshine.jpg?s=612x612&w=0&k=20&c=miGC_L_Ia5EA_X68cTvmpSmzKEsMsfgqRH96Epg54IM="

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    val demo = listOf(
        GolfItem("1", "Pro V1 Golf Balls", "Tour-level performance, 12 pack", 54.99, DEMO_IMAGE),
        GolfItem("2", "Blade Putter", "Classic feel and control", 179.00, "https://www.piquacountryclub.com/images/Went-out-with-some-buddies-to.jpg"),
        GolfItem("3", "Stand Bag", "Lightweight, 5-way top, dual strap", 139.95, DEMO_IMAGE),
        GolfItem("4", "Forged Wedge 56°", "High spin, soft feel", 129.00, DEMO_IMAGE),
        GolfItem("5", "Distance Driver", "Max ball speed and forgiveness", 349.00, DEMO_IMAGE),
        GolfItem("6", "Tour Glove (3 pack)", "Premium cabretta leather", 39.99, DEMO_IMAGE)
    )

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        HomeScreen(
            items = demo,
            onBuy = {  },
            onLogout = {},
            modifier = Modifier.padding(padding)
        )
    }
}