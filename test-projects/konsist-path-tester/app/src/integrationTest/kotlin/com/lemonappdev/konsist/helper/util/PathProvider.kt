package com.lemonappdev.konsist.helper.util

import java.io.File

object PathProvider {
    val projectRootDirectory = File("")
        .absoluteFile
        .path
        .dropLastWhile { it != '/' }
        .dropLastWhile { it != '/' }
        .dropLast(1)

    val rootMainSourceSetDirectory = "$projectRootDirectory/src/main/kotlin/com/lemonappdev"

    const val rootMainSourceSetProjectDirectory = "src/main/kotlin/com/lemonappdev"

    val rootTestSourceSetDirectory = "$projectRootDirectory/src/test/kotlin/com/lemonappdev"

    const val rootTestSourceSetProjectDirectory = "src/test/kotlin/com/lemonappdev"

    val appMainSourceSetDirectory = "$projectRootDirectory/app/src/main/kotlin/com/lemonappdev"

    const val appMainSourceSetProjectDirectory = "app/src/main/kotlin/com/lemonappdev"

    val appIntegrationTestSourceSetDirectory = "$projectRootDirectory/app/src/integrationTest/kotlin/com/lemonappdev"

    const val appIntegrationTestSourceSetProjectDirectory = "app/src/integrationTest/kotlin/com/lemonappdev"

    val dataMainSourceSetDirectory = "$projectRootDirectory/data/src/main/kotlin/com/lemonappdev"

    const val dataMainSourceSetProjectDirectory = "data/src/main/kotlin/com/lemonappdev"

    val dataTestSourceSetDirectory = "$projectRootDirectory/data/src/test/kotlin/com/lemonappdev"

    const val dataTestSourceSetProjectDirectory = "data/src/test/kotlin/com/lemonappdev"
}
