package com.lemon.konsist.util

import com.intellij.openapi.util.Disposer
import com.intellij.psi.PsiManager
import com.intellij.testFramework.LightVirtualFile
import com.lemon.konsist.core.declaration.KoFile
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.psi.KtFile
import java.io.File

object KotlinFileParser {
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

    private fun getKtFile(file: File): KtFile {
        // Tests are using code snippets with txt extension
//        require(file.isKotlinFile) { "File must be a Kotlin file" }

        val text = file.readText()

        // Tests are using code snippets with txt extension that is messing up with Kotlin file parsing
        val filePath = file.path.replace(".kttxt", "")
        val lightVirtualFile = LightVirtualFile(filePath, KotlinFileType.INSTANCE, text)
        val psiFile = psiManager.findFile(lightVirtualFile)
        return psiFile as KtFile
    }

    fun getKonsistFile(file: File): KoFile {
        val ktFile = getKtFile(file)
        return KoFile(ktFile)
    }
}
