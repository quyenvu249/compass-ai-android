plugins {
    id("compassai.kotlin.library")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.bundles.testing.unit)
}
