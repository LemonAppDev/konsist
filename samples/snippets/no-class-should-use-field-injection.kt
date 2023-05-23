Konsist.scopeFromProject()
    .classes()
    .assert {
        it.hasAnnotations("javax.inject.Inject")
    }
