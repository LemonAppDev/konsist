plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvmToolchain(11)

    jvm {
        withJava()
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }
    js {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.server.netty)
                implementation(libs.ktor.server.html.builder.jvm)
                implementation(libs.kotlinx.html.jvm)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.konsist)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(libs.kotlin.react)
                implementation(libs.kotlin.react.dom)
                implementation(libs.kotlin.emotion)
            }
        }
        val jsTest by getting
    }
}

tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}
