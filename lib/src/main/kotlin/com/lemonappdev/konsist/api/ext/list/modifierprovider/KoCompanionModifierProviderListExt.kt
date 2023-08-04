import com.lemonappdev.konsist.api.provider.modifier.KoCompanionModifierProvider

/**
 * List containing elements with `companion` modifier.
 *
 * @return A list containing elements with the `companion` modifier.
 */
fun <T : KoCompanionModifierProvider> List<T>.withCompanionModifier(): List<T> = filter { it.hasCompanionModifier }

/**
 * List containing elements without `companion` modifier.
 *
 * @return A list containing elements without the `companion` modifier.
 */
fun <T : KoCompanionModifierProvider> List<T>.withoutCompanionModifier(): List<T> = filterNot { it.hasCompanionModifier }
