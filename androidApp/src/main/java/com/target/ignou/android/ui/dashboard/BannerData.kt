package com.target.ignou.android.ui.dashboard

import com.target.ignou.R
import com.target.ignou.model.Banner

object BannerData {
    fun getBanners(): List<Banner> {
        return listOf(
            Banner(
                id = 1,
                title = "IGNOU Admit Card",
                imageRes = R.drawable.ignou_admit_card
            ),
            Banner(
                id = 2,
                title = "IGNOU Exam Form",
                imageRes = R.drawable.ignou_exam_form
            ),
            Banner(
                id = 3,
                title = "IGNOU Result Update",
                imageRes = R.drawable.ignou_result_update
            )
        )
    }
}
