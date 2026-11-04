package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.cache.KoFileDeclarationCache
import com.lemonappdev.konsist.core.util.FileExtension
import com.lemonappdev.konsist.core.util.KotlinFileParser
import java.io.File

// Name is checked first, because isFile reads file attributes from the file system
internal val File.isKotlinFile: Boolean get() = name.endsWith(FileExtension.KOTLIN) && isFile

internal val File.isKotlinSnippetFile: Boolean get() = name.endsWith(FileExtension.KOTLIN_TEST_SNIPPET) && isFile

internal fun File.toKoFile(): KoFileDeclaration = KoFileDeclarationCache.getOrCreateInstance(this, KotlinFileParser::getKtFile)
