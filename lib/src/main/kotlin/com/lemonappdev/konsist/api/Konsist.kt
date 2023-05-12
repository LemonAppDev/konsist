package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.core.scope.KoScopeCreatorImpl

/**
 * Represents the Konsist API. This is the main entry point to the Konist library.
 */
object Konsist : KoScopeCreator by KoScopeCreatorImpl()
