package io.kotest.core.spec.style

open class FreeSpec(
    val body: FreeSpec.() -> Unit = {},
) {
    val testCase = TestCase()

    operator fun String.invoke(value: () -> Unit) {}
}

class TestCase {
    val name: TestName = TestName()
}

class TestName {
    val testName: String = ""
}
