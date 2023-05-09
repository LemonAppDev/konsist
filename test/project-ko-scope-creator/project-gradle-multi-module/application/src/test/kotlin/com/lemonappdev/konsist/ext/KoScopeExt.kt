package com.lemonappdev.konsist.ext

import com.lemonappdev.konsist.api.KoScope

fun KoScope.mapToFilePaths() = files()
    .toList()
    .map { it.filePath }
