package com.lemonappdev.konsist.core.ext

import java.io.File

internal fun String.toNormalizedPath(): String = replace("/", File.separator)

internal fun List<String>.toNormalizedPaths(): List<String> = map { it.replace("/", File.separator) }
