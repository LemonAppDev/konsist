//package com.lemonappdev.konsist.core.verify
//
//import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
//import com.lemonappdev.konsist.core.declaration.KoFileDeclarationImpl
//import com.lemonappdev.konsist.core.exception.KoException
//import com.lemonappdev.konsist.core.exception.KoInternalException
//
//fun <E : KoFileDeclaration> Sequence<E>.assert(function: (E) -> Boolean?) {
//    assert(function, true)
//}
//
//fun <E : KoFileDeclaration> Sequence<E>.assertNot(function: (E) -> Boolean?) {
//    assert(function, false)
//}
//
//@Suppress("detekt.ThrowsCount")
//private fun <E : KoFileDeclaration> Sequence<E>.assert(function: (E) -> Boolean?, positiveCheck: Boolean) {
//    var lastFile: KoFileDeclaration? = null
//
//    try {
//        val localList = this.toList()
//
//        checkIfLocalListIsEmpty(localList, "File", getTestMethodNameFromFourthIndex())
//
//        val notSuppressedFiles = localList
//            .filterNot { checkIfSuppressed(it, getTestMethodNameFromFifthIndex()) }
//
//        val result = notSuppressedFiles.groupBy {
//            lastFile = it
//            function(it)
//        }
//
//        getResult(notSuppressedFiles, result, positiveCheck, getTestMethodNameFromFifthIndex())
//    } catch (e: KoException) {
//        throw e
//    } catch (@Suppress("detekt.TooGenericExceptionCaught") e: Exception) {
//        throw KoInternalException(e.message.orEmpty(), e, lastFile)
//    }
//}
//
//private fun checkIfSuppressed(file: KoFileDeclaration, testMethodName: String): Boolean {
//    val annotationParameter = (file as KoFileDeclarationImpl)
//        .annotations
//        .firstOrNull { it.name == "Suppress" }
//        ?.text
//        ?.removePrefix("@file:Suppress(\"")
//        ?.removeSuffix("\")")
//
//    return annotationParameter == testMethodName || annotationParameter == "konsist.$testMethodName"
//}
