import java.util.Base64

plugins {
    `maven-publish`
    signing
}

private val konsistPublicationName = "konsist"

publishing {
    publications {
        create<MavenPublication>(konsistPublicationName) {
            groupId = "com.lemon"
            artifactId = "konsist"
            version = "0.7.0"

            from(components["java"])

            pom {
                name.set("Konsist")
                description.set("A Kotlin architecture test library. Define and guard ode base consistency rules using Kotlin.")
                url.set("https://github.com/LemonAppDev/konsist")
                properties.set(
                    mapOf(
                        "myProp" to "value",
                        "prop.with.dots" to "anotherValue",
                    ),
                )
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("lemonappdev")
                        name.set("Lemon App Dev")
                        email.set("igor.wojda@gmail.com")
                    }
                }
                // Docs: https://maven.apache.org/pom.html#scm
                scm {
                    url.set("https://github.com/LemonAppDev/konsist")
                    connection.set("scm:git:git@github.com:LemonAppDev/konsist.git")
                    developerConnection.set("scm:git:git@github.com:LemonAppDev/konsist.git")
                }
            }
        }
    }

    repositories {
        maven {
            val repositoryUrl = when {
                project.hasProperty("release") -> ""
                project.hasProperty("snapshot") -> ""
                else -> mavenLocal().url
            }

            url = uri(repositoryUrl)
        }
    }
}

signing {
    val signingKey = providers.gradleProperty("signingKey")
    val signingPassword = providers.gradleProperty("signingPassword")

    if (signingKey.isPresent && signingPassword.isPresent) {
        useInMemoryPgpKeys(
            decodeBase64(signingKey.get()),
            decodeBase64(signingPassword.get()),
        )

        sign(publishing.publications[konsistPublicationName])
    } else {
        if (!signingKey.isPresent) {
            println("signingKey is not provided. Skipping signing.")
        } else if (!signingPassword.isPresent) {
            println("signingPassword is not provided. Skipping signing.")
        }
    }
}

fun decodeBase64(string: String) = String(Base64.getDecoder().decode(string)).trim()
