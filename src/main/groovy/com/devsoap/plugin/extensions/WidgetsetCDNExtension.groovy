/*
* Copyright 2017 John Ahlroos
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
package com.devsoap.plugin.extensions

import groovyx.net.http.AuthConfig
import org.gradle.api.Project
import org.gradle.api.provider.PropertyState

/**
 * Created by john on 1/19/17.
 */
class WidgetsetCDNExtension {

    final PropertyState<Boolean> proxyEnabled
    final PropertyState<Integer> proxyPort
    final PropertyState<String> proxyScheme
    final PropertyState<String> proxyHost
    final PropertyState<AuthConfig> proxyAuth

    WidgetsetCDNExtension(Project project){
        proxyEnabled = project.property(Boolean)
        proxyPort = project.property(Integer)
        proxyScheme = project.property(String)
        proxyHost = project.property(String)
        proxyAuth = project.property(AuthConfig)

        proxyEnabled.set(false)
        proxyPort.set(Integer.parseInt(System.getProperty('http.proxyPort') ?: '-1'))
        proxyScheme.set(System.getProperty('http.proxyScheme') ?: 'http')
        proxyHost.set(System.getProperty('http.proxyHost') ?: 'localhost')
        proxyAuth.set(null)
    }

    /**
     * Should the widgetset compiler use a proxy
     */
    Boolean getProxyEnabled() {
        proxyEnabled.get()
    }

    /**
     * Should the widgetset compiler use a proxy
     */
    void setProxyEnabled(Boolean enabled) {
        proxyEnabled.set(enabled)
    }

    /**
     * The proxy port
     */
    Integer getProxyPort() {
        proxyPort.get()
    }

    /**
     * The proxy port
     */
    void setProxyPort(Integer port) {
        proxyPort.set(port)
    }

    /**
     * The proxy scheme
     */
    String getProxyScheme() {
        proxyScheme.get()
    }

    /**
     * The proxy scheme
     */
    void setProxyScheme(String scheme) {
        proxyScheme.set(scheme)
    }

    /**
     * The proxy url
     */
    String getProxyHost() {
        proxyHost.get()
    }

    /**
     * The proxy url
     */
    void setProxyHost(String host) {
        proxyHost.set(host)
    }

    /**
     * Proxy authentication configuration
     */
    AuthConfig getProxyAuth() {
        proxyAuth.getOrNull()
    }

    /**
     * Proxy authentication configuration
     */
    void setProxyAuth(AuthConfig auth) {
        proxyAuth.set(auth)
    }
}
