package com.lemonappdev.konsist.helper.ext

import com.lemonappdev.konsist.api.KoScope

fun KoScope.mapToFilePaths() = files()
    .toList()
    .map { it.path }
