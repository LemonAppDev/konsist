package com.lemonappdev.konsist.helper.ext

import com.lemonappdev.konsist.core.util.PathHelper
import java.io.File

internal val fileSeparator: String = PathHelper.sep

internal fun String.toOsSeparator(): String = PathHelper.toOsSeparator(this)

internal fun List<String>.toOsSeparator(): List<String> = PathHelper.toOsSeparator(this)
