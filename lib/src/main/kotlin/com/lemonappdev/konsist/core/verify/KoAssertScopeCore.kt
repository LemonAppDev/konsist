package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.verify.KoAssertScope

internal class KoAssertScopeCore : KoAssertScope {
    private val _reasons = mutableListOf<String>()

    val reasons: List<String>
        get() = _reasons

    override fun addReason(reason: String): Unit {
        _reasons += reason
    }
}
