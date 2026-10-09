package com.lemonappdev.konsist.helper.util

import com.lemonappdev.konsist.helper.ext.toOsSeparator
import java.io.File

object PathProvider {
    val projectRootDirectory = File("")
        .absoluteFile
        .path
        .replace(File.separator, "/")
        .dropLastWhile { it != '/' }
        .dropLastWhile { it != '/' }
        .dropLast(1)

    val rootMainSourceSetDirectory = "$projectRootDirectory/src/main/kotlin/com/lemonappdev".toOsSeparator()

    val rootMainSourceSetProjectDirectory = "src/main/kotlin/com/lemonappdev".toOsSeparator()

    val rootTestSourceSetDirectory = "$projectRootDirectory/src/test/kotlin/com/lemonappdev".toOsSeparator()

    val rootTestSourceSetProjectDirectory = "src/test/kotlin/com/lemonappdev".toOsSeparator()

    val appMainSourceSetDirectory = "$projectRootDirectory/app/src/main/kotlin/com/lemonappdev".toOsSeparator()

    val appMainSourceSetProjectDirectory = "app/src/main/kotlin/com/lemonappdev".toOsSeparator()

    val appIntegrationTestSourceSetDirectory = "$projectRootDirectory/app/src/integrationTest/kotlin/com/lemonappdev".toOsSeparator()

    val appIntegrationTestSourceSetProjectDirectory = "app/src/integrationTest/kotlin/com/lemonappdev".toOsSeparator()

    val dataMainSourceSetDirectory = "$projectRootDirectory/data/src/main/kotlin/com/lemonappdev".toOsSeparator()

    val dataMainSourceSetProjectDirectory = "data/src/main/kotlin/com/lemonappdev".toOsSeparator()

    val dataTestSourceSetDirectory = "$projectRootDirectory/data/src/test/kotlin/com/lemonappdev".toOsSeparator()

    val dataTestSourceSetProjectDirectory = "data/src/test/kotlin/com/lemonappdev".toOsSeparator()
}
