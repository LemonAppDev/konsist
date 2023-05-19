Konsist.scopeFromProject()
    .classes()
    .assert {
        val names = it.parameters.map { parameter -> parameter.name }
        val sortedNames = it.parameters.map { parameter -> parameter.name }.sorted()
        names == sortedNames
    }
