/*
 * Odessa State environmental University
 * Copyright (C) 2013
 */

package ua.edu.odeku.ceem.mapRadar.tools

import java.util.ResourceBundle

import ua.edu.odeku.ceem.mapRadar.settings.Settings

/**
 * User: Aleo Bakalov
 * Date: 10.12.13
 * Time: 17:08
 */
trait NamingTool {

  def nameTool: String = this.getClass.getName

  def name: String = {
    val locale = Settings.Program.locale
    if (ResourceBundle.getBundle("bundles/tools", locale).containsKey(nameTool)) {
      ResourceBundle.getBundle("bundles/tools", locale).getString(nameTool)
    } else {
      nameTool
    }
  }

}
