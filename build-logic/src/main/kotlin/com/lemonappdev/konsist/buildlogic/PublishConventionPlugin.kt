package com.lemonappdev.konsist.buildlogic

import com.lemonappdev.konsist.buildlogic.config.ReleaseTarget
import com.lemonappdev.konsist.buildlogic.ext.getFullKonsistVersion
import com.lemonappdev.konsist.buildlogic.ext.getLocalPropertyOrGradleProperty
import com.lemonappdev.konsist.buildlogic.ext.getReleaseTarget
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.plugins.signing.SigningExtension
import java.util.Base64

class PublishConventionPlugin : Plugin<Project> {
    @Suppress("detekt.LongMethod")
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "maven-publish")
            apply(plugin = "signing")

            val konsistPublicationName = "konsist"

            configure<PublishingExtension> {
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

                            // Repository URL for snapshot deployment and download access.
                            ReleaseTarget.SNAPSHOT -> {
                                name = "snapshot"
                                url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                                project.setCredentialsFromGradleProperties().invoke(this)
                            }

                            ReleaseTarget.RELEASE -> {
                                // Repository URL for release deployment, no download access.
                                name = "release"
                                url = uri("https://s01.oss.sonatype.org/content/repositories/releases/")
                                project.setCredentialsFromGradleProperties().invoke(this)
                            }
                        }
                    }
                }
            }

            configure<SigningExtension> {
                val signingKey = getLocalPropertyOrGradleProperty("konsist.signingKey")
                val signingPassword = getLocalPropertyOrGradleProperty("konsist.signingPassword")

                if (signingKey != null && signingPassword != null) {
                    useInMemoryPgpKeys(
                        decodeBase64(signingKey),
                        decodeBase64(signingPassword),
                    )

                    sign(extensions.getByType(PublishingExtension::class.java).publications.getByName(konsistPublicationName))
                } else {
                    if (signingKey == null) {
                        logger.warn("Property 'konsist.signingKey' is not provided. Skipping signing.")
                    } else if (signingPassword == null) {
                        logger.warn("Property 'signingPassword' is not provided. Skipping signing.")
                    }
                }
            }
        }
    }

    private fun Project.setCredentialsFromGradleProperties(): MavenArtifactRepository.() -> Unit = {
        val ossrhUsername = getLocalPropertyOrGradleProperty("konsist.ossrhUsername")
        val ossrhPassword = getLocalPropertyOrGradleProperty("konsist.ossrhPassword")

        credentials {
            username = ossrhUsername
            password = ossrhPassword
        }
    }

    private fun decodeBase64(string: String) = String(Base64.getDecoder().decode(string)).trim()
}
