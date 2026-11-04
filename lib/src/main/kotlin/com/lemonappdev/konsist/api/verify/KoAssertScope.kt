package com.lemonappdev.konsist.api.verify

/**
 * Scope available inside the `assertTrue` and `assertFalse` predicate.
 */
interface KoAssertScope {
    /**
     * Adds a reason explaining why the currently checked element violates the assertion.
     *
     * The reason is displayed under the element in the assertion error, but only when this element fails the assertion.
     * Reasons added for elements that pass the assertion are ignored.
     *
     * @param reason The reason to display under the failed element.
     */
    fun addFailureReason(reason: String): Unit
}
