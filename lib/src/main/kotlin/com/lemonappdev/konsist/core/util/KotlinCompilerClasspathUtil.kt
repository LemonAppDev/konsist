package com.lemonappdev.konsist.core.util

import java.io.File
import java.net.URL

/**
 * Konsist parses files with `kotlin-compiler-embeddable` (IntelliJ classes relocated to `org.jetbrains.kotlin.com.intellij`).
 * Other Kotlin compiler copies (e.g. `kotlin-compiler` or Android Lint `intellij-core`) contain classes with the same names
 * but non-relocated IntelliJ dependencies. When such copy is loaded first, parsing fails with errors like `ClassCastException`,
 * `NoClassDefFoundError` or `NoSuchMethodError`, depending on the classpath order.
 */
internal object KotlinCompilerClasspathUtil {
    private const val KOTLIN_FILE_TYPE_RESOURCE = "org/jetbrains/kotlin/idea/KotlinFileType.class"
    private const val JAR_PROTOCOL = "jar"
    private const val JAR_SEPARATOR = "!/"
    private const val KNOWN_ISSUES_URL = "https://docs.konsist.lemonappdev.com/help/known-issues"

    /**
     * Returns error message describing Kotlin compiler classpath conflict or null if a single Kotlin compiler is found.
     */
    fun getConflictMessage(classLoader: ClassLoader?): String? {
        val locations =
            classLoader
                ?.getResources(KOTLIN_FILE_TYPE_RESOURCE)
                ?.toList()
                ?.map { getLocation(it) }
                ?.distinct()
                .orEmpty()

        if (locations.size < 2) {
            return null
        }

        return buildString {
            appendLine("Multiple Kotlin compilers found on the classpath:")
            locations.forEach { appendLine(" - $it") }
            appendLine("Konsist requires only 'kotlin-compiler-embeddable'.")
            appendLine("Remove other Kotlin compilers from the classpath (e.g. move Konsist tests to a separate module).")
            append("See $KNOWN_ISSUES_URL")
        }
    }

    private fun getLocation(resourceUrl: URL): String =
        runCatching {
            if (resourceUrl.protocol == JAR_PROTOCOL) {
                File(URL(resourceUrl.path.substringBefore(JAR_SEPARATOR)).toURI()).path
            } else {
                File(resourceUrl.toURI()).path.removeSuffix(File(KOTLIN_FILE_TYPE_RESOURCE).path).removeSuffix(File.separator)
            }
        }.getOrDefault(resourceUrl.toString())
}
