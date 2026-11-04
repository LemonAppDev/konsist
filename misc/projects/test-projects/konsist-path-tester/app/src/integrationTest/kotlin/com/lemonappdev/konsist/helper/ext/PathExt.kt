package com.lemonappdev.konsist.helper.ext

import com.lemonappdev.konsist.core.util.PathUtil
import java.io.File

internal val fileSeparator: String = PathUtil.separator

internal fun String.toOsSeparator(): String = PathUtil.toOsSeparator(this)

internal fun List<String>.toOsSeparator(): List<String> = PathUtil.toOsSeparator(this)
