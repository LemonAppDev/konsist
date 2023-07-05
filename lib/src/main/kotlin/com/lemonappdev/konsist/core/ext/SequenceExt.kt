package com.lemonappdev.konsist.core.ext

inline fun <reified T : Any> Sequence<*>.indexOfFirstInstance(): Int = indexOfFirst { it is T }

inline fun <reified T : Any> Sequence<*>.indexOfLastInstance(): Int = indexOfLast { it is T }
