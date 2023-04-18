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
            name = "Local"
            url = uri("~/.m2/repository")

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
    var signingKey = base64Decode("signingKey")
    var signingPassword = base64Decode("signingPassword")

    @Suppress("Detekt.MaxLineLength")
    signingKey = """
        -----BEGIN PGP PRIVATE KEY BLOCK-----

        lIYEZD5QwRYJKwYBBAHaRw8BAQdALe3I9pSJ0+wwICGOboTiP2/nFvMlnENU49yj
        jGIJljf+BwMCbSIisXKjQVXyXRhiEAKK9vWeOXttGFrMcuxINy+8RUbbj4igLwXV
        lqeNEPIf+Iucx9m2L5KbCOOrcOkqnNAhUaBmrsclV+Rsfm1Cfuf2drQkTGVtb24g
        QXBwIERldiA8aWdvci53b2pkYUBnbWFpbC5jb20+iJMEExYKADsWIQQ1DF110Eql
        mcutIjSNUFnysZJ6mgUCZD5QwQIbAwULCQgHAgIiAgYVCgkICwIEFgIDAQIeBwIX
        gAAKCRCNUFnysZJ6mhBtAQDgj646El5+Nu2B1EXEVetRt0517uCXBINFsVKq0DYs
        kAEA3Pfk7jOGgjniKQW9B/gRL3x100srCoOXM1Ztf97ZmgyciwRkPlDBEgorBgEE
        AZdVAQUBAQdA8f9pWBKFWPRr6AoXjNQHHbee0HbAKU2OkxdiFQo9Gw4DAQgH/gcD
        An2Ub3PZpOvg8mDD6slGiv0aaIs1P/5YWz4JnaVWCfhi55tJe4qmb9XIK7O1g0Ng
        ZYCLzikTKmK9gxWHrrtstOcs7hYo/SMQMMlFYRU75ACIeAQYFgoAIBYhBDUMXXXQ
        SqWZy60iNI1QWfKxknqaBQJkPlDBAhsMAAoJEI1QWfKxknqaA5ABAJVje2MeixeX
        H6GXNmAjYMddcwPGgC4A0S9eAkP8z4yMAQCftWq4Pprdq7Z7Je2moRzHgTComEyQ
        OzLb64KEZ2IfCw==
        =vCyW
        -----END PGP PRIVATE KEY BLOCK-----
    """.trimIndent()
    signingPassword = "2perfection!BEASTgpg"

    useInMemoryPgpKeys(signingKey, signingPassword)

    // Sign all publications
    sign(publishing.publications[konsistPublicationName])
}

fun base64Decode(prop: String) = project.findProperty(prop)?.let {
    String(Base64.getDecoder().decode(it.toString())).trim()
}
