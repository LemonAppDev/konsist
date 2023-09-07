import com.lemonappdev.konsist.api.provider.modifier.KoCompanionModifierProvider

/**
 * List containing declarations with `companion` modifier.
 *
 * @return A list containing declarations with the `companion` modifier.
 */
fun <T : KoCompanionModifierProvider> List<T>.withCompanionModifier(): List<T> = filter { it.hasCompanionModifier }

/**
 * List containing declarations without `companion` modifier.
 *
 * @return A list containing declarations without the `companion` modifier.
 */
fun <T : KoCompanionModifierProvider> List<T>.withoutCompanionModifier(): List<T> = filterNot { it.hasCompanionModifier }
