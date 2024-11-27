plugins {
    kotlin("multiplatform") version "2.1.0"
    id("io.kotest.multiplatform") version "6.0.0-LOCAL"
    application
    id("com.android.application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvm {
        jvmToolchain(11)
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
                implementation("io.ktor:ktor-server-netty:3.0.1")
                implementation("io.ktor:ktor-server-html-builder-jvm:3.0.1")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.11.0")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation("com.lemonappdev:konsist:0.17.0")
                implementation("io.kotest:kotest-runner-junit5:5.9.1")
            }
        }

        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.3.1-pre.839")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.3.1-pre.839")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.13.5-pre.839")
            }
        }
        val jsTest by getting
    }
}

application {
    mainClass.set("org.example.application.ServerKt")
}

tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}
