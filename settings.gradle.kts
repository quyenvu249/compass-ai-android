pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CompassAI"

// App
include(":app")

// Core modules
include(":core:core-network")
include(":core:core-data")
include(":core:core-domain")
include(":core:core-ui")

// Feature modules
include(":feature:feature-auth")
include(":feature:feature-resume")
include(":feature:feature-analysis")
