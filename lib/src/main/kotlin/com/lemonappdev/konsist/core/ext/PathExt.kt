package com.lemonappdev.konsist.core.ext

import java.io.File
import java.nio.file.Paths

val sep: String = File.separator

fun String.toCanonicalPaths(): String = Paths.get(this).toString()
