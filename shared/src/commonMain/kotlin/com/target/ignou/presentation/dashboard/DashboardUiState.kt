package com.target.ignou.presentation.dashboard


import com.target.ignou.model.Banner

data class DashboardUiState(
    val banners: List<Banner> = emptyList()
)
