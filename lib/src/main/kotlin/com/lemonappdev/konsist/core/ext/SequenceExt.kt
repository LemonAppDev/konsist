package com.lemonappdev.konsist.core.ext

inline fun <reified T : Any> Sequence<*>.indexOfFirst(): Int = indexOfFirst { it is T }

inline fun <reified T : Any> Sequence<*>.indexOfLast(): Int = indexOfLast { it is T }
