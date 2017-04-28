package fi.jasoft.plugin.integration

import org.junit.Test

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue

/**
 * Created by john on 4/29/17.
 */
class SpringBootTest extends IntegrationTest {

    @Override
    protected void applyBuildScriptRepositories(File buildFile) {
        super.applyBuildScriptRepositories(buildFile)
        buildFile << "maven { url 'https://plugins.gradle.org/m2/' }\n"
    }

    @Override
    protected void applyBuildScriptClasspathDependencies(File buildFile) {
        super.applyBuildScriptClasspathDependencies(buildFile)
        buildFile << "classpath 'org.springframework.boot:spring-boot-gradle-plugin:1.5.3.RELEASE'\n"
    }

    @Override
    protected void applyThirdPartyPlugins(File buildFile) {
        super.applyThirdPartyPlugins(buildFile)
        buildFile << "apply plugin: 'org.springframework.boot'\n"
    }

    @Test void 'Jar is built by default'() {
        def output = runFailureExpected('--info','build')

        assertTrue output.contains(':jar')
        assertFalse output.contains(':war')

        assertTrue output.contains('Applying JavaPluginAction')
        assertTrue output.contains('Applying SpringBootAction')
        assertFalse output.contains('Applying WarPluginAction')
        assertTrue output.contains('Applying VaadinPluginAction')
    }

    @Test void 'War is built if applied'() {
        buildFile << "apply plugin: 'war'\n"

        def output = runFailureExpected('--info','build')

        assertTrue output.contains(':war')
        assertFalse output.contains(':jar')

        assertTrue output.contains('Applying JavaPluginAction')
        assertTrue output.contains('Applying SpringBootAction')
        assertTrue output.contains('Applying WarPluginAction')
        assertTrue output.contains('Applying VaadinPluginAction')
    }
}