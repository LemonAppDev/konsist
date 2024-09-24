package com.lemonappdev.konsist.api.ext.list.provider

/**
 * Returns the index of the first occurrence of an declaration of type [T] in the list,
 * or -1 if the list does not contain any declarations of that type. non-null subtype of [Any].
 *
 * @return The index of the first occurrence of an declaration of type [T] in the list, or -1 if not found.
 */
inline fun <reified T : Any> List<*>.indexOfFirstInstance(): Int = indexOfFirst { it is T }

/**
 * Returns the index of the last occurrence of an declaration of type [T] in the list,
 * or -1 if the list does not contain any declarations of that type.
 *
 * @return The index of the last occurrence of an declaration of type [T] in the list, or -1 if not found.
 */
inline fun <reified T : Any> List<*>.indexOfLastInstance(): Int = indexOfLast { it is T }
