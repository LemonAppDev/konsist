package com.lemonappdev.konsist.helper.util

import com.lemonappdev.konsist.helper.ext.toOsSeparator
import java.io.File

object PathProvider {
    val projectRootDirectory =
        File("")
            .absoluteFile
            .path
            .replace(File.separator, "/")
            .dropLastWhile { it != '/' }
            .dropLastWhile { it != '/' }
            .dropLast(1)

    val rootMainSourceSetDirectory = "$projectRootDirectory/src/main/kotlin/com/lemonappdev".toOsSeparator()

    const val ROOT_MAIN_SOURCE_SET_PROJECT_DIRECTORY = "src/main/kotlin/com/lemonappdev"

    val rootTestSourceSetDirectory = "$projectRootDirectory/src/test/kotlin/com/lemonappdev".toOsSeparator()

    const val ROOT_TEST_SOURCE_SET_PROJECT_DIRECTORY = "src/test/kotlin/com/lemonappdev"

    val appMainSourceSetDirectory = "$projectRootDirectory/app/src/main/kotlin/com/lemonappdev".toOsSeparator()

    const val APP_MAIN_SOURCE_SET_PROJECT_DIRECTORY = "app/src/main/kotlin/com/lemonappdev"

    val appIntegrationTestSourceSetDirectory = "$projectRootDirectory/app/src/integrationTest/kotlin/com/lemonappdev".toOsSeparator()

    const val APP_INTEGRATION_TEST_SOURCE_SET_PROJECT_DIRECTORY = "app/src/integrationTest/kotlin/com/lemonappdev"

    val dataMainSourceSetDirectory = "$projectRootDirectory/data/src/main/kotlin/com/lemonappdev".toOsSeparator()

    const val DATA_MAIN_SOURCE_SET_PROJECT_DIRECTORY = "data/src/main/kotlin/com/lemonappdev"

    val dataTestSourceSetDirectory = "$projectRootDirectory/data/src/test/kotlin/com/lemonappdev".toOsSeparator()

    const val DATA_TEST_SOURCE_SET_PROJECT_DIRECTORY = "data/src/test/kotlin/com/lemonappdev"
}
