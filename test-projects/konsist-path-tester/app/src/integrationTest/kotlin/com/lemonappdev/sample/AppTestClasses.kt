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
        val sut = AppClass("")
    }
}

class AppTestClass6 {
    fun sampleTest() {
        val sut: AppClass = getInstance()
    }
}

private fun getInstance() = AppClass("")
