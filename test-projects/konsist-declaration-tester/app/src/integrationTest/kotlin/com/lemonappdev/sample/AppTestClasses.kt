package com.lemonappdev.sample

class AppTestClass1 {
    val sut = AppClass("")
}

class AppTestClass2 {
    val sut: AppClass = getInstance()
}

class AppTestClass3 {
    val sut: AppClass
        get() = AppClass("")
}

class AppTestClass4 {
    val sut
        get() = AppClass("")
}

class AppTestClass5 {
    fun sampleTest() {
        @Suppress("detekt.UnusedPrivateProperty")
        val sut = AppClass("")
    }
}

class AppTestClass6 {
    fun sampleTest() {
        @Suppress("detekt.UnusedPrivateProperty")
        val sut: AppClass = getInstance()
    }
}

class AppTestClass7 {
    val sut = getInstance()
}

class AppTestClass8 {
    fun sampleTest() {
        @Suppress("detekt.UnusedPrivateProperty")
        val sut = getInstance()
    }
}

private fun getInstance() = AppClass("")
