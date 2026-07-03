import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

/**
 * Convention plugin: compassai.android.feature
 *
 * Applied to all feature modules (feature-auth, feature-resume, feature-analysis).
 * Automatically includes:
 *   - Android library config
 *   - Compose
 *   - Hilt
 *   - core-domain (use cases, domain models — every feature needs these)
 *   - core-ui (shared design system components)
 *   - Standard feature dependencies (ViewModel, Navigation, etc.)
 *
 * Usage:
 *   plugins { id("compassai.android.feature") }
 */
class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("compassai.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("com.google.dagger.hilt.android")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
            }

            dependencies {
                // Every feature module gets these automatically
                add("implementation", project(":core:core-domain"))
                add("implementation", project(":core:core-ui"))
            }
        }
    }
}
