Konsist.scopeFromProject()
    .classes()
    .assert {
        it.name.toTitleCase() == it.type.sourceType
    }
