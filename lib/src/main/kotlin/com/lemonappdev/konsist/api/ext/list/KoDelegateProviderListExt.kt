package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoDelegateProvider

/**
 * List containing declarations with delegate with given name.
 *
 * @param names The delegate names to include.
 * @return A list containing declarations with the specified delegate name(s) (or any delegate if [names] is empty).
 * @see KoDelegateProvider.hasDelegate
 */
fun <T : KoDelegateProvider> List<T>.withDelegate(vararg names: String): List<T> = withDelegate(listOf(*names))

/**
 * List containing declarations with delegate with given name.
 *
 * @param names The delegate names to include.
 * @return A list containing declarations with the specified delegate name(s) (or any delegate if [names] is empty).
 * @see KoDelegateProvider.hasDelegate
 */
fun <T : KoDelegateProvider> List<T>.withDelegate(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasDelegate()
            else -> names.any { name -> it.hasDelegate(name) }
        }
    }

/**
 * List containing declarations without delegate with given name.
 *
 * @param names The delegate names to exclude.
 * @return A list containing declarations without the specified delegate name(s) (or no delegate if [names] is empty).
 * @see KoDelegateProvider.hasDelegate
 */
fun <T : KoDelegateProvider> List<T>.withoutDelegate(vararg names: String): List<T> = withoutDelegate(listOf(*names))

/**
 * List containing declarations without delegate with given name.
 *
 * @param names The delegate names to exclude.
 * @return A list containing declarations without the specified delegate name(s) (or no delegate if [names] is empty).
 * @see KoDelegateProvider.hasDelegate
 */
fun <T : KoDelegateProvider> List<T>.withoutDelegate(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> !it.hasDelegate()
            else -> names.none { name -> it.hasDelegate(name) }
        }
    }

/**
 * List containing declarations with `lazy` delegate with given thread safety mode.
 *
 * @param modes The thread safety modes to include.
 * @return A list containing declarations with `lazy` delegate with the specified thread safety mode(s)
 * (or any `lazy` delegate if [modes] is empty).
 * @see KoDelegateProvider.hasLazyDelegate
 */
fun <T : KoDelegateProvider> List<T>.withLazyDelegate(vararg modes: LazyThreadSafetyMode): List<T> = withLazyDelegate(listOf(*modes))

/**
 * List containing declarations with `lazy` delegate with given thread safety mode.
 *
 * @param modes The thread safety modes to include.
 * @return A list containing declarations with `lazy` delegate with the specified thread safety mode(s)
 * (or any `lazy` delegate if [modes] is empty).
 * @see KoDelegateProvider.hasLazyDelegate
 */
fun <T : KoDelegateProvider> List<T>.withLazyDelegate(modes: Collection<LazyThreadSafetyMode>): List<T> =
    filter {
        when {
            modes.isEmpty() -> it.hasLazyDelegate()
            else -> modes.any { mode -> it.hasLazyDelegate(mode) }
        }
    }

/**
 * List containing declarations without `lazy` delegate with given thread safety mode.
 *
 * @param modes The thread safety modes to exclude.
 * @return A list containing declarations without `lazy` delegate with the specified thread safety mode(s)
 * (or without any `lazy` delegate if [modes] is empty).
 * @see KoDelegateProvider.hasLazyDelegate
 */
fun <T : KoDelegateProvider> List<T>.withoutLazyDelegate(vararg modes: LazyThreadSafetyMode): List<T> = withoutLazyDelegate(listOf(*modes))

/**
 * List containing declarations without `lazy` delegate with given thread safety mode.
 *
 * @param modes The thread safety modes to exclude.
 * @return A list containing declarations without `lazy` delegate with the specified thread safety mode(s)
 * (or without any `lazy` delegate if [modes] is empty).
 * @see KoDelegateProvider.hasLazyDelegate
 */
fun <T : KoDelegateProvider> List<T>.withoutLazyDelegate(modes: Collection<LazyThreadSafetyMode>): List<T> =
    filter {
        when {
            modes.isEmpty() -> !it.hasLazyDelegate()
            else -> modes.none { mode -> it.hasLazyDelegate(mode) }
        }
    }

/**
 * List containing declarations with `Delegates.observable` delegate.
 *
 * @return A list containing declarations with `Delegates.observable` delegate.
 * @see KoDelegateProvider.hasObservableDelegate
 */
fun <T : KoDelegateProvider> List<T>.withObservableDelegate(): List<T> = filter { it.hasObservableDelegate }

/**
 * List containing declarations without `Delegates.observable` delegate.
 *
 * @return A list containing declarations without `Delegates.observable` delegate.
 * @see KoDelegateProvider.hasObservableDelegate
 */
fun <T : KoDelegateProvider> List<T>.withoutObservableDelegate(): List<T> = filterNot { it.hasObservableDelegate }

/**
 * List containing declarations with `Delegates.vetoable` delegate.
 *
 * @return A list containing declarations with `Delegates.vetoable` delegate.
 * @see KoDelegateProvider.hasVetoableDelegate
 */
fun <T : KoDelegateProvider> List<T>.withVetoableDelegate(): List<T> = filter { it.hasVetoableDelegate }

/**
 * List containing declarations without `Delegates.vetoable` delegate.
 *
 * @return A list containing declarations without `Delegates.vetoable` delegate.
 * @see KoDelegateProvider.hasVetoableDelegate
 */
fun <T : KoDelegateProvider> List<T>.withoutVetoableDelegate(): List<T> = filterNot { it.hasVetoableDelegate }

/**
 * List containing declarations with `Delegates.notNull` delegate.
 *
 * @return A list containing declarations with `Delegates.notNull` delegate.
 * @see KoDelegateProvider.hasNotNullDelegate
 */
fun <T : KoDelegateProvider> List<T>.withNotNullDelegate(): List<T> = filter { it.hasNotNullDelegate }

/**
 * List containing declarations without `Delegates.notNull` delegate.
 *
 * @return A list containing declarations without `Delegates.notNull` delegate.
 * @see KoDelegateProvider.hasNotNullDelegate
 */
fun <T : KoDelegateProvider> List<T>.withoutNotNullDelegate(): List<T> = filterNot { it.hasNotNullDelegate }

/**
 * List containing declarations with property reference delegate.
 *
 * @return A list containing declarations with property reference delegate.
 * @see KoDelegateProvider.hasPropertyReferenceDelegate
 */
fun <T : KoDelegateProvider> List<T>.withPropertyReferenceDelegate(): List<T> = filter { it.hasPropertyReferenceDelegate }

/**
 * List containing declarations without property reference delegate.
 *
 * @return A list containing declarations without property reference delegate.
 * @see KoDelegateProvider.hasPropertyReferenceDelegate
 */
fun <T : KoDelegateProvider> List<T>.withoutPropertyReferenceDelegate(): List<T> = filterNot { it.hasPropertyReferenceDelegate }
