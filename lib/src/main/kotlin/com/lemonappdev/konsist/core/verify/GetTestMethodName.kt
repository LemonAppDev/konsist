package com.lemonappdev.konsist.core.verify

private const val INDEX_FOUR = 4
private const val INDEX_FIVE = 5
private const val INDEX_NINE = 9

private const val DEFAULT_ARGUMENTS_METHOD_SUFFIX = "\$default"
private const val LAMBDA_METHOD_INFIX = "\$lambda"

/**
 * In this call stack hierarchy assert method name is at index 4.
 */
internal fun getAssertMethodNameFromFourthIndex(): String = getStackTraceElement(INDEX_FOUR).methodName

/**
 * In this call stack hierarchy test name is at index 5.
 */
internal fun getTestMethodNameFromFifthIndex(): String? = getTestMethodName(INDEX_FIVE)

/**
 * In this call stack hierarchy test name is at index 9.
 */
internal fun getTestMethodNameFromNinthIndex(): String? = getTestMethodName(INDEX_NINE)

private fun getStackTraceElement(index: Int): StackTraceElement =
    Thread
        .currentThread()
        .stackTrace[index]

/**
 * Frames of methods with default arguments (`assertTrue$default`) are placed between the assert and the test method.
 */
private fun getTestMethodName(index: Int): String? =
    Thread
        .currentThread()
        .stackTrace
        .drop(index)
        .first { !it.methodName.endsWith(DEFAULT_ARGUMENTS_METHOD_SUFFIX) }
        .toTestMethodName()

/**
 * Returns the name of the method that contains the assert call or null if it can't be determined from the call stack
 * (e.g. Kotest test is a suspend lambda declared in the spec constructor, so its frame is `MySpec$1$1.invokeSuspend`).
 */
private fun StackTraceElement.toTestMethodName(): String? {
    // Lambda compiled to an anonymous class e.g. `MyTest$myTest$1.invoke`. Methods of this class (including lambdas
    // declared inside it e.g. `MySpec$1$1.invokeSuspend$lambda$0`) are part of the method that declares the class.
    if (className.isAnonymousClassName()) {
        val enclosingName = className.split("$").dropLastWhile { it.isNumber() }.joinToString("$")

        if (!enclosingName.contains("$")) {
            return null
        }

        return getDeclaredMethodName(enclosingName.substringBeforeLast("$"), enclosingName.substringAfterLast("$"))
    }

    // Lambda compiled to a method e.g. JUnit 5 dynamic test `myTest$lambda$0$0` (`myTest$lambda-0` in Kotlin 1.x)
    val lambdaIndex = methodName.indexOf(LAMBDA_METHOD_INFIX)

    if (lambdaIndex > 0) {
        val sanitizedName = methodName.substring(0, lambdaIndex)
        return getDeclaredMethodName(className, sanitizedName) ?: sanitizedName
    }

    return methodName
}

/**
 * Kotlin replaces characters that are not valid in Java identifiers with `_` in synthetic names
 * (`my test` -> `my_test`), so declared methods are used to restore the original test name.
 */
private fun getDeclaredMethodName(
    className: String,
    sanitizedName: String,
): String? =
    runCatching { Class.forName(className, false, Thread.currentThread().contextClassLoader) }
        .getOrNull()
        ?.declaredMethods
        ?.map { it.name }
        ?.firstOrNull { it.sanitize() == sanitizedName }

private fun String.sanitize() = map { if (Character.isJavaIdentifierPart(it)) it else '_' }.joinToString("")

private fun String.isAnonymousClassName() = substringAfterLast("$", "").isNumber()

private fun String.isNumber() = isNotEmpty() && all { it.isDigit() }
