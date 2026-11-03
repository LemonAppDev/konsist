package com.lemonappdev.konsist.api.verify

/**
 * Describes the rule checked by an assertion. When the assertion fails, the rule is displayed in the error message,
 * so a developer (or an AI agent) knows what the rule checks, why it exists and how to fix the violation.
 *
 * ```kotlin
 * Konsist
 *     .scopeFromProject()
 *     .classes()
 *     .withNameEndingWith("ViewModel")
 *     .assertTrue(
 *         rule = KoRule(
 *             title = "ViewModels must extend BaseViewModel.",
 *             rationale = "Lifecycle handling lives in BaseViewModel.",
 *             howToFix = "Change the parent class to BaseViewModel.",
 *             goodExample = "class LoginViewModel : BaseViewModel()",
 *             badExample = "class LoginViewModel : ViewModel()",
 *         ),
 *     ) { it.hasParentClassWithName("BaseViewModel") }
 * ```
 *
 * @property title One sentence that says what the rule checks, e.g. `"ViewModels must extend BaseViewModel."`.
 * @property rationale Why the rule exists.
 * @property howToFix How to fix a violation.
 * @property goodExample A short code sample that follows the rule. Common indentation is removed, so a raw string can be used.
 * @property badExample A short code sample that violates the rule. Common indentation is removed, so a raw string can be used.
 */
data class KoRule(
    val title: String,
    val rationale: String,
    val howToFix: String? = null,
    val goodExample: String? = null,
    val badExample: String? = null,
) {
    init {
        require(title.isNotBlank()) { "KoRule title must not be blank." }
        require(rationale.isNotBlank()) { "KoRule rationale must not be blank." }
    }
}
