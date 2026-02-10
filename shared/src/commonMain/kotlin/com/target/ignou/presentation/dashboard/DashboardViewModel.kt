package com.target.ignou.presentation.dashboard

import com.target.ignou.data.remote.repository.DashboardRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class DashboardViewModel {

    private val repo = DashboardRepositoryImpl()

    private val _state = MutableStateFlow(DashboardUiState())
    val state: StateFlow<DashboardUiState> = _state

    suspend fun load() {
        _state.value = DashboardUiState(
            banners = repo.getBanners()
        )
    }
}
