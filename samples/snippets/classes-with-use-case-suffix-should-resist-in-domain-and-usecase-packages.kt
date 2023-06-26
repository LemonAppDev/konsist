Konsist.scopeFromProject()
.classes()
.withNameEndingWith("UseCase")
.assert { it.resideInPackage("..domain..usecase..") }
