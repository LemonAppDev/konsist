package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.ext.list.withoutName
import com.lemonappdev.konsist.api.ext.list.withoutNameStartingWith
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocParamTags
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocReturnTag
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.verify.assert
import org.junit.jupiter.api.Test
import java.util.*

class ApiKonsistTest {
    @Test
    fun `every api declaration has kdoc`() {
        apiPackageScope
            .declarationsOf<KoKDocProvider>()
            .assert { it.hasKDoc }
    }

    @Test
    fun `every api declaration has explicit return type`() {
        apiPackageScope
            .functions()
            .assert { it.hasReturnType() }
    }

    @Test
    fun `every api function has valid KDoc`() {
        apiPackageScope
            .functions()
            .assert { it.hasValidKDocParamTags() && it.hasValidKDocReturnTag() }
    }

    @Test
    fun `includeNested parameter is always before includeLocal parameter`() {
        apiPackageScope
            .functions()
            .assert {
                val includeNestedParameter =
                    it.parameters.indexOfFirst { parameter -> parameter.name == "includeNested" }
                val includeLocalParameter =
                    it.parameters.indexOfFirst { parameter -> parameter.name == "includeLocal" }

                includeNestedParameter <= includeLocalParameter || (includeNestedParameter != -1 && includeLocalParameter == -1)
            }
    }

    @Test
    fun `every provider has correct hasX methods`() {
        val declarations = Konsist
            .scopeFromPackage("com.lemonappdev.konsist.api.declaration..", sourceSetName = "main")
            .interfaces()

        Konsist
            .scopeFromPackage("com.lemonappdev.konsist.api.provider..", sourceSetName = "main")
            .interfaces()
            .withoutName(
                "KoKDocTagsProvider",
                "KoParentInterfaceProvider"
            ) // remove after merge https://lemonappdev.atlassian.net/browse/KON-456 and release v.1.0.0
            .withoutNameStartingWith("KoLocal") // remove after merge https://lemonappdev.atlassian.net/browse/KON-416
            .filter {
                it.containsProperty { property ->
                    property.type?.hasNameStartingWith("List<Ko") ?: false
                }
            } // change this lines to .withProperty { } (after https://lemonappdev.atlassian.net/browse/KON-416)
            .assert {
                val property = it
                    .properties()
                    .first { property ->
                        property.type?.hasNameStartingWith("List<Ko") ?: false
                    }

                val typeName = property
                    .type
                    ?.name

                // We distinguish singularName and pluralName due to different variations of words,
                // e.g. import -> imports (suffix - "s") , but class -> classes (suffix - "es")

                val singularName = typeName
                    ?.removePrefix("List<Ko")
                    ?.removeSuffix(">")
                    ?.removeSuffix("Declaration") // `removeSuffix` is used twice because of exception with KoModifier
                    ?: ""

                val pluralName = property
                    .name
                    .replaceFirstChar { char -> if (char.isLowerCase()) char.titlecase(Locale.getDefault()) else char.toString() }

                val hasKoNameProvider = declarations
                    .firstOrNull { declaration -> declaration.name == typeName }
                    ?.hasParentWithName("KoNameProvider") ?: false

                if (typeName == "List<KoModifier>") {
                    it.hasModifiersFunctions(singularName, pluralName)
                } else if (hasKoNameProvider) {
                    it.hasBasicFunctions(singularName, pluralName) && it.hasNamedFunctions(singularName, pluralName)
                } else {
                    it.hasBasicFunctions(singularName, pluralName)
                }
            }
    }

    private fun KoInterfaceDeclaration.hasModifiersFunctions(singularName: String, pluralName: String): Boolean =
        containsFunction { function -> function.name == "has$pluralName" && !function.hasParameters() } &&
                containsFunction { function ->
                    function.name == "has$singularName" && function.hasParametersWithAllNames(
                        "modifier",
                        "modifiers",
                    )
                } &&
                containsFunction { function ->
                    function.name == "hasAll$pluralName" && function.hasParametersWithAllNames(
                        "modifier",
                        "modifiers",
                    )
                }

    private fun KoInterfaceDeclaration.hasBasicFunctions(singularName: String, pluralName: String): Boolean {
        return containsFunction { function -> function.name == "count$pluralName" && function.hasParameterWithName("predicate") } &&
                containsFunction { function -> function.name == "has$pluralName" && !function.hasParameters() } &&
                containsFunction { function -> function.name == "has$singularName" && function.hasParameterWithName("predicate") } &&
                containsFunction { function -> function.name == "hasAll$pluralName" && function.hasParameterWithName("predicate") }
    }

    private fun KoInterfaceDeclaration.hasNamedFunctions(singularName: String, pluralName: String): Boolean =
        containsFunction { function ->
            function.name == "has${singularName}WithName" && function.hasParametersWithAllNames("name", "names")
        } && containsFunction { function ->
            function.name == "has${pluralName}sWithAllNames" && function.hasParametersWithAllNames("name", "names")
        }

    companion object {
        val apiPackageScope = Konsist.scopeFromPackage("com.lemonappdev.konsist.api..", sourceSetName = "main")
    }
}
