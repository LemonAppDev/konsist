package com.lemonappdev.konsist.core.filesystem.rootprovider

import com.lemonappdev.konsist.core.filesystem.PathVerifier

class GitProjectRootDirResolver(
    pathVerifier: PathVerifier,
) : ProjectRootDirResolver(pathVerifier) {
    /*
    Random files and directory selected from fresh '.git' repository (creates using the 'git init' command)
    Description of the '.git' directory https://githowto.com/git_internals_git_directory
     */
    override val paths = setOf(
        "./git/config",
        "./git/HEAD",
        "./git/refs",
    )
}
