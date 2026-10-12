plugins {
    id("com.lemonappdev.konsist.convention.detekt")
    id("com.lemonappdev.konsist.convention.kotlin")
    id("com.lemonappdev.konsist.convention.publishaggregation")
}

dependencies {
    nmcpAggregation(project(":lib"))
}

// build-logic is an included build, so its Spotless tasks are not picked up by running "spotlessCheck" from the root
listOf("spotlessCheck", "spotlessApply").forEach { taskName ->
    tasks.register(taskName) {
        group = "verification"
        dependsOn(gradle.includedBuild("build-logic").task(":$taskName"))
    }
}
