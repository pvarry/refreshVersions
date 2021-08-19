package de.fayard.refreshVersions.rules

import de.fayard.refreshVersions.core.AbstractDependencyGroup
import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyReader
import dependencies.ALL_DEPENDENCIES_NOTATIONS
import org.junit.jupiter.api.*
import testutils.junit.dynamicTest
import java.io.File

@Suppress("UnstableApiUsage")
class BundledRulesTest {

    private val mainResources: File = File(".").absoluteFile.resolve("src/main/resources")
    private val rulesDir = mainResources.resolve("refreshVersions-rules")
    private val versionKeyReader = ArtifactVersionKeyReader.fromRules(rulesDir.listFiles()!!.map { it.readText() })

    @TestFactory
    fun `test bundled version key rules against dependencies constants`(): List<DynamicTest> {
        return bundledRules.map { (expectedKey, moduleIdentifiers) ->
            dynamicTest(displayName = expectedKey) {
                moduleIdentifiers.forEach { moduleIdentifier ->
                    Assertions.assertEquals(
                        expectedKey,
                        versionKeyReader.readVersionKey(moduleIdentifier.group, moduleIdentifier.name)
                    )
                }
            }
        }
    }

    @Test
    @Disabled("Not implemented yet")
    fun `check all dependencies constants have version key rules`() {
        TODO()
    }
}
