package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.provider.tag.KoKDocReceiverTagProvider

/**
 * List containing declarations with receiver tag.
 *
 * @return A list containing declarations with receiver tag.
 */
fun <T : KoKDocReceiverTagProvider> List<T>.withReceiverTag(): List<T> = filter { it.hasReceiverTag }

/**
 * List containing declarations with no receiver tag.
 *
 * @return A list containing declarations with no receiver tag.
 */
fun <T : KoKDocReceiverTagProvider> List<T>.withoutReceiverTag(): List<T> = filterNot { it.hasReceiverTag }
