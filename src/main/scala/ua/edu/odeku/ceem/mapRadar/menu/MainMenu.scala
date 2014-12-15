/*
 * Odessa State environmental University
 * Copyright (C) 2014
 */

package ua.edu.odeku.ceem.mapRadar.menu

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.JMenuItem

import ua.edu.odeku.ceem.mapRadar.AppCeemRadarFrame
import ua.edu.odeku.ceem.mapRadar.settings.SettingsFrame

/***********************************************************************************************************************
  * Об'єкт який створює головне меню
  *
  * Created by Алексей on 31.05.2014.
  * *********************************************************************************************************************/
object MainMenu extends MenuCreator {

	override def nameMenu: String = resourceBundle.getString("program")

	override def menuItems: Array[JMenuItem] = createMenus()


	private def createMenus() = {
		val close = new JMenuItem(resourceBundle.getString("close"))
		close.addActionListener(new ActionListener {
			override def actionPerformed(e: ActionEvent): Unit = {
				System.exit(0)
				AppCeemRadarFrame.dispose()

			}
		})

    val settings = new JMenuItem(resourceBundle.getString("settings"))
    settings.addActionListener(new ActionListener {
      override def actionPerformed(p1: ActionEvent): Unit = {
        val dialog = new SettingsFrame
        dialog.setVisible(true)
      }
    })

		Array(settings, close)
	}
}
