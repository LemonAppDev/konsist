package com.lemonappdev.konsist.core.filesystem

import com.lemonappdev.konsist.core.filesystem.rootprovider.GitProjectRootDirResolver
import com.lemonappdev.konsist.core.filesystem.rootprovider.GradleProjectRootDirResolver
import com.lemonappdev.konsist.core.filesystem.rootprovider.MavenProjectRootDirResolver

class ProjectRootDirProviderFactory(
    pathVerifier: PathVerifier,
) {
    private val providers =
        listOf(
            GradleProjectRootDirResolver(pathVerifier),
            MavenProjectRootDirResolver(pathVerifier),
            GitProjectRootDirResolver(pathVerifier),
        )

    fun create() = providers
}
