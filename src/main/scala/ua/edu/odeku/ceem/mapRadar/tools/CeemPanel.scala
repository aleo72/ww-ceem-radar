package ua.edu.odeku.ceem.mapRadar.tools

import javax.swing.JPanel

/**
 * Этот интерфейс должены реализовывать все Tool которые имеют панели
 * User: Aleo Bakalov
 * Date: 10.12.13
 * Time: 17:23
 */
trait CeemPanel {
  def getRootPanel: JPanel
}
