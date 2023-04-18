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
            val repositoryProperty = providers.gradleProperty("repository")

            when (repositoryProperty.get()) {
                "release" -> {
                    url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                    setCredentialsFromGradleProperties()
                }

                "snapshot" -> {
                    url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
                    setCredentialsFromGradleProperties()
                }

                "local" -> {
                    url = mavenLocal().url
                }

                else -> {
                    throw GradleException("Unknown repository: ${repositoryProperty.get()}")
                }
            }
        }
    }
}

signing {
    val signingKeyProperty = providers.gradleProperty("signingKey")
    val signingPasswordProperty = providers.gradleProperty("signingPassword")

    if (signingKeyProperty.isPresent && signingPasswordProperty.isPresent) {
        useInMemoryPgpKeys(
            decodeBase64(signingKeyProperty.get()),
            decodeBase64(signingPasswordProperty.get()),
        )

        sign(publishing.publications[konsistPublicationName])
    } else {
        if (!signingKeyProperty.isPresent) {
            println("signingKey is not provided. Skipping signing.")
        } else if (!signingPasswordProperty.isPresent) {
            println("signingPassword is not provided. Skipping signing.")
        }
    }
}

fun MavenArtifactRepository.setCredentialsFromGradleProperties() {
    val ossrhUsername = providers.gradleProperty("ossrhUsername")
    val ossrhPassword = providers.gradleProperty("ossrhPassword")

    if (!ossrhUsername.isPresent) {
        throw GradleException("ossrhUsername is not provided.")
    }

    if (!ossrhPassword.isPresent) {
        throw GradleException("ossrhPassword is not provided.")
    }

    credentials {
        username = ossrhUsername.get()
        password = ossrhPassword.get()
    }
}

fun decodeBase64(string: String) = String(Base64.getDecoder().decode(string)).trim()
