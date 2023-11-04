plugins {
    id("org.jetbrains.kotlin.jvm")
}

kotlin {
    jvmToolchain(19)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    testImplementation("com.lemonappdev:konsist:0.13.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
}
