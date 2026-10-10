package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationCore
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.ext.isKotlinFile
import com.lemonappdev.konsist.core.ext.isKotlinSnippetFile
import com.lemonappdev.konsist.core.util.FileExtension.KOTLIN
import com.lemonappdev.konsist.core.util.FileExtension.KOTLIN_TEST_SNIPPET
import com.lemonappdev.konsist.core.util.PathUtil.toMacOsSeparator
import org.jetbrains.kotlin.CoreEnvironmentDeprecation
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.com.intellij.openapi.util.Disposer
import org.jetbrains.kotlin.com.intellij.psi.PsiManager
import org.jetbrains.kotlin.com.intellij.testFramework.LightVirtualFile
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.psi.KtFile
import java.io.File

object KotlinFileParser {
    @OptIn(CoreEnvironmentDeprecation::class, CompilerConfiguration.Internals::class)
    private val project by lazy {
        KotlinCoreEnvironment
            .createForProduction(
                Disposer.newDisposable(),
                CompilerConfiguration().apply { setExtensionsStorageIfSupported() },
                EnvironmentConfigFiles.JVM_CONFIG_FILES,
            ).project
    }

    /**
     * Kotlin 2.4+ requires extension storage in the compiler configuration, while Kotlin 2.3 has no API for it.
     * Setter is resolved via reflection to support both `kotlin-compiler-embeddable` versions at runtime
     * (e.g. Spring Boot dependency management aligns it with the project Kotlin version).
     */
    @OptIn(ExperimentalCompilerApi::class)
    private fun CompilerConfiguration.setExtensionsStorageIfSupported() {
        val setExtensionsStorage =
            runCatching {
                Class
                    .forName("org.jetbrains.kotlin.cli.FrontendConfigurationKeysKt")
                    .getMethod(
                        "setExtensionsStorage",
                        CompilerConfiguration::class.java,
                        CompilerPluginRegistrar.ExtensionStorage::class.java,
                    )
            }.getOrNull() ?: return

        setExtensionsStorage.invoke(null, this, CompilerPluginRegistrar.ExtensionStorage())
    }

    private val psiManager by lazy {
        PsiManager.getInstance(project)
    }

    @Suppress("detekt.TooGenericExceptionCaught")
    private fun getKtFile(file: File): KtFile {
        require(file.isKotlinFile || file.isKotlinSnippetFile) { "File must be a Kotlin file: ${toMacOsSeparator(file.path)}" }

        try {
            val fileContent =
                file
                    .readText()
                    .replace(Regex(EndOfLine.WINDOWS.value), EndOfLine.UNIX.value)

            // Tests are using code snippets with txt extension that is messing up with Kotlin file parsing
            val filePath = file.path.replace(KOTLIN_TEST_SNIPPET, KOTLIN)
            val lightVirtualFile = LightVirtualFile(filePath, KotlinFileType.INSTANCE, fileContent)
            val psiFile = psiManager.findFile(lightVirtualFile)
            return psiFile as KtFile
        } catch (e: Exception) {
            throw KoInternalException("Failed to parse Kotlin file: ${toMacOsSeparator(file.path)}", e)
        }
    }

    fun getKoFile(file: File): KoFileDeclaration {
        val ktFile = getKtFile(file)
        return KoFileDeclarationCore(ktFile)
    }
}
