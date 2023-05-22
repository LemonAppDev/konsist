package com.lemonappdev.konsist.core.filesystem.rootprovider

import com.lemonappdev.konsist.core.filesystem.PathVerifier

class GitProjectRootDirResolver(
    pathVerifier: PathVerifier,
) : ProjectRootDirResolver(pathVerifier) {
    /*
    A few files and directories selected from fresh '.git' directory. This directory stores config files for the git repository
    (created using the 'git init' command).

    Description of the '.git' directory https://githowto.com/git_internals_git_directory
     */
    override val paths = setOf(
        "./git/config",
        "./git/HEAD",
        "./git/refs",
    )
}
