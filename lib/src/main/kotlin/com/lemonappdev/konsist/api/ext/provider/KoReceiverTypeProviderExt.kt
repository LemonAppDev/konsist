package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import kotlin.reflect.typeOf

/**
 * Determines whether the declaration has a receiver type of type [T], including its type arguments.
 * For example, `List<String>` matches `hasReceiverTypeOf<List<String>>()`, but not `hasReceiverTypeOf<List<Int>>()`.
 *
 * @return `true` if the declaration has a receiver type matching [T], `false` otherwise.
 */
inline fun <reified T> KoReceiverTypeProvider.hasReceiverTypeOf(): Boolean = hasReceiverTypeOf(typeOf<T>())

/**
 * Determines whether the declaration has a valid KDoc with a RECEIVER tag.
 * A declaration with a receiver type must have a RECEIVER tag. Otherwise, it must not have one.
 *
 * @return `true` if the declaration has a valid KDoc with the RECEIVER tag, `false` otherwise.
 */
fun <T : KoReceiverTypeProvider> T.hasValidKDocReceiverTag(): Boolean =
    if (receiverType != null) {
        (this as? KoKDocProvider)?.kDoc?.hasTag(KoKDocTag.RECEIVER) == true
    } else {
        (this as? KoKDocProvider)?.kDoc?.receiverTag == null
    }
