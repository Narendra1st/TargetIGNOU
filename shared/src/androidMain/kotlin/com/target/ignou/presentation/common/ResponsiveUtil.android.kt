package com.target.ignou.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
actual fun getScreenWidthDp(): Int = LocalConfiguration.current.screenWidthDp
