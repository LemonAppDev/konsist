package com.lemonappdev.konsist.core.filesystem.rootprovider

import com.lemonappdev.konsist.core.filesystem.PathVerifier

class GradleProjectRootDirResolver(
    pathVerifier: PathVerifier,
) : ProjectRootDirResolver(pathVerifier) {
    override val paths = setOf(
        "gradlew",
        "gradlew.bat",
        "./gradle/wrapper/gradle-wrapper.jar",
        "./gradle/wrapper/gradle-wrapper.properties",
    )
}
