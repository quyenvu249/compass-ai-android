// test-shared: shared test utilities available to all modules.
// Contains: fake repositories, test factories, test data builders.
// This module is ONLY ever a testImplementation dependency — never implementation.
plugins {
    id("compassai.android.library")
}

android {
    namespace = "com.compassai.test"
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":core:core-data"))

    api(libs.bundles.testing.unit)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)
}
