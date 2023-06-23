Konsist.scopeFromProject()
.classes()
.assert {
    it.hasTest()
}

// without value and data classes: two ways

Konsist.scopeFromProject()
.classes()
.withoutDataModifier()
.withoutValueModifier()
.assert {
    it.hasTest()
}

Konsist.scopeFromProject()
.classes()
.withoutModifiers(KoModifier.DATA, KoModifier.VALUE)
.assert {
    it.hasTest()
}
