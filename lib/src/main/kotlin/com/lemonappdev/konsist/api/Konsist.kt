package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.api.container.koscope.KoScopeCreator
import com.lemonappdev.konsist.core.container.koscope.KoScopeCreatorImpl

/**
 * Represents the Konsist API. This is the main entry point to the Konsist library.
 *
 * It allows to create a [com.lemonappdev.konsist.api.container.koscope.KoScope] instance from the given set of files such as all
 * project files, single module, path etc.
 */
object Konsist : KoScopeCreator by KoScopeCreatorImpl()
