package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoPathProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPathProviderListExtTest {
    @Test
    fun `withPath(empty list) returns all declarations`() {
        // given
        val declaration1: KoPathProvider = mockk()
        val declaration2: KoPathProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPath(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withPath(empty set) returns all declarations`() {
        // given
        val declaration1: KoPathProvider = mockk()
        val declaration2: KoPathProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPath(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutPath(empty list) returns none declaration`() {
        // given
        val declaration1: KoPathProvider = mockk()
        val declaration2: KoPathProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPath(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutPath(empty set) returns none declaration`() {
        // given
        val declaration1: KoPathProvider = mockk()
        val declaration2: KoPathProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPath(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withPath() returns declaration with given path`() {
        // given
        val path = "com/sample/samplepath.."
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPath(path)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPath() returns declaration without given path`() {
        // given
        val path = "com/sample/samplepath.."
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPath(path)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withPath() with absolute path 'true' returns declarations with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns true
                every { resideInPath(path2, true) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withPath(path1, path2, absolutePath = true)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withPath(List) with absolute path 'true' returns declarations with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns true
                every { resideInPath(path2, true) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = listOf(path1, path2)

        // when
        val sut = declarations.withPath(paths, absolutePath = true)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withPath(Set) with absolute path 'true' returns declarations with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns true
                every { resideInPath(path2, true) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = setOf(path1, path2)

        // when
        val sut = declarations.withPath(paths, absolutePath = true)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutPath() with absolute path 'true' returns declaration without any of given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns true
                every { resideInPath(path2, true) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutPath(path1, path2, absolutePath = true)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutPath(List) with absolute path 'true' returns declaration without any of given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns true
                every { resideInPath(path2, true) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = listOf(path1, path2)

        // when
        val sut = declarations.withoutPath(paths, absolutePath = true)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutPath(Set) with absolute path 'true' returns declaration without any of given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns true
                every { resideInPath(path2, true) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = setOf(path1, path2)

        // when
        val sut = declarations.withoutPath(paths, absolutePath = true)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withPath(String) with absolute path 'false' returns declarations with one of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns true
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withPath(projectPath1, projectPath2, absolutePath = false)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withPath(list of String) with absolute path 'false' returns declarations with one of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns true
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = listOf(projectPath1, projectPath2)

        // when
        val sut = declarations.withPath(paths, absolutePath = false)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withPath(set of String) with absolute path 'false' returns declarations with one of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns true
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = setOf(projectPath1, projectPath2)

        // when
        val sut = declarations.withPath(paths, absolutePath = false)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutPath(String) with absolute path 'false' returns declaration without any of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns true
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutPath(projectPath1, projectPath2, absolutePath = false)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutPath(list of String) with absolute path 'false' returns declaration without any of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns true
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = listOf(projectPath1, projectPath2)

        // when
        val sut = declarations.withoutPath(paths, absolutePath = false)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutPath(set of String) with absolute path 'false' returns declaration without any of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns true
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = setOf(projectPath1, projectPath2)

        // when
        val sut = declarations.withoutPath(paths, absolutePath = false)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withAbsolutePath(String) returns declaration with given path`() {
        // given
        val path = "com/sample/samplepath.."
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path, true) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path, true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAbsolutePath(path)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAbsolutePath(String) returns declaration without given path`() {
        // given
        val path = "com/sample/samplepath.."
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path, true) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path, true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAbsolutePath(path)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAbsolutePath(String) returns declarations with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns true
                every { resideInPath(path2, true) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withAbsolutePath(path1, path2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withAbsolutePath(list of String) returns declarations with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns true
                every { resideInPath(path2, true) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = listOf(path1, path2)

        // when
        val sut = declarations.withAbsolutePath(paths)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withAbsolutePath(set of String) returns declarations with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns true
                every { resideInPath(path2, true) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = setOf(path1, path2)

        // when
        val sut = declarations.withAbsolutePath(paths)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutAbsolutePath(String) returns declaration without any of given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns true
                every { resideInPath(path2, true) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutAbsolutePath(path1, path2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutAbsolutePath(list of String) returns declaration without any of given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns true
                every { resideInPath(path2, true) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = listOf(path1, path2)

        // when
        val sut = declarations.withoutAbsolutePath(paths)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutAbsolutePath(set of String) returns declaration without any of given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns true
                every { resideInPath(path2, true) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(path1, true) } returns false
                every { resideInPath(path2, true) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = setOf(path1, path2)

        // when
        val sut = declarations.withoutAbsolutePath(paths)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withProjectPath(String) returns declaration with given project path`() {
        // given
        val projectPath = "com/sample/sampleProjectPath.."
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(projectPath, false) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(projectPath, false) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withProjectPath(projectPath)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutProjectPath(String) returns declaration without given project path`() {
        // given
        val projectPath = "com/sample/sampleProjectPath.."
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(projectPath, false) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(projectPath, false) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutProjectPath(projectPath)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withProjectPath(String) returns declarations with one of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns true
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withProjectPath(projectPath1, projectPath2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withProjectPath(list of String) returns declarations with one of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns true
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = listOf(projectPath1, projectPath2)

        // when
        val sut = declarations.withProjectPath(paths)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withProjectPath(set of String) returns declarations with one of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns true
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = setOf(projectPath1, projectPath2)

        // when
        val sut = declarations.withProjectPath(paths)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutProjectPath(String) returns declaration without any of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns true
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutProjectPath(projectPath1, projectPath2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutProjectPath(list of String) returns declaration without any of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns true
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = listOf(projectPath1, projectPath2)

        // when
        val sut = declarations.withoutProjectPath(paths)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutProjectPath(set of String) returns declaration without any of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val declaration1: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns true
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration2: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns true
            }
        val declaration3: KoPathProvider =
            mockk {
                every { resideInPath(projectPath1, false) } returns false
                every { resideInPath(projectPath2, false) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val paths = setOf(projectPath1, projectPath2)

        // when
        val sut = declarations.withoutProjectPath(paths)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
