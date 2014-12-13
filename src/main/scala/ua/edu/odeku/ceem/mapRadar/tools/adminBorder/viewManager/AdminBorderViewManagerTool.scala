/*
 * Odessa State environmental University
 * Copyright (C) 2014
 */

package ua.edu.odeku.ceem.mapRadar.tools.adminBorder.viewManager

import javax.swing.JPanel

import ua.edu.odeku.ceem.mapRadar.tools.{CeemRadarTool, ToolFrame}

/**
 * User: Aleo Bakalov
 * Date: 26.03.2014
 * Time: 9:51
 */
class AdminBorderViewManagerTool extends CeemRadarTool {

	val form = new AdminBorderViewForm

	override def rootPanel: JPanel = form.rootPanel()

	override def endFunction: (ToolFrame) => Unit = (tool: ToolFrame) => {}

	override def startFunction: (ToolFrame) => Unit = (tool: ToolFrame) => {}

	/**
	 * Метод для инициализации инструмента,
	 * вызовется при мервом вызове, а не в помент создания
	 */
	override def init(): Unit = {
    val handler = new AdminBorderViewManagerFormHandler(this)
	}
}
