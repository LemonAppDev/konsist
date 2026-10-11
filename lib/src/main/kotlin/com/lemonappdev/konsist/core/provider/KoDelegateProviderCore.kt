package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoDelegateProvider

private const val DELEGATES_PREFIX = """^(kotlin\.properties\.)?Delegates\."""
private const val TYPE_ARGUMENTS = """(<.*>)?"""

private val lazyDelegateRegex = Regex("""^(kotlin\.)?lazy$TYPE_ARGUMENTS(\((?<arguments>.*))?$""")
private val lazyThreadSafetyModeRegex = Regex("""^(mode=)?((kotlin\.)?LazyThreadSafetyMode\.)?(?<mode>SYNCHRONIZED|PUBLICATION|NONE)$""")
private val observableDelegateRegex = Regex("""${DELEGATES_PREFIX}observable$TYPE_ARGUMENTS\(""")
private val vetoableDelegateRegex = Regex("""${DELEGATES_PREFIX}vetoable$TYPE_ARGUMENTS\(""")
private val notNullDelegateRegex = Regex("""${DELEGATES_PREFIX}notNull$TYPE_ARGUMENTS\(""")
private val propertyReferenceDelegateRegex = Regex("""^[^()]*::\w+$""")

internal interface KoDelegateProviderCore :
    KoDelegateProvider,
    KoBaseProviderCore {
    override fun hasLazyDelegate(mode: LazyThreadSafetyMode?): Boolean {
        val lazyDelegate =
            delegateName
                ?.filterNot { it.isWhitespace() }
                ?.let { lazyDelegateRegex.find(it) }
                ?: return false

        return when (mode) {
            null -> true
            else -> getLazyThreadSafetyMode(lazyDelegate.groups["arguments"]?.value) == mode
        }
    }

    override val hasObservableDelegate: Boolean
        get() = hasDelegateMatching(observableDelegateRegex)

    override val hasVetoableDelegate: Boolean
        get() = hasDelegateMatching(vetoableDelegateRegex)

    override val hasNotNullDelegate: Boolean
        get() = hasDelegateMatching(notNullDelegateRegex)

    override val hasPropertyReferenceDelegate: Boolean
        get() = hasDelegateMatching(propertyReferenceDelegateRegex)

    override fun hasDelegate(delegateName: String?): Boolean =
        when (delegateName) {
            null -> this.delegateName != null
            else -> this.delegateName == delegateName
        }

    // Delegate name is cut before the first `{`, so the arguments may lack the closing parenthesis,
    // e.g. `lazy(LazyThreadSafetyMode.NONE, { 0 })` gives `LazyThreadSafetyMode.NONE,` arguments.
    // Returns `null` when the mode can't be determined from the text (e.g. variable or lock argument).
    private fun getLazyThreadSafetyMode(arguments: String?): LazyThreadSafetyMode? {
        val firstArgument =
            arguments
                ?.removeSuffix(")")
                ?.substringBefore(",")
                .orEmpty()

        if (firstArgument.isEmpty() || firstArgument.startsWith("initializer=")) {
            return LazyThreadSafetyMode.SYNCHRONIZED
        }

        return lazyThreadSafetyModeRegex
            .find(firstArgument)
            ?.groups
            ?.get("mode")
            ?.value
            ?.let { LazyThreadSafetyMode.valueOf(it) }
    }

    private fun hasDelegateMatching(regex: Regex): Boolean =
        delegateName
            ?.filterNot { it.isWhitespace() }
            ?.let { regex.containsMatchIn(it) }
            ?: false
}
