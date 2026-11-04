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
 *             id = "VM-001",
 *             title = "ViewModels must extend BaseViewModel.",
 *             reason = "Lifecycle handling lives in BaseViewModel.",
 *             description = "Applies to every class with the ViewModel suffix.",
 *             howToFix = "Change the parent class to BaseViewModel.",
 *             goodExample = "class LoginViewModel : BaseViewModel()",
 *             badExample = "class LoginViewModel : ViewModel()",
 *         ),
 *     ) { it.hasParentClassWithName("BaseViewModel") }
 * ```
 *
 * @property id Unique rule identifier, e.g. `"VM-001"`. Lets a developer (or an AI agent) refer to the rule.
 * @property title One sentence that says what the rule checks, e.g. `"ViewModels must extend BaseViewModel."`.
 * @property reason Why the rule exists.
 * @property description Additional details about the rule, e.g. its scope or exceptions.
 * @property howToFix How to fix a violation.
 * @property goodExample A short code sample that follows the rule. Common indentation is removed, so a raw string can be used.
 * @property badExample A short code sample that violates the rule. Common indentation is removed, so a raw string can be used.
 */
data class KoRule(
    val id: String,
    val title: String,
    val reason: String,
    val description: String? = null,
    val howToFix: String? = null,
    val goodExample: String? = null,
    val badExample: String? = null,
) {
    init {
        require(id.isNotBlank()) { "KoRule id must not be blank." }
        require(title.isNotBlank()) { "KoRule title must not be blank." }
        require(reason.isNotBlank()) { "KoRule reason must not be blank." }
    }
}
