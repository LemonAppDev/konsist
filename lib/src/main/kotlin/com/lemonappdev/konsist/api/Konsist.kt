package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.core.scope.KoScopeCreatorImpl

/**
 * Represents the Konsist API. This is the main entry point to the Konist library.
 *
 * It allows to create a [KoScope] instance from the given set of files such as all project files, single module, path etc.
 */
object Konsist : KoScopeCreator by KoScopeCreatorImpl()
