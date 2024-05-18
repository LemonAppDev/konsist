plugins {
    kotlin("multiplatform") version "1.9.24"
    application
    id("com.android.application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvm {
        jvmToolchain(8)
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
                implementation("io.ktor:ktor-server-netty:2.3.11")
                implementation("io.ktor:ktor-server-html-builder-jvm:2.3.11")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.11.0")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation("com.lemonappdev:konsist:0.15.1")
            }
        }

        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.3.1-pre.749")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.3.1-pre.749")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.11.4-pre.749")
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
