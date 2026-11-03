package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.verify.KoRule

/**
 * Returns the rule and the additional message displayed in the assertion error, or null when both are null.
 */
internal fun getCustomMessage(
    rule: KoRule?,
    additionalMessage: String?,
): String? =
    listOfNotNull(rule?.toMessage(), additionalMessage)
        .takeIf { it.isNotEmpty() }
        ?.joinToString("\n")

private fun KoRule.toMessage(): String =
    listOfNotNull(
        "RULE: $title",
        "WHY: $rationale",
        howToFix.toSection("HOW TO FIX: "),
        goodExample?.trimIndent().toSection("GOOD EXAMPLE:\n"),
        badExample?.trimIndent().toSection("BAD EXAMPLE:\n"),
    ).joinToString("\n")

private fun String?.toSection(label: String): String? = this?.takeIf { it.isNotBlank() }?.let { "$label$it" }
