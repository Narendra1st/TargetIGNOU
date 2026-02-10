package com.target.ignou.data.remote.repository

import com.target.ignou.model.Banner
import com.target.ignou.model.Faq
import com.target.ignou.model.Pyq
import com.target.ignou.repository.DashboardRepository


class DashboardRepositoryImpl : DashboardRepository {

    override suspend fun getBanners(): List<Banner> =
        listOf(
            Banner(id = 1, title = "Banner 1", imageRes = 0),
            Banner(id = 2, title = "Banner 2", imageRes = 0)
        )

    override suspend fun getPyqs(): List<Pyq> =
        listOf(
            Pyq("BCA", 2023, "DSA", "https://exam.ignou.ac.in/")
        )

    override suspend fun getFaqs(): List<Faq> =
        listOf(
            Faq("What is TargetIGNOU?", "IGNOU exam preparation app")
        )
}
