package com.target.ignou.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.target.ignou.android.ui.dashboard.DashboardScreen
import com.target.ignou.android.ui.features.BookmarkScreen
import com.target.ignou.android.ui.features.FaqScreen
import com.target.ignou.android.ui.features.PyqSolveScreen
import com.target.ignou.android.ui.pyq.PyqCourseSelectionScreen
import com.target.ignou.android.ui.pyq.PyqYearSelectionScreen
import com.target.ignou.android.ui.theme.TargetIGNOUTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TargetIGNOUTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "dashboard") {
                    composable("dashboard") {
                        DashboardScreen(navController = navController)
                    }
                    composable("pyq_course_selection") {
                        PyqCourseSelectionScreen(navController = navController)
                    }
                    composable(
                        "pyq_year_selection/{subject}",
                        arguments = listOf(navArgument("subject") { type = NavType.StringType })
                    ) { backStackEntry ->
                        PyqYearSelectionScreen(
                            navController = navController,
                            subject = backStackEntry.arguments?.getString("subject")
                        )
                    }
                    composable("pyq_solve") {
                        PyqSolveScreen(navController = navController)
                    }
                    composable("bookmark") {
                        BookmarkScreen(navController = navController)
                    }
                    composable("faq") {
                        FaqScreen(navController = navController)
                    }
                }
            }
        }
    }
}
