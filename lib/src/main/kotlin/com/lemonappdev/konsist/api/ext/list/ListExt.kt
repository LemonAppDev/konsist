package com.lemonappdev.konsist.api.ext.list

inline fun <reified T : Any> List<*>.indexOfFirstInstance(): Int = indexOfFirst { it is T }

inline fun <reified T : Any> List<*>.indexOfLastInstance(): Int = indexOfLast { it is T }
