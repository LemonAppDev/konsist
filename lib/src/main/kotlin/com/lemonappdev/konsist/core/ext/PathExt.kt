package com.lemonappdev.konsist.core.ext

import java.io.File

val sep: String = File.separator

fun String.toNormalizedPath(): String = replace("/", File.separator)
