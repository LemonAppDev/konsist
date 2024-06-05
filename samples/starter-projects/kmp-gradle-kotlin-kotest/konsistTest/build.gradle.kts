plugins {
    id("org.jetbrains.kotlin.jvm")
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    testImplementation("com.lemonappdev:konsist:0.15.1")
    testImplementation("io.kotest:kotest-runner-junit5:5.9.0")
}
