Konsist.scopeFromProject()
.packages()
.assert {
    it
        .filePath
        .replace("/", ".")
        .endsWith(it.qualifiedName)
}
