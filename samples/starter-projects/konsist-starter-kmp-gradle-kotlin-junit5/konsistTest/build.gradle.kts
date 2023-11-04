plugins {
    id("org.jetbrains.kotlin.jvm")
}

kotlin {
    jvmToolchain(19)
}

tasks.withType<Test> {
    // Configure JUnit 5 tests
    useJUnitPlatform()
}

dependencies {
    testImplementation("com.lemonappdev:konsist:0.13.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}