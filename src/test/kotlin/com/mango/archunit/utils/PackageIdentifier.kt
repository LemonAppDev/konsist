package com.mango.archunit.utils

object PackageIdentifier {
    const val COM_MANGO = "com.mango"
    private const val APPLICATION = "application"
    private const val DOMAIN = "domain"
    private const val DATA = "data"
    private const val COMMON = "common"
    const val COM_MANGO_APPLICATION_ANY = "$COM_MANGO.$APPLICATION.."
    const val COM_MANGO_DATA_ANY = "$COM_MANGO.$DATA.."
    const val COM_MANGO_DOMAIN_ANY = "$COM_MANGO.$DOMAIN.."
    const val COM_MANGO_COMMON_ANY = "$COM_MANGO.$COMMON.."
    const val ANY_APPLICATION_ANY = "..$APPLICATION.."
    const val ANY_DOMAIN_ANY = "..$DOMAIN.."
    const val ANY_DATA_ANY = "..$DATA.."
    const val ANY_APPLICATION_ANY_CONTROLLER_ANY = "..$APPLICATION..controller.."
    const val ANY_DOMAIN_ANY_USECASE_ANY = "..$DOMAIN..usecase.."
}
