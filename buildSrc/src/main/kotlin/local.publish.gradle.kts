import util.getLocalOrGradleProperty
import java.util.*

plugins {
    `maven-publish`
    signing
}

private val konsistPublicationName = "konsist"

publishing {
    publications {
        create<MavenPublication>(konsistPublicationName) {
            groupId = "com.lemonappdev"
            artifactId = "konsist"
            version = "0.7.0-SNAPSHOT"

            from(components.getByName("java"))

            pom {
                name.set("konsist")
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
            name = "snapshots"
            // Repository URL for snapshot deployment and download access:
            url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

            setCredentialsFromGradleProperties()
            // Repository URL for release deployment, no download access! :
            // url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
        }

        mavenLocal()
    }
}

signing {
    val signingKey = getLocalOrGradleProperty("signingKey")
    val signingPassword = getLocalOrGradleProperty("signingPassword")

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

fun MavenArtifactRepository.setCredentialsFromGradleProperties() {
    val ossrhUsername = getLocalOrGradleProperty("ossrhUsername")
    val ossrhPassword = getLocalOrGradleProperty("ossrhPassword")

    credentials {
        username = ossrhUsername
        password = ossrhPassword
    }
}

fun decodeBase64(string: String) = String(Base64.getDecoder().decode(string)).trim()
