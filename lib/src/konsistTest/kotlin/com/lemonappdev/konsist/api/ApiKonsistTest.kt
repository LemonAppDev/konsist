package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.ext.list.withoutName
import com.lemonappdev.konsist.api.ext.list.withoutNameStartingWith
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocParamTags
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocReturnTag
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.api.verify.assert
import org.junit.jupiter.api.Test
import java.util.*

class ApiKonsistTest {
    private val apiPackageScope = Konsist.scopeFromPackage("com.lemonappdev.konsist.api..", sourceSetName = "main")

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
        Konsist
            .scopeFromPackage("com.lemonappdev.konsist.api.provider..", sourceSetName = "main")
            .interfaces()
            .withoutName(
                "KoKDocTagsProvider",
                "KoParentInterfaceProvider",
            ) // remove after merge https://lemonappdev.atlassian.net/browse/KON-456 and release v1.0.0
            .withoutNameStartingWith("KoLocal") // remove after merge https://lemonappdev.atlassian.net/browse/KON-416
            .filter {
                it.containsProperty { property ->
                    property.type?.hasNameStartingWith("List<Ko") ?: false
                }
            } // change this lines to .withProperty { } (after https://lemonappdev.atlassian.net/browse/KON-416)
            .assert {
                it.hasCorrectMethods(false)
            }
    }

    @Test
    fun `every provider containing property with list of declarations type has correct extensions`() {
        val providers = Konsist
            .scopeFromPackage("com.lemonappdev.konsist.api.provider..", sourceSetName = "main")
            .interfaces()
            .withoutName(
                "KoKDocTagsProvider",
                "KoParentInterfaceProvider",
            ) // remove after merge https://lemonappdev.atlassian.net/browse/KON-456 and release v1.0.0
            .withoutNameStartingWith("KoLocal") // remove after merge https://lemonappdev.atlassian.net/browse/KON-416
            .filter {
                it.containsProperty { property ->
                    property.type?.hasNameStartingWith("List<Ko") ?: false
                }
            } // change this lines to .withProperty { } (after https://lemonappdev.atlassian.net/browse/KON-416)
            .map { it.name }

        Konsist
            .scopeFromPackage("com.lemonappdev.konsist.api.ext.list..", sourceSetName = "main")
            .files
            .filter {
                providers.any { providerName -> it.hasNameContaining(providerName) }
            } // change this lines to .withProperty { } (after https://lemonappdev.atlassian.net/browse/KON-416)
            .assert {
                it.hasCorrectMethods(true)
            }
    }

    private val declarations = Konsist
        .scopeFromPackage("com.lemonappdev.konsist.api.declaration..", sourceSetName = "main")
        .interfaces()

    private fun <T> T.hasCorrectMethods(isExtension: Boolean): Boolean
        where T : KoPropertyProvider,
              T : KoFunctionProvider {
        val property = properties()
            .first { property ->
                property.type?.hasNameStartingWith("List<Ko") ?: false
            } // change this lines to .hasProperty { } (after https://lemonappdev.atlassian.net/browse/KON-416)

        val declarationName = property
            .type
            ?.name
            ?.removePrefix("List<")
            ?.removeSuffix(">")

        // We distinguish singularName and pluralName due to different variations of words,
        // e.g. import -> imports (suffix - "s") , but class -> classes (suffix - "es")

        val singularName = declarationName
            ?.removePrefix("Ko")
            ?.removeSuffix("Declaration")
            ?: ""

        val pluralName = property
            .name
            .replaceFirstChar { char -> if (char.isLowerCase()) char.titlecase(Locale.getDefault()) else char.toString() }

        val hasKoNameProvider = declarations
            .firstOrNull { declaration -> declaration.name == declarationName }
            ?.hasParentWithName("KoNameProvider") ?: false

        return if (isExtension) {
            checkForExtensions(declarationName, singularName, pluralName, hasKoNameProvider)
        } else {
            checkForProviders(declarationName, singularName, pluralName, hasKoNameProvider)
        }
    }

    private fun KoFunctionProvider.checkForExtensions(
        declarationName: String?,
        singularName: String,
        pluralName: String,
        hasKoNameProvider: Boolean,
    ): Boolean = if (declarationName == "KoModifier") {
        hasModifiersFunctions(singularName, pluralName, "with") &&
            hasModifiersFunctions(singularName, pluralName, "without")
    } else if (hasKoNameProvider) {
        hasBasicFunctions(singularName, pluralName, "with") &&
            hasNamedFunctionsForExt(singularName, pluralName, "with") &&
            hasBasicFunctions(singularName, pluralName, "without") &&
            hasNamedFunctionsForExt(singularName, pluralName, "without")
    } else {
        hasBasicFunctions(singularName, pluralName, "with") &&
            hasBasicFunctions(singularName, pluralName, "without")
    }

    private fun KoFunctionProvider.checkForProviders(
        declarationName: String?,
        singularName: String,
        pluralName: String,
        hasKoNameProvider: Boolean,
    ): Boolean = if (declarationName == "KoModifier") {
        hasModifiersFunctions(singularName, pluralName, "has")
    } else if (hasKoNameProvider) {
        hasBasicFunctions(singularName, pluralName, "has") &&
            hasNamedFunctions(singularName, pluralName)
    } else {
        hasBasicFunctions(singularName, pluralName, "has")
    }

    private fun KoFunctionProvider.hasModifiersFunctions(
        singularName: String,
        pluralName: String,
        prefix: String,
    ): Boolean =
        containsFunction { function -> function.name == "${prefix}$pluralName" && !function.hasParameters() } &&
            containsFunction { function ->
                function.name == "${prefix}$singularName" && function.hasParametersWithAllNames(
                    "modifier",
                    "modifiers",
                )
            } &&
            containsFunction { function ->
                function.name == "${prefix}All$pluralName" && function.hasParametersWithAllNames(
                    "modifier",
                    "modifiers",
                )
            }

    private fun KoFunctionProvider.hasBasicFunctions(
        singularName: String,
        pluralName: String,
        prefix: String,
    ): Boolean {
        return containsFunction { function -> function.name == "${prefix}$pluralName" && !function.hasParameters() } &&
            containsFunction { function ->
                function.name == "${prefix}$singularName" && function.hasParameterWithName(
                    "predicate",
                )
            } &&
            containsFunction { function ->
                function.name == "${prefix}All$pluralName" && function.hasParameterWithName(
                    "predicate",
                )
            }
    }

    private fun KoFunctionProvider.hasNamedFunctions(singularName: String, pluralName: String): Boolean =
        containsFunction { function ->
            function.name == "has${singularName}WithName" && function.hasParametersWithAllNames("name", "names")
        } && containsFunction { function ->
            function.name == "has${pluralName}WithAllNames" && function.hasParametersWithAllNames("name", "names")
        }

    private fun KoFunctionProvider.hasNamedFunctionsForExt(
        singularName: String,
        pluralName: String,
        prefix: String,
    ): Boolean =
        containsFunction { function ->
            function.name == "$prefix${singularName}Named" && function.hasParametersWithAllNames("name", "names")
        } && containsFunction { function ->
            function.name == "${prefix}All${pluralName}Named" && function.hasParametersWithAllNames("name", "names")
        }
}
