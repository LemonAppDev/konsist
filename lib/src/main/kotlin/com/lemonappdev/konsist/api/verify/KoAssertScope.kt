package com.lemonappdev.konsist.api.verify

/**
 * Scope available inside the `assertTrue` and `assertFalse` predicate.
 */
interface KoAssertScope {
    /**
     * Adds a message describing the currently checked element.
     *
     * The message is displayed under the element in the assertion error, but only when this element fails the assertion.
     * Messages added for elements that pass the assertion are ignored.
     *
     * @param message The message to display under the failed element.
     */
    fun addMessage(message: String): Unit
}
