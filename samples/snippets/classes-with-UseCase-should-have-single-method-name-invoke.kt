Konsist.scopeFromProject()
.classes()
.withNameEndingWith("UseCase")
.assert { it.declarations().toList().size == 1 && it.containsFunction("invoke") }
