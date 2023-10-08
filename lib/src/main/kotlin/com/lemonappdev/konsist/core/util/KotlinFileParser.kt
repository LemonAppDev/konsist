package com.lemonappdev.konsist.core.util

import com.intellij.openapi.util.Disposer
import com.intellij.psi.PsiManager
import com.intellij.testFramework.LightVirtualFile
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationCore
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.ext.isKotlinFile
import com.lemonappdev.konsist.core.ext.isKotlinSnippetFile
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.psi.KtFile
import java.io.File

object KotlinFileParser {
    const val KOTLIN_SNIPPET_FILE_EXTENSION = ".kttxt"
    const val KOTLIN_FILE_EXTENSION = ".kt"

    private val project by lazy {
        KotlinCoreEnvironment.createForProduction(
            Disposer.newDisposable(),
            CompilerConfiguration(),
            EnvironmentConfigFiles.JVM_CONFIG_FILES,
        ).project
    }

    private val psiManager by lazy {
        PsiManager.getInstance(project)
    }

    @Suppress("detekt.TooGenericExceptionCaught")
    private fun getKtFile(file: File): KtFile {
        require(file.isKotlinFile || file.isKotlinSnippetFile) { "File must be a Kotlin file" }

        try {
            val fileContent = file
                .readText()
                .replace(Regex(EndOfLine.WINDOWS.value), EndOfLine.UNIX.value)

            // Tests are using code snippets with txt extension that is messing up with Kotlin file parsing
            val filePath = file.path.replace(KOTLIN_SNIPPET_FILE_EXTENSION, KOTLIN_FILE_EXTENSION)
            val lightVirtualFile = LightVirtualFile(filePath, KotlinFileType.INSTANCE, fileContent)
            val psiFile = psiManager.findFile(lightVirtualFile)
            return psiFile as KtFile
        } catch (e: Exception) {
            throw KoInternalException("Failed to parse Kotlin file: ${file.path}", e)
        }
    }

    fun getKoFile(file: File): KoFileDeclaration {
        val ktFile = getKtFile(file)
        return KoFileDeclarationCore(ktFile)
    }
}
