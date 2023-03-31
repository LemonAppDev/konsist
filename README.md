# Konsist

Konsist performs detailed checks of Kotlin code. It helps to standardise Kotlin codebase and enforce coding conventions.
Syntax is easy to learn and familiar to use because it “extends” Kotlin collection processing API.

Example checks:
- Check if all repository classes are reside in repository package
- Check if every use case has one public function
- Check if class is not using forbidden dependencies
- Check if `Controller` class is annotated with `@Controller` Annotation 
- Check if use case has one public function
- Check if no classes are using Java util logging 
- and much more… see sample checks

## Why there are no pre-defined rules?

Many linters including [Detekt](https://github.com/detekt/detekt) and [ktlint](https://github.com/pinterest/ktlint)
have predefined set of rules. These rules are derived and aligned with guidelines or common practices for writing good 
code and industry coding conventions ([Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html), 
[Android Kotlin style guide](https://developer.android.com/kotlin/style-guide), etc.). 

However, there are no industry standards when comes to application architecture. Every code base is different - 
different class naming, different package structure, different application layers... These "rules" are also hard to 
capture in a linter, because they are very specific to the project. Consider a simple use case class:
- can use case extend or include another use case?
- do we want every use case to have `UseCase` suffix?
- what package these use cases should resist in?
- what annotations use case should have?
- should use case have a single method?
- how this method should be named?
- can this method have overloads?
- should this method be defined as `invoke operator`?
- should this method always be `suspended`?
- …

Every of the above questions can be answered differently in different projects. Capturing all of these details would 
result in large number of rules and rigid API. That's why konsist favours more flexible approach - it allows you to 
define your own tests (checks) using Kotlin collection processing API.

That's being set I am considering adding a small set of predefined rules. Some things can be standardised across 
different projects e.g. constructor parameter names being derived from the property name, or alphabetic the order of 
the parameters.
