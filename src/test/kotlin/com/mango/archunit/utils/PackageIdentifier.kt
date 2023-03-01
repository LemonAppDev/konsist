package com.mango.archunit.utils

object PackageIdentifier {
    private const val COM_MANGO = "com.mango"
    const val COM_MANGO_PRESENTATION = "${COM_MANGO}presentation"
    const val COM_MANGO_PERSISTENCE = "${COM_MANGO}persistence"
    const val COM_MANGO_BUSINESS = "${COM_MANGO}business"
    const val COM_MANGO_COMMON = "${COM_MANGO}common"
    const val ANY_PRESENTATION_ANY = "..presentation.."
    const val ANY_BUSINESS_ANY = "..business.."
    const val ANY_PERSISTENCE_ANY = "..persistence.."
    const val ANY_PRESENTATION_ANY_CONTROLLER_ANY = "..presentation..controller.."
    const val ANY_PERSISTENCE_ANY_REPOSITORY_ANY = "..persistence..repository.."
    const val ANY_BUSINESS_ANY_USECASE_ANY = "..business..usecase.."
}
