package com.target.ignou.android.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.target.ignou.android.ui.profile.ProfileSettingsSheet
import com.target.ignou.presentation.common.getScreenWidthDp
import com.target.ignou.presentation.common.isTablet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {

    var showProfileMenu by remember { mutableStateOf(false) }

    val screenWidthDp = getScreenWidthDp()
    val isTablet = isTablet(screenWidthDp)
    val columns = if (isTablet) 4 else 2

    // 🔥 Banner data
    val banners = remember { BannerData.getBanners() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        /* -------- MAIN CONTENT -------- */
        Column {

            /* -------- TOP BAR -------- */
            Surface(shadowElevation = 4.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {
                        Text(
                            text = "My Dashboard",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Normal User",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }

                    IconButton(onClick = { showProfileMenu = true }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            /* -------- SLIDE BANNER -------- */
            BannerSlider(
                banners = banners,
                bannerHeight = if (isTablet) 220.dp else 160.dp
            )

            Spacer(modifier = Modifier.height(20.dp))

            /* -------- FEATURE GRID -------- */
            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    FeatureCard("PYQ IGNOU") {
                        navController.navigate("pyq_course_selection")
                    }
                }
                item {
                    FeatureCard("IGNOU PYQ Solve (All Years)") {
                        navController.navigate("pyq_solve")
                    }
                }
                item {
                    FeatureCard("Bookmark") {
                        navController.navigate("bookmark")
                    }
                }
                item {
                    FeatureCard("FAQ") {
                        navController.navigate("faq")
                    }
                }
            }
        }

        /* -------- PROFILE BOTTOM SHEET -------- */
        if (showProfileMenu) {
            ModalBottomSheet(
                onDismissRequest = { showProfileMenu = false }
            ) {
                ProfileSettingsSheet(
                    onClose = { showProfileMenu = false }
                )
            }
        }
    }
}
