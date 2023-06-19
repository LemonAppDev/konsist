package com.lemonappdev.konsist.testdata

import com.lemonappdev.konsist.core.util.PathHelper

internal fun String.toOsSeparator(): String = PathHelper.toOsSeparator(this)
