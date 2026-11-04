package com.lemonappdev.konsist.buildlogic.config

// Published Konsist artifact (main source set) must run on Java 11
private const val ARTIFACT_JVM_RELEASE = 11

// Konsist tests run on JUnit Platform 6, which requires Java 17
private const val TEST_JVM_RELEASE = 17

private val mainCompileTaskNames = setOf("compileJava", "compileKotlin")

internal fun jvmReleaseFor(compileTaskName: String) =
    if (compileTaskName in mainCompileTaskNames) ARTIFACT_JVM_RELEASE else TEST_JVM_RELEASE
