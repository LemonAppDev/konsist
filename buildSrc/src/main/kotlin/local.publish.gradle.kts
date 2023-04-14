plugins {
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.lemon.konsist"
            artifactId = "konsist"
            version = "0.7.0"

            from(components["java"])
        }
    }
}
