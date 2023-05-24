package com.lemonappdev.konsist.helper.ext

import com.lemonappdev.konsist.api.container.koscope

fun KoScope.mapToFilePaths() = files()
    .toList()
    .map { it.path }
