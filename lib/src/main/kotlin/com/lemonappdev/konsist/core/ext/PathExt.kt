package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.util.PathHelper.toMacOsSeparator
import com.lemonappdev.konsist.core.util.PathHelper.toOsSeparator
import java.io.File

val sep: String = File.separator

internal fun String.toOsSeparator(): String = toOsSeparator(this)

internal fun String.toMacOsSeparator(): String = toMacOsSeparator(this)
