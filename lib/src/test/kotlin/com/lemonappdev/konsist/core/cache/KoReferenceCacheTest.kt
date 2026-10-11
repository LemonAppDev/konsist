package com.lemonappdev.konsist.core.cache

import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import org.junit.jupiter.api.Test
import java.lang.ref.Reference
import java.lang.ref.SoftReference

class KoReferenceCacheTest {
    private val references = mutableListOf<Reference<Any>>()

    private val sut = KoReferenceCache<Any> { value -> SoftReference(value).also { references += it } }

    @Test
    fun `should return cached instance`() {
        // given
        val instance = sut.getOrCreateInstance("key") { Any() }

        // when
        val result = sut.getOrCreateInstance("key") { Any() }

        // then
        result shouldBe instance
        references.size shouldBeEqualTo 1
    }

    @Test
    fun `should create instance again when it was released from memory`() {
        // given
        val instance = sut.getOrCreateInstance("key") { Any() }
        references.forEach { it.clear() }

        // when
        val result = sut.getOrCreateInstance("key") { Any() }

        // then
        result shouldNotBe instance
        sut.get("key") shouldBe result
    }

    @Test
    fun `should return null when instance is not cached`() {
        // when
        val result = sut.get("key")

        // then
        result shouldBeEqualTo null
    }
}
