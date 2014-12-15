/*
 * Odessa State environmental University
 * Copyright (C) 2014
 */

package ua.edu.odeku.ceem.mapRadar

import ua.edu.odeku.ceem.mapRadar.tools.cache.CacheDownloadTool
import ua.edu.odeku.ceem.mapRadar.tools.geoName.imports.ImportGeoNameTool
import ua.edu.odeku.ceem.mapRadar.tools.geoName.view.ViewGeoNameTool
import ua.edu.odeku.ceem.mapRadar.tools.radar.RadarManagerTool

/**
 * Created by Aleo on 13.01.14.
 */
package object tools {
	val arrayTool: Array[String] = Array(
		classOf[ImportGeoNameTool].getName,
		classOf[CacheDownloadTool].getName,
		classOf[ViewGeoNameTool].getName,
		classOf[RadarManagerTool].getName
	)
}