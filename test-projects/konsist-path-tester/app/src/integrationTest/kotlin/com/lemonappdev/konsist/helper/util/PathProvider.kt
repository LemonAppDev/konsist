package com.lemonappdev.konsist.helper.util

import com.lemonappdev.konsist.helper.ext.toCanonicalPath
import java.io.File

object PathProvider {
    val projectRootDirectory = File("")
        .absoluteFile
        .path
        .replace(File.separator, "/")
        .dropLastWhile { it != '/' }
        .dropLastWhile { it != '/' }
        .dropLast(1)

    val rootMainSourceSetDirectory = "$projectRootDirectory/src/main/kotlin/com/lemonappdev".toCanonicalPath()

    val rootMainSourceSetProjectDirectory = "src/main/kotlin/com/lemonappdev".toCanonicalPath()

    val rootTestSourceSetDirectory = "$projectRootDirectory/src/test/kotlin/com/lemonappdev".toCanonicalPath()

    val rootTestSourceSetProjectDirectory = "src/test/kotlin/com/lemonappdev".toCanonicalPath()

    val appMainSourceSetDirectory = "$projectRootDirectory/app/src/main/kotlin/com/lemonappdev".toCanonicalPath()

    val appMainSourceSetProjectDirectory = "app/src/main/kotlin/com/lemonappdev".toCanonicalPath()

    val appIntegrationTestSourceSetDirectory = "$projectRootDirectory/app/src/integrationTest/kotlin/com/lemonappdev".toCanonicalPath()

    val appIntegrationTestSourceSetProjectDirectory = "app/src/integrationTest/kotlin/com/lemonappdev".toCanonicalPath()

    val dataMainSourceSetDirectory = "$projectRootDirectory/data/src/main/kotlin/com/lemonappdev".toCanonicalPath()

    val dataMainSourceSetProjectDirectory = "data/src/main/kotlin/com/lemonappdev".toCanonicalPath()

    val dataTestSourceSetDirectory = "$projectRootDirectory/data/src/test/kotlin/com/lemonappdev".toCanonicalPath()

    val dataTestSourceSetProjectDirectory = "data/src/test/kotlin/com/lemonappdev".toCanonicalPath()
}
