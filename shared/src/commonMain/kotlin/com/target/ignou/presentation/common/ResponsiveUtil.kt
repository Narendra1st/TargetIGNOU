package com.target.ignou.presentation.common

import androidx.compose.runtime.Composable

@Composable
expect fun getScreenWidthDp(): Int

fun isTablet(screenWidthDp: Int): Boolean = screenWidthDp >= 600

fun getDeviceType(screenWidthDp: Int): DeviceType = if (isTablet(screenWidthDp)) DeviceType.TABLET else DeviceType.PHONE

