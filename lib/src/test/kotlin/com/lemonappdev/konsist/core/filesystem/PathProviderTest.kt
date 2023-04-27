package com.lemonappdev.konsist.core.filesystem

import com.lemonappdev.konsist.core.filesystem.rootprovider.ProjectRootDirectoryProvider
import io.mockk.mockk

class PathProviderTest {

    private val koFileFactory = mockk<KoFileFactory>()
    private val projectRootDirectoryProviders = mockk<List<ProjectRootDirectoryProvider>>()

    private val sut = PathProvider(
        koFileFactory,
        projectRootDirectoryProviders,
    )
//
//    @Test
//    fun
}
