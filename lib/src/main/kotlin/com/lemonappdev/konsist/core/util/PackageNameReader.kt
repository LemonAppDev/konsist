package com.lemonappdev.konsist.core.util

import org.jetbrains.kotlin.com.intellij.psi.TokenType
import org.jetbrains.kotlin.com.intellij.psi.tree.TokenSet
import org.jetbrains.kotlin.lexer.KotlinLexer
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtPsiUtil
import java.io.File

/**
 * Reads the package name of the Kotlin file from its package directive without parsing the file, which is much faster
 * and does not keep the PSI tree in memory.
 *
 * The package directive is placed at the beginning of the file (only comments and file annotations can be placed
 * before it), so usually only the beginning of the file is read.
 */
internal object PackageNameReader {
    private const val UTF8_BOM = "\uFEFF"

    private const val PREFIX_SIZE_IN_BYTES = 4096

    private val openingBrackets =
        TokenSet.create(KtTokens.LPAR, KtTokens.LBRACKET, KtTokens.LBRACE, KtTokens.LONG_TEMPLATE_ENTRY_START)

    private val closingBrackets =
        TokenSet.create(KtTokens.RPAR, KtTokens.RBRACKET, KtTokens.RBRACE, KtTokens.LONG_TEMPLATE_ENTRY_END)

    // Package directive is placed before the imports and declarations
    private val tokensAfterPackageDirective =
        TokenSet.create(
            KtTokens.IMPORT_KEYWORD,
            KtTokens.CLASS_KEYWORD,
            KtTokens.INTERFACE_KEYWORD,
            KtTokens.OBJECT_KEYWORD,
            KtTokens.FUN_KEYWORD,
            KtTokens.VAL_KEYWORD,
            KtTokens.VAR_KEYWORD,
            KtTokens.TYPE_ALIAS_KEYWORD,
        )

    /**
     * Returns the package name of the file (empty for the file without package directive) or `null` when the file
     * can't be read.
     */
    fun getPackageName(file: File): String? =
        runCatching {
            val prefix = file.inputStream().use { it.readNBytes(PREFIX_SIZE_IN_BYTES) }
            val isWholeFile = prefix.size < PREFIX_SIZE_IN_BYTES

            getPackageName(prefix.decodeToString(), isWholeFile)
                ?: getPackageName(file.readText(), isWholeText = true)
        }.getOrNull()

    /**
     * Returns the package name declared in the [text] (empty for the text without package directive) or `null` when
     * the [text] is only the beginning of the file and it ends before the package name is known.
     */
    internal fun getPackageName(
        text: String,
        isWholeText: Boolean,
    ): String? {
        val lexer = KotlinLexer()
        lexer.start(text.removePrefix(UTF8_BOM))

        var bracketDepth = 0

        while (lexer.tokenType != null) {
            val tokenType = lexer.tokenType

            when {
                lexer.isAtEndOfIncompleteText(isWholeText) -> return null

                openingBrackets.contains(tokenType) -> bracketDepth++

                closingBrackets.contains(tokenType) -> bracketDepth--

                // e.g. class literal in the file annotation argument `@file:UseSerializers(DateSerializer::class)`
                bracketDepth > 0 -> Unit

                tokenType == KtTokens.PACKAGE_KEYWORD -> return readQualifiedName(lexer, isWholeText)

                tokensAfterPackageDirective.contains(tokenType) -> return ""
            }

            lexer.advance()
        }

        return ""
    }

    /*
     * Reads identifiers separated by dots (e.g. `com.app`) following the package keyword.
     */
    private fun readQualifiedName(
        lexer: KotlinLexer,
        isWholeText: Boolean,
    ): String? {
        val segments = mutableListOf<String>()
        var expectedTokenType = KtTokens.IDENTIFIER

        while (true) {
            lexer.advanceSkippingWhitespacesAndComments()

            when {
                lexer.isAtEndOfIncompleteText(isWholeText) -> return null
                lexer.tokenType != expectedTokenType -> return segments.joinToString(".")
                expectedTokenType == KtTokens.IDENTIFIER -> segments += KtPsiUtil.unquoteIdentifier(lexer.tokenText)
            }

            expectedTokenType = if (expectedTokenType == KtTokens.IDENTIFIER) KtTokens.DOT else KtTokens.IDENTIFIER
        }
    }

    /*
     * Token ending at the end of the incomplete text may be incomplete (e.g. `pack` instead of `package`).
     */
    private fun KotlinLexer.isAtEndOfIncompleteText(isWholeText: Boolean): Boolean = !isWholeText && tokenEnd >= bufferEnd

    private fun KotlinLexer.advanceSkippingWhitespacesAndComments() {
        do {
            advance()
        } while (tokenType == TokenType.WHITE_SPACE || KtTokens.COMMENTS.contains(tokenType))
    }
}
