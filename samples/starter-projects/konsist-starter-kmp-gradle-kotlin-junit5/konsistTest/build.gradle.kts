plugins {
    id("org.jetbrains.kotlin.jvm")
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Test> {
    // Configure JUnit 5 tests
    useJUnitPlatform()
}

dependencies {
    testImplementation("com.lemonappdev:konsist:0.15.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}
