package com.lemonappdev.konsist.core.filesystem.rootprovider

import com.lemonappdev.konsist.core.ext.toCanonicalPaths
import com.lemonappdev.konsist.core.filesystem.PathVerifier

class GradleProjectRootDirResolver(
    pathVerifier: PathVerifier,
) : ProjectRootDirResolver(pathVerifier) {
    override val paths = setOf(
        "gradlew",
        "gradlew.bat",
        "./gradle/wrapper/gradle-wrapper.jar".toCanonicalPaths(),
        "./gradle/wrapper/gradle-wrapper.properties".toCanonicalPaths(),
    )
}
