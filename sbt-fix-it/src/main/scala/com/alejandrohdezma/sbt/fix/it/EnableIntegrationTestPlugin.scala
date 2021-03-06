/*
 * Copyright 2019-2020 Alejandro Hernández <https://github.com/alejandrohdezma>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alejandrohdezma.sbt.fix.it

import sbt.PluginTrigger
import sbt.Plugins
import sbt._
import sbt.librarymanagement.Configuration

import org.scalafmt.sbt.ScalafmtPlugin
import org.scalafmt.sbt.ScalafmtPlugin.scalafmtConfigSettings
import scalafix.sbt.ScalafixPlugin
import scalafix.sbt.ScalafixPlugin.autoImport.scalafixConfigSettings

/**
 * This plugin activates the `IntegrationTest` configuration by
 * default in all projects and adds the scalafix/scalafmt
 * settings for the `it` configuration.
 *
 * If this is not the desired outcome for a certain project add:
 *
 * {{{
 * .disablePlugins(EnableIntegrationTestPlugin)
 * }}}
 */
object EnableIntegrationTestPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = ScalafmtPlugin && ScalafixPlugin

  override def projectConfigurations: Seq[Configuration] = Seq(IntegrationTest)

  override def projectSettings: Seq[Def.Setting[_]] = inConfig(IntegrationTest) {
    Defaults.testSettings ++ scalafixConfigSettings(IntegrationTest) ++ scalafmtConfigSettings
  }

}
