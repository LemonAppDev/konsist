package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.util.PathUtil.toMacOsSeparator
import com.lemonappdev.konsist.core.util.PathUtil.toOsSeparator

internal fun String.toOsSeparator(): String = toOsSeparator(this)

internal fun String.toMacOsSeparator(): String = toMacOsSeparator(this)
