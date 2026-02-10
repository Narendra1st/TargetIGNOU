package com.target.ignou.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

@Immutable
data class Banner(
    val id: Int,
    val title: String,
    @DrawableRes val imageRes: Int
)
