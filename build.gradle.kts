plugins {
    id("com.lemonappdev.konsist.convention.detekt")
    id("com.lemonappdev.konsist.convention.kotlin")
    id("com.lemonappdev.konsist.convention.publishaggregation")
}

dependencies {
    nmcpAggregation(project(":lib"))
}
