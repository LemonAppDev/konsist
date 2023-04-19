plugins {
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.lemonappdev.konsist"
            artifactId = "konsist"
            version = "0.7.0"

            from(components["java"])
        }
    }
}
