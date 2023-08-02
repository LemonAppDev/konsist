package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

private const val INDEX_FOUR = 4
private const val INDEX_FIVE = 5
private const val INDEX_SEVEN = 7

/**
 * In this call stack hierarchy test name is at index 4.
 */
internal fun getTestMethodNameFromFourthIndex() = getTestMethodName(INDEX_FOUR)

/**
 * In this call stack hierarchy test name is at index 5.
 */
internal fun getTestMethodNameFromFifthIndex() = getTestMethodName(INDEX_FIVE)

/**
 * In this call stack hierarchy test name is at index 7.
 */
internal fun getTestMethodNameFromSeventhIndex() = getTestMethodName(INDEX_SEVEN)

private fun getTestMethodName(index: Int): String = Thread.currentThread().stackTrace[index].methodName
