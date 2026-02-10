package com.target.ignou.repository


import com.target.ignou.model.Banner
import com.target.ignou.model.*

interface DashboardRepository {
    suspend fun getBanners(): List<Banner>
    suspend fun getPyqs(): List<Pyq>
    suspend fun getFaqs(): List<Faq>
}
