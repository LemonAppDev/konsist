package com.lemonappdev.konsist.core.filesystem.rootprovider

import com.lemonappdev.konsist.core.ext.toOsSeparator
import com.lemonappdev.konsist.core.filesystem.PathVerifier

class MavenProjectRootDirResolver(
    pathVerifier: PathVerifier,
) : ProjectRootDirResolver(pathVerifier) {
    override val paths =
        setOf(
            "mvnw",
            "mvnw.cmd",
            "/.mvn/wrapper/maven-wrapper.jar".toOsSeparator(),
            "/.mvn/wrapper/maven-wrapper.properties".toOsSeparator(),
        )
}
