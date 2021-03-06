/*
 * Copyright (c) 2014 Red Hat, Inc. and others
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 */

/**
 * === Vault Config Store
 *
 * The Vault Store extends the Vert.x Configuration Retriever and provides support for Vault (https://www.vaultproject.io/).
 * So, configuration (secrets) is retrieved from Vault.
 *
 * ==== Using the Vault Config Store
 *
 * To use the Vault Config Store, add the following dependency to the
 * _dependencies_ section of your build descriptor:
 *
 * * Maven (in your `pom.xml`):
 *
 * [source,xml,subs="+attributes"]
 * ----
 * <dependency>
 * <groupId>${maven.groupId}</groupId>
 * <artifactId>${maven.artifactId}</artifactId>
 * <version>${maven.version}</version>
 * </dependency>
 * <dependency>
 * <groupId>${maven.groupId}</groupId>
 * <artifactId>vertx-config</artifactId>
 * <version>${maven.version}</version>
 * </dependency>
 * ----
 *
 * * Gradle (in your `build.gradle` file):
 *
 * [source,groovy,subs="+attributes"]
 * ----
 * compile '${maven.groupId}:vertx-config:${maven.version}'
 * compile '${maven.groupId}:${maven.artifactId}:${maven.version}'
 * ----
 *
 * ==== Configuring the store
 *
 * Once added to your classpath or dependencies, you need to configure the
 * {@link io.vertx.config.ConfigRetriever} to use this store:
 *
 * [source, $lang]
 * ----
 * {@link examples.Examples#example1(io.vertx.core.Vertx, io.vertx.core.json.JsonObject)}
 * ----
 *
 * To use the Vault config store, set the `type` to `vault`. The configuration is provided as Json. It configures the
 * access to Vault, authentication and the path of the secret to retrieve:
 *
 * [source, $lang]
 * ----
 * {@link examples.Examples#example1WithConfig(io.vertx.core.Vertx)}}
 * ----
 *
 * The `vault_config` object can contain the HTTP client / Web client configuration such as trust stores, timeout,
 * certificates, port and host. The `path` and `host` entries are mandatory. The `path` indicates the secret to
 * retrieve. The `host` is the hostname of the Vault server. By default the port 8200 is used. SSL is disabled by
 * default, but you should enable it for production settings.
 *
 * Then, you need to use one of the following method to configure the token to use or the authentication mechanism.
 *
 * ==== Using an existing token
 *
 * If you know the token to use, set the `token` entry in the configuration:
 *
 * [source, $lang]
 * ----
 * {@link examples.Examples#exampleWithToken(io.vertx.core.Vertx, java.lang.String)}
 * ----
 *
 * You can use the root token, but it's not recommended. When the token is revoked, the access to the secret is
 * blocked. If the token is renewable, the token is renewed when it expires.
 *
 * ==== Generating a token
 *
 * If you have a token allowing you to generate new token, you can request the token generation:
 *
 * [source, $lang]
 * ----
 * {@link examples.Examples#exampleWithTokenCreation(io.vertx.core.Vertx, java.lang.String)}
 * ----
 *
 * When using this approach, no token must be provided in the root configuration, the the token use to request the
 * generation is passed in the nested JSON structure. If the generated token is renewable, it will be
 * renewed automatically upon expiration. The `renew-window` is the time window to add to the token validity to renew
 * it. If the generated token is revoked, the access to the secret is blocked.
 *
 * ==== Using certificates
 *
 * You can use TLS certificates as authentication mechanism. So, you don't need to know a token, the token is
 * generated automatically.
 *
 * [source, $lang]
 * ----
 * {@link examples.Examples#exampleWithCerts(io.vertx.core.Vertx)}
 * ----
 *
 * Check out the HTTP client and Web client configuration to pass the certificates. If the generated token is
 * renewable, it will be renewed. If not, the store attempts to authenticate again.
 *
 * ==== Using AppRole
 *
 * `AppRole` is used when your application is known by Vault and you have the `appRoleId` and `secretId`. You don't
 * need a token, the token being generated automatically:
 *
 * [source, $lang]
 * ----
 * {@link examples.Examples#exampleWithAppRole(io.vertx.core.Vertx, java.lang.String, java.lang.String)}
 * ----
 *
 * If the generated token is renewable, it will be renewed. If not, the store attempts to authenticate again.
 *
 * ==== Using username and password
 *
 * The `userpass` auth backend is used when the user / app is authenticated using a username/password. You don't need a
 * token as the token is generated during the authentication process:
 *
 * [source, $lang]
 * ----
 * {@link examples.Examples#exampleWithUserPass(io.vertx.core.Vertx, java.lang.String, java.lang.String)}
 * ----
 *
 * If the generated token is renewable, it will be renewed. If not, the store attempts to authenticate again.
 */
@Document(fileName = "vault-store.adoc")
@ModuleGen(name = "vertx-config", groupPackage = "io.vertx")
package io.vertx.config.vault;

import io.vertx.codegen.annotations.ModuleGen;
import io.vertx.docgen.Document;
