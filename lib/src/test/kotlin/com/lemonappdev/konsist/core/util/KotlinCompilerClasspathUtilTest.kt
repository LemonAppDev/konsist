package com.lemonappdev.konsist.core.util

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.net.URLClassLoader
import java.util.jar.JarEntry
import java.util.jar.JarOutputStream

class KotlinCompilerClasspathUtilTest {
    @Test
    fun `should return null when single kotlin compiler is on classpath`(
        @TempDir tempDir: File,
    ) {
        // given
        val compilerJar = createCompilerJar(tempDir, "kotlin-compiler-embeddable.jar")
        val classLoader = URLClassLoader(arrayOf(compilerJar.toURI().toURL()), null)

        // when
        val sut = KotlinCompilerClasspathUtil.getConflictMessage(classLoader)

        // then
        sut.shouldBeNull()
    }

    @Test
    fun `should return null when class loader is null`() {
        // when
        val sut = KotlinCompilerClasspathUtil.getConflictMessage(null)

        // then
        sut.shouldBeNull()
    }

    @Test
    fun `should return conflict message when multiple kotlin compilers are on classpath`(
        @TempDir tempDir: File,
    ) {
        // given
        val compilerJar = createCompilerJar(tempDir, "kotlin-compiler.jar")
        val compilerEmbeddableJar = createCompilerJar(tempDir, "kotlin-compiler-embeddable.jar")
        val compilerClassesDir = File(tempDir, "classes")
        File(compilerClassesDir, KOTLIN_FILE_TYPE_RESOURCE).apply {
            parentFile.mkdirs()
            createNewFile()
        }
        val urls = listOf(compilerJar, compilerEmbeddableJar, compilerClassesDir).map { it.toURI().toURL() }
        val classLoader = URLClassLoader(urls.toTypedArray(), null)

        // when
        val sut = KotlinCompilerClasspathUtil.getConflictMessage(classLoader)

        // then
        sut shouldBeEqualTo
            """
            Multiple Kotlin compilers found on the classpath:
             - ${compilerJar.path}
             - ${compilerEmbeddableJar.path}
             - ${compilerClassesDir.path}
            Konsist requires only 'kotlin-compiler-embeddable'.
            Remove other Kotlin compilers from the classpath (e.g. move Konsist tests to a separate module).
            See https://docs.konsist.lemonappdev.com/help/known-issues
            """.trimIndent()
    }

    private fun createCompilerJar(
        dir: File,
        name: String,
    ): File =
        File(dir, name).apply {
            JarOutputStream(outputStream()).use { it.putNextEntry(JarEntry(KOTLIN_FILE_TYPE_RESOURCE)) }
        }

    companion object {
        private const val KOTLIN_FILE_TYPE_RESOURCE = "org/jetbrains/kotlin/idea/KotlinFileType.class"
    }
}
