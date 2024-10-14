package com.lemonappdev.konsist.api.ext.comparable

/**
 * Checks if the list is sorted according to the specified [selector] function.
 *
 * This function determines if the elements in the list are in the specified order
 * when transformed by the given selector function. The order is determined by the
 * natural order of the [Comparable] type [R] returned by the selector.
 *
 * @param T The type of elements in the list.
 * @param R The comparable type returned by the selector function.
 * @param selector A function that extracts a comparable value from an element.
 * @param ascending Determines the order of sorting. If true (default), checks for ascending order.
 *                  If false, checks for descending order.
 * @param ignoreCase For String comparisons, determines whether the comparison should be case-insensitive.
 *                   Default is true. Has no effect on non-String comparisons.
 * @return `true` if the list is sorted according to the selector function in the specified order, `false` otherwise.
 *
 * @throws ClassCastException if the [selector] function returns values that are not mutually comparable.
 */
fun <T, R : Comparable<R>> List<T>.isSortedBy(
    selector: (T) -> R,
    ascending: Boolean = true,
    ignoreCase: Boolean = true,
): Boolean {
    if (size <= 1) return true

    val iterator = iterator()
    var previous = selector(iterator.next())

    while (iterator.hasNext()) {
        val current = selector(iterator.next())

        val comparisonResult =
            when {
                previous is String && current is String ->
                    previous.compareTo(current, ignoreCase = ignoreCase)
                else -> previous.compareTo(current)
            }

        if ((ascending && comparisonResult > 0) || (!ascending && comparisonResult < 0)) {
            return false
        }

        previous = current
    }

    return true
}
