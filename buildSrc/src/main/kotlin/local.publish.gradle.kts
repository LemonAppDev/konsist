import config.ReleaseTarget
import ext.getFullKonsistVersion
import ext.getLocalPropertyOrGradleProperty
import ext.getReleaseTarget
import java.util.Base64

plugins {
    `maven-publish`
    signing
}

val konsistPublicationName = "konsist"

publishing {
    val releaseTarget = project.getReleaseTarget()

    publications {
        create<MavenPublication>(konsistPublicationName) {
            val konsistDescription =
                "Konsist is a powerful static code analyzer tailored for Kotlin, " +
                    "focused on ensuring codebase consistency and adherence to coding conventions."

            groupId = "com.lemonappdev"
            artifactId = "konsist"
            version = project.getFullKonsistVersion(releaseTarget)
            description = konsistDescription

            from(components.getByName("java"))

            pom {
                name.set("konsist")
                description.set(konsistDescription)
                url.set("https://docs.konsist.lemonappdev.com/")

                contributors {
                    contributor {
                        name.set("Igor Wojda")
                        email.set("igor.wojda@gmail.com")
                    }
                }

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
            when (releaseTarget) {
                ReleaseTarget.LOCAL -> {
                    name = "local"
                    url = mavenLocal().url
                }

                ReleaseTarget.SNAPSHOT -> {
                    name = "snapshot"
                    // Repository URL for snapshot deployment and download access:
                    url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

                    setCredentialsFromGradleProperties()
                }

                ReleaseTarget.RELEASE -> {
                    name = "release"
                    // Repository URL for release deployment, no download access! :
                    url = uri("https://s01.oss.sonatype.org/content/repositories/releases/")

                    setCredentialsFromGradleProperties()
                }
            }
        }
    }
}

signing {
    val signingKey = getLocalPropertyOrGradleProperty("konsist.signingKey")
    val signingPassword = getLocalPropertyOrGradleProperty("konsist.signingPassword")

    if (signingKey != null && signingPassword != null) {
        useInMemoryPgpKeys(
            decodeBase64(signingKey),
            decodeBase64(signingPassword),
        )

        sign(publishing.publications[konsistPublicationName])
    } else {
        if (signingKey == null) {
            logger.warn("Property 'konsist.signingKey' is not provided. Skipping signing.")
        } else if (signingPassword == null) {
            logger.warn("Property 'signingPassword' is not provided. Skipping signing.")
        }
    }
}

fun MavenArtifactRepository.setCredentialsFromGradleProperties() {
    val ossrhUsername = getLocalPropertyOrGradleProperty("konsist.ossrhUsername")
    val ossrhPassword = getLocalPropertyOrGradleProperty("konsist.ossrhPassword")

    credentials {
        username = ossrhUsername
        password = ossrhPassword
    }
}

fun decodeBase64(string: String) = String(Base64.getDecoder().decode(string)).trim()
