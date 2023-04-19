import util.getLocalPropertyOrGradleProperty
import java.util.*

plugins {
    `maven-publish`
    signing
}

val konsistPublicationName = "konsist"

publishing {
    publications {
        create<MavenPublication>(konsistPublicationName) {
            val konsistDescription = "A Kotlin architecture test library. Define and guard code base consistency using Kotlin."

            groupId = "com.lemonappdev"
            artifactId = "konsist"
            version = getKonsistVersion()
            description = konsistDescription

            from(components.getByName("java"))

            pom {
                name.set("konsist")
                description.set(konsistDescription)
                url.set("https://www.lemonappdev.com/")

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
            when (getReleaseTarget()) {
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
            println("signingKey is not provided. Skipping signing.")
        } else if (signingPassword == null) {
            println("signingPassword is not provided. Skipping signing.")
        }
    }
}

enum class ReleaseTarget(val value: String) {
    LOCAL("local"),
    SNAPSHOT("snapshot"),
    RELEASE("release"),
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

fun getReleaseTarget(): ReleaseTarget {
    val releaseTargetStr = getLocalPropertyOrGradleProperty("konsist.releaseTarget")

    return ReleaseTarget
        .values()
        .firstOrNull { it.value == releaseTargetStr }
        ?: ReleaseTarget.LOCAL
}

fun getKonsistVersion(): String {
    val releaseTarget = getReleaseTarget()
    val version = getLocalPropertyOrGradleProperty("konsist.version") ?: error("konsist.version is not provided.")

    return when (releaseTarget) {
        ReleaseTarget.LOCAL -> "$version-SNAPSHOT"
        ReleaseTarget.SNAPSHOT -> "$version-SNAPSHOT"
        ReleaseTarget.RELEASE -> version
    }
}
