/*
* Copyright 2016 John Ahlroos
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package fi.jasoft.plugin.servers

import org.gradle.api.artifacts.DependencySet
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Runs the project on a Jetty server
 */
class JettyApplicationServer extends ApplicationServer {

    public static final String NAME = 'jetty'

    def jettyVersion = '9.3.6.v20151106'

    JettyApplicationServer(Object project, Object browserParameters) {
        super(project, browserParameters)
    }

    @Override
    String getServerRunner() {
        'fi.jasoft.plugin.JettyServerRunner'
    }

    @Override
    String getServerName() {
        'jetty'
    }

    @Override
    String getSuccessfullyStartedLogToken() {
        'org.eclipse.jetty.server.Server - Started'
    }

    @Override
    def defineDependecies(DependencyHandler projectDependencies, DependencySet dependencies) {
        def jettyAll =  projectDependencies.create("org.eclipse.jetty.aggregate:jetty-all:$jettyVersion")
        dependencies.add(jettyAll)

        def jettyAnnotations = projectDependencies.create("org.eclipse.jetty:jetty-annotations:$jettyVersion")
        dependencies.add(jettyAnnotations)

        def jettyPlus = projectDependencies.create("org.eclipse.jetty:jetty-plus:$jettyVersion")
        dependencies.add(jettyPlus)

        def jettyDeploy = projectDependencies.create("org.eclipse.jetty:jetty-deploy:$jettyVersion")
        dependencies.add(jettyDeploy)

        def slf4j = projectDependencies.create('org.slf4j:slf4j-simple:1.7.12')
        dependencies.add(slf4j)

        def asm = projectDependencies.create('org.ow2.asm:asm:5.0.3')
        dependencies.add(asm)

        def asmCommons = projectDependencies.create('org.ow2.asm:asm-commons:5.0.3')
        dependencies.add(asmCommons)

        def jspApi = projectDependencies.create('javax.servlet.jsp:jsp-api:2.2')
        dependencies.add(jspApi)
    }
}
