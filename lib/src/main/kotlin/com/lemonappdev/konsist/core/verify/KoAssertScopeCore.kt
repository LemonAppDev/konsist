package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.verify.KoAssertScope

internal class KoAssertScopeCore : KoAssertScope {
    private val _failureReasons = mutableListOf<String>()

    val failureReasons: List<String>
        get() = _failureReasons

    override fun addFailureReason(reason: String): Unit {
        _failureReasons += reason
    }
}
