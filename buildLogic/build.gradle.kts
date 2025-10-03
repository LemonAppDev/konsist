plugins {
    `kotlin-dsl`
}

group = "com.lemonappdev.konsist.buildlogic"

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(plugin(libs.plugins.kotlinJvm))
    implementation(plugin(libs.plugins.spotless))
    implementation(plugin(libs.plugins.testLogger))
    implementation(plugin(libs.plugins.detekt))
    implementation(plugin(libs.plugins.dokka))

    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

fun plugin(plugin: Provider<PluginDependency>) =
    plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("baseConvention") {
            id = "com.lemonappdev.konsist.convention.base"
            implementationClass = "com.lemonappdev.konsist.buildlogic.BaseConventionPlugin"
        }

        register("kotlinConvention") {
            id = "com.lemonappdev.konsist.convention.kotlin"
            implementationClass = "com.lemonappdev.konsist.buildlogic.KotlinConventionPlugin"
        }

        register("spotlessConvention") {
            id = "com.lemonappdev.konsist.convention.spotless"
            implementationClass = "com.lemonappdev.konsist.buildlogic.SpotlessConventionPlugin"
        }

        register("detektConvention") {
            id = "com.lemonappdev.konsist.convention.detekt"
            implementationClass = "com.lemonappdev.konsist.buildlogic.DetektConventionPlugin"
        }

        register("testLoggerConvention") {
            id = "com.lemonappdev.konsist.convention.testlogger"
            implementationClass = "com.lemonappdev.konsist.buildlogic.TestLoggerConventionPlugin"
        }

        register("dokkaConvention") {
            id = "com.lemonappdev.konsist.convention.dokka"
            implementationClass = "com.lemonappdev.konsist.buildlogic.DokkaConventionPlugin"
        }

        register("javaLibraryConvention") {
            id = "com.lemonappdev.konsist.convention.javalibrary"
            implementationClass = "com.lemonappdev.konsist.buildlogic.JavaLibraryConventionPlugin"
        }

        register("publishConvention") {
            id = "com.lemonappdev.konsist.convention.publish"
            implementationClass = "com.lemonappdev.konsist.buildlogic.PublishConventionPlugin"
        }
    }
}
