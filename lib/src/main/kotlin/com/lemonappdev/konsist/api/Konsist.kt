package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.api.container.KoScopeCreator
import com.lemonappdev.konsist.core.container.KoScopeCreatorCore

/**
 * Represents the Konsist API. This is the main entry point to the Konsist library.
 *
 * It allows creating a [com.lemonappdev.konsist.api.container.KoScope] instance from a given set of files, such as all
 * project files, a single module, a path, etc.
 */
object Konsist : KoScopeCreator by KoScopeCreatorCore()
