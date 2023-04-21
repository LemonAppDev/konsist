---
description: What is declaration?
---

# Declaration

The declaration represents a code entity, a piece of Kotlin code. Every parsed Kotlin File (`KoFile`) (usually) contains multiple declarations. The declaration can be a package (`KoPackage`), property (`KoProperty`), annotation (`KoAnnotation`), class (`KoClass`), etc.

{% hint style="info" %}

{% endhint %}

Consider this Kotlin code snippet from \~/`project/User.kt` file:

```kotlin
private const val logLevel = "debug"

@Entity
open class Logger(val level: String) {
   fun log(message: String) {
   
   } 
}
```

Declarations contained by the scope ([koscope.md](koscope.md "mention")) are mimicking the Kotlin file structure. The above snippet contains two top-level declarations - property declaration (`KoProperty`) and class declaration (`KoClass`). The `Logger` the class declaration contains a single function (`KoFunction` ) declaration.

Each declaration contains a set of properties to facilitate filtering and verification eg. `KoClass` declaration has `name`,  `modifiers` , `annotations` , `declarations` (containing `KoFunction`) etc.

## Additional properties

Every declaration has a few additional properties:

* `text` - provides declaration text eg. `val property role = "Developer"`
* `location` - provides file path with file name, line, and column e.g. `~\Dev\IdeaProject\Konsist\lib\src\kotlin\com\konsist\core\KoScope:10:5`
* `textWithLocation` - provides `location` together with declaration `text`

