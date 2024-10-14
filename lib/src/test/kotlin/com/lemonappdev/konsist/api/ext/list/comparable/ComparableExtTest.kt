package com.lemonappdev.konsist.api.ext.list.comparable

import com.lemonappdev.konsist.api.ext.comparable.isSortedBy
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class ComparableExtTest {
    @Test
    fun `isSortedBy - empty list should return true`() {
        emptyList<String>().isSortedBy({ it }) shouldBe true
    }

    @Test
    fun `isSortedBy - single element list should return true`() {
        listOf("a").isSortedBy({ it }) shouldBe true
    }

    @Test
    fun `isSortedBy - sorted string list should return true`() {
        listOf("a", "b", "c").isSortedBy({ it }) shouldBe true
    }

    @Test
    fun `isSortedBy - unsorted string list should return false`() {
        listOf("b", "a", "c").isSortedBy({ it }) shouldBe false
    }

    @Test
    fun `isSortedBy - case-insensitive sorted string list should return true by default`() {
        listOf("a", "B", "c").isSortedBy({ it }) shouldBe true
    }

    @Test
    fun `isSortedBy - case-sensitive sorted string list should return false when ignoreCase is false`() {
        listOf("a", "B", "c").isSortedBy({ it }, ignoreCase = false) shouldBe false
    }

    @Test
    fun `isSortedBy - descending sorted string list should return true`() {
        listOf("c", "b", "a").isSortedBy({ it }, ascending = false) shouldBe true
    }

    @Test
    fun `isSortedBy - descending case-insensitive sorted string list should return true by default`() {
        listOf("C", "b", "A").isSortedBy({ it }, ascending = false) shouldBe true
    }

    @Test
    fun `isSortedBy - descending case-sensitive sorted string list should return false when ignoreCase is false`() {
        listOf("C", "b", "A").isSortedBy({ it }, ascending = false, ignoreCase = false) shouldBe false
    }

    @Test
    fun `isSortedBy - sorted int list should return true`() {
        listOf(1, 2, 3, 4, 5).isSortedBy({ it }) shouldBe true
    }

    @Test
    fun `isSortedBy - unsorted int list should return false`() {
        listOf(1, 3, 2, 5, 4).isSortedBy({ it }) shouldBe false
    }

    @Test
    fun `isSortedBy - descending sorted int list should return true`() {
        listOf(5, 4, 3, 2, 1).isSortedBy({ it }, ascending = false) shouldBe true
    }

    @Test
    fun `isSortedBy - custom selector function should work correctly`() {
        data class Person(
            val name: String,
            val age: Int,
        )

        val people =
            listOf(
                Person("Alice", 25),
                Person("Bob", 30),
                Person("Charlie", 35),
            )

        people.isSortedBy({ it.age }) shouldBe true
        people.isSortedBy({ it.name }) shouldBe true
    }

    @Test
    fun `isSortedBy - custom selector function with descending order should work correctly`() {
        data class Person(
            val name: String,
            val age: Int,
        )

        val people =
            listOf(
                Person("Charlie", 35),
                Person("Bob", 30),
                Person("Alice", 25),
            )

        people.isSortedBy({ it.age }, ascending = false) shouldBe true
        people.isSortedBy({ it.name }, ascending = false) shouldBe true
    }
}
