/*
 * Odessa State environmental University
 * Copyright (C) 2014
 */

package ua.edu.odeku.ceem.mapRadar.tools.adminBorder.imports

import javax.swing.JPanel

import ua.edu.odeku.ceem.mapRadar.tools.{CeemRadarTool, ToolFrame}

/**
 * Created by Aleo on 22.03.14.
 */
class ImportAdminBorderTool extends CeemRadarTool {

	val form = new ImportAdminBorderForm

	val importer = new ImporterAdminBorder(this)

	override def rootPanel: JPanel = form.$$$getRootComponent$$$().asInstanceOf[JPanel]

	override def endFunction: (ToolFrame) => Unit = (toolFrame: ToolFrame) => {
		toolFrame.dispose()
		importer.viewStopImport()
	}

	override def startFunction: (ToolFrame) => Unit = (toolFrame: ToolFrame) => ()

	/**
	 * Метод для инициализации инструмента,
	 * вызовется при мервом вызове, а не в помент создания
	 */
	override def init(): Unit = {
		//none
	}
}
