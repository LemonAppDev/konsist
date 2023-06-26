import org.springframework.web.bind.annotation.RestController

Konsist.scopeFromProject()
.classes()
.withAnnotationOf<RestController>()
.assert { it.resideInPackage("..controller..") }
