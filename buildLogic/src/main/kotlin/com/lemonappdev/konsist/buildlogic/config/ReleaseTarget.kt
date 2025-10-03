package com.lemonappdev.konsist.buildlogic.config

enum class ReleaseTarget(val value: String) {
    LOCAL("local"),
    RELEASE("release"),
    SNAPSHOT("snapshot"),
}
