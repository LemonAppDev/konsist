package com.lemonappdev.konsist.core.filesystem.rootprovider

import com.lemonappdev.konsist.core.filesystem.PathVerifier

class MavenProjectRootDirProvider(
    pathVerifier: PathVerifier,
) : ProjectRootDirProvider(pathVerifier) {
    override val paths = setOf(
        "mvnw",
        "mvnw.cmd",
        "./mvn/wrapper/maven-wrapper.jar",
        "./mvn/wrapper/maven-wrapper.properties",
    )
}
