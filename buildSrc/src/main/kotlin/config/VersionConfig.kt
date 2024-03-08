package config

import java.util.Properties

object VersionConfig {
    private val versions: Properties by lazy {
        val versionsProps = Properties()
        try {
            versionsProps.load(this::class.java.classLoader.getResourceAsStream("versions.toml"))
        } catch (e: Exception) {
            println("Error reading versions.toml: ${e.message}")
        }
        versionsProps
    }

    val ktlintCliVersion: String by lazy {
        println("AAA ${versions.getProperty("versions.ktlintCliVersion")}")
        checkNotNull(versions.getProperty("versions.ktlintCliVersion")) { "ktlintVersion is not defined in libs.versions.toml" }
    }
}
