package com.lemonappdev.konsist.helper.ext

import com.lemonappdev.konsist.core.util.PathUtil

internal fun String.toOsSeparator(): String = PathUtil.toOsSeparator(this)
