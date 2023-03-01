package com.mango.archunit.utils

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter

object ProjectClassesProvider {
    val allClasses: JavaClasses = ClassFileImporter().importPackages("com.mango..")
}
