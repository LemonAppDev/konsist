package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.verify.KoAssertScope

internal class KoAssertScopeCore : KoAssertScope {
    private val _messages = mutableListOf<String>()

    val messages: List<String>
        get() = _messages

    override fun addMessage(message: String): Unit {
        _messages += message
    }
}
