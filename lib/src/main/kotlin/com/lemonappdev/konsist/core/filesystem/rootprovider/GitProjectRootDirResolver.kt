package com.lemonappdev.konsist.core.filesystem.rootprovider

import com.lemonappdev.konsist.core.ext.toOsSeparator
import com.lemonappdev.konsist.core.filesystem.PathVerifier

class GitProjectRootDirResolver(
    pathVerifier: PathVerifier,
) : ProjectRootDirResolver(pathVerifier) {
    /*
    A few files and directories selected from fresh '.git' directory. This directory represents standard directory layout for the git
    repository (it can be created using the 'git init' command).

    Description of the '.git' directory https://githowto.com/git_internals_git_directory
     */
    override val paths = setOf(
        "./.git/config".toOsSeparator(),
        "./.git/HEAD".toOsSeparator(),
        "./.git/refs".toOsSeparator(),
    )
}
