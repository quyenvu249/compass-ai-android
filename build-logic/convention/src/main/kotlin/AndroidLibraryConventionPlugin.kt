import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Convention plugin: compassai.android.library
 *
 * Applied to Android library modules that do NOT need Compose
 * (e.g. core-network, core-data, core-domain).
 *
 * Usage in module build.gradle.kts:
 *   plugins { id("compassai.android.library") }
 */
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidCommon(this)

                buildFeatures {
                    buildConfig = false
                }

                defaultConfig {
                    consumerProguardFiles("consumer-rules.pro")
                }
            }
        }
    }
}
