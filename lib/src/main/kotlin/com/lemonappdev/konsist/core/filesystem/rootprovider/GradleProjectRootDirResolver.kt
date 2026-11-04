package com.lemonappdev.konsist.core.filesystem.rootprovider

import com.lemonappdev.konsist.core.ext.toOsSeparator
import com.lemonappdev.konsist.core.filesystem.PathVerifier
import java.io.File

class GradleProjectRootDirResolver(
    pathVerifier: PathVerifier,
) : ProjectRootDirResolver(pathVerifier) {
    override val paths =
        setOf(
            "gradlew",
            "gradlew.bat",
            "./gradle/wrapper/gradle-wrapper.jar".toOsSeparator(),
            "./gradle/wrapper/gradle-wrapper.properties".toOsSeparator(),
        )

    /*
    Subproject may contain its own Gradle wrapper (e.g. 'app/gradlew'), so the root directory must also contain
    a settings file. Directory without a settings file is skipped and the search continues in parent directories.
     */
    private val settingsPaths =
        setOf(
            "settings.gradle",
            "settings.gradle.kts",
        )

    override fun getProjectRootDir(file: File): File? =
        super.getProjectRootDir(file)?.takeIf { dir -> settingsPaths.any { pathVerifier.verifyPathIfExists(dir, it) } }
}
