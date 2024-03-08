import com.adarshr.gradle.testlogger.theme.ThemeType
import gradle.kotlin.dsl.accessors._3ffb5dd0950846471e2c972470f8fb4d.testlogger

plugins {
    id("org.gradle.jvm-test-suite")
    id("com.adarshr.test-logger")
}

tasks.withType<Test> {
    useJUnitPlatform()

    // Enable parallel test execution
    systemProperties = mapOf(
        "junit.jupiter.execution.parallel.enabled" to "true",
        "junit.jupiter.execution.parallel.mode.default " to "concurrent",
    )
}

testlogger {
    theme = ThemeType.MOCHA
}
