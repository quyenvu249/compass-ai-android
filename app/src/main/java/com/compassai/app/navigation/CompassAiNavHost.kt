package com.compassai.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.compassai.feature_auth.navigation.authNavGraph
import com.compassai.feature_auth.navigation.AuthRoute
import com.compassai.feature_resume.navigation.resumeNavGraph
import com.compassai.feature_analysis.navigation.analysisNavGraph

/**
 * Root navigation host.
 *
 * Each feature module contributes its own NavGraph extension function
 * (e.g. authNavGraph(), resumeNavGraph()) which is registered here.
 * The app module is the only place that knows about all feature graphs —
 * feature modules themselves are navigation-decoupled from each other.
 *
 * Start destination is the auth graph. Once authenticated, the auth graph
 * navigates to the resume graph and pops itself off the back stack.
 */
@Composable
fun CompassAiNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = AuthRoute.Graph,
    ) {
        authNavGraph(
            onLoginSuccess = {
                navController.navigate(ResumeRoute.Graph) {
                    popUpTo(AuthRoute.Graph) { inclusive = true }
                }
            },
        )

        resumeNavGraph(
            onNavigateToAnalysis = { resumeId, jdId ->
                navController.navigate(AnalysisRoute.Result(resumeId, jdId))
            },
        )

        analysisNavGraph(
            onNavigateBack = { navController.popBackStack() },
        )
    }
}
