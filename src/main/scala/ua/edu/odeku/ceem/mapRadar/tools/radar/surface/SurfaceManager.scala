package ua.edu.odeku.ceem.mapRadar.tools.radar.surface

import com.google.common.primitives.Doubles
import gov.nasa.worldwind.WorldWind
import gov.nasa.worldwind.geom.{Sector, LatLon, Angle}
import gov.nasa.worldwind.globes.{Earth, ElevationModel}
import gov.nasa.worldwind.layers.RenderableLayer
import gov.nasa.worldwind.util.BufferFactory.DoubleBufferFactory
import gov.nasa.worldwind.util.{BufferFactory, BufferWrapper}
import ua.edu.odeku.ceem.mapRadar.AppCeemRadarFrame
import ua.edu.odeku.ceem.mapRadar.tools.radar.models.Radar
import ua.edu.odeku.ceem.mapRadar.utils.gui.VisibleUtils

import scala.collection.mutable.ArrayBuffer

/**
 * Created by ABakalov on 18.12.2014.
 */
object SurfaceManager {

  val SHOW_TYPE_ISOLINE: String = "ISOLINE"
  val SHOW_TYPE_2D: String = "2D"
  val SHOW_TYPE_3D: String = "3D"

  protected val HUE_BLUE: Double = 240d / 360d
  protected val HUE_RED: Double = 0d / 360d
  protected val POLAR_RADIUS: Double = AppCeemRadarFrame.wwd.getModel.getGlobe.getPolarRadius
  protected val EQUATORIAL_RADIUS: Double = AppCeemRadarFrame.wwd.getModel.getGlobe.getEquatorialRadius
  protected val GLOBE_RADIUS: Double = (POLAR_RADIUS + EQUATORIAL_RADIUS) / 2.0

  private val NORTH: Angle = Angle.fromDegrees(0)
  private val EAST: Angle = Angle.fromDegrees(90)
  private val SOUTH: Angle = Angle.fromDegrees(180)
  private val WEST: Angle = Angle.fromDegrees(270)

  private val step: Int = 100

  private val renderableLayer: RenderableLayer = new RenderableLayer {
    setPickEnabled(false)
    setName("DistributionPowerDensityManager layer")
  }

  def hiden() {
    renderableLayer.removeAllRenderables()
    AppCeemRadarFrame.wwd.getModel.getLayers.remove(renderableLayer)
    AppCeemRadarFrame.wwd.redraw()
  }

  def show(typeShow: String, elevation: Int, radars: Iterable[Radar]): Unit = {
    VisibleUtils.insertBeforeCompass(AppCeemRadarFrame.wwd, renderableLayer)
    renderableLayer.clearList()

    val elevationModel = AppCeemRadarFrame.wwd.getModel.getGlobe.getElevationModel

    val distributionPowerDensity: AnalyticSurface = typeShow match {
      case SHOW_TYPE_3D => createDistribution3DPowerDensity(elevationModel, step, elevation, radars);
      case SHOW_TYPE_2D => createDistribution2DPowerDensity(elevationModel, step, elevation, radars);
    }

    distributionPowerDensity.setClientLayer(renderableLayer)
    renderableLayer.addRenderable(distributionPowerDensity)
    AppCeemRadarFrame.wwd.redraw()
  }

  private def createDistribution3DPowerDensity(em: ElevationModel, step: Int, roof: Double, radars: Iterable[Radar]): AnalyticSurface = {
    val sector = createSectorForAllRadar(radars, roof)
    val coordinates: Array[Array[LatLon]] = createSectorCoordinates(sector, step)
    val elevation: Array[Array[Double]] = createElevationSector(coordinates, em, roof)
    val gridPower: Array[Array[Double]] = createGridPower(coordinates, radars, roof)
    val value: Array[Array[Double]] = mergeElevationAndPower(elevation, gridPower)
    val flatValue: Array[Double] = value.flatten

    new AnalyticSurface(sector, roof, coordinates.length, coordinates(0).length) {
      setSurfaceAttributes(
        new AnalyticSurfaceAttributes {
          setDrawShadow(true)
          setInteriorOpacity(1.0)
          setOutlineWidth(3)
        }
      )

      setValues(
        AnalyticSurface.createColorGradientValues(
          flatValue,
          0.0, //Double.MAX_VALUE,
          flatValue.min / 25000,
          flatValue.max,
          HUE_BLUE,
          HUE_RED
        )
      )
    }
  }

  private def createDistribution2DPowerDensity(em: ElevationModel, step: Int, roof: Double, radars: Iterable[Radar]): AnalyticSurface = {
    val sector = createSectorForAllRadar(radars, roof)
    val coordinates: Array[Array[LatLon]] = createSectorCoordinates(sector, step)
    val elevation: Array[Array[Double]] = createElevationSector(coordinates, em, roof)
    val gridPower: Array[Array[Double]] = createGridPower(coordinates, radars, roof)
    val value: Array[Array[Double]] = mergeElevationAndPower(elevation, gridPower)
    val flatValue: Array[Double] = value.flatten

    new AnalyticSurface(sector, roof, coordinates.length, coordinates(0).length) {

      setAltitude(WorldWind.CLAMP_TO_GROUND)

      setSurfaceAttributes(
        new AnalyticSurfaceAttributes {
          setDrawShadow(false)
          setInteriorOpacity(0.6)
          setOutlineWidth(3)
        }
      )

      setValues(
        AnalyticSurface.createColorGradientValues(
          flatValue,
          0.0, //Double.MAX_VALUE,
          flatValue.min / 10000,
          flatValue.max,
          HUE_BLUE,
          HUE_RED
        )
      )
    }
  }

  implicit def arrayDoublesToBufferWrapper(value: Array[Double]): BufferWrapper = {
    val buffer: BufferWrapper = new DoubleBufferFactory().newBuffer(value.length)
    buffer.putDouble(0, value, 0, value.length)
    buffer
  }

  private def createSectorForAllRadar(radars: Iterable[Radar], researchHeight: Double): Sector = {
    var minLat = 90.0
    var minLon = 180.0
    var maxLat = 0.0
    var maxLon = -180.0

    for (radar <- radars) {
      val dist: Double = radar.radiusOnElevation(researchHeight)

      val positions = Array(
        destinationPoint(radar.latLon, NORTH, dist),
        destinationPoint(radar.latLon, EAST, dist),
        destinationPoint(radar.latLon, SOUTH, dist),
        destinationPoint(radar.latLon, WEST, dist)
      )

      for (pos <- positions) {
        minLat = Math.min(minLat, pos.latitude.degrees)
        minLon = Math.min(minLon, pos.longitude.degrees)
        maxLat = Math.max(maxLat, pos.latitude.degrees)
        maxLon = Math.max(maxLon, pos.longitude.degrees)
      }

    }
    new Sector(
      Angle.fromDegreesLatitude(minLat),
      Angle.fromDegreesLatitude(maxLat),
      Angle.fromDegreesLongitude(minLon),
      Angle.fromDegreesLongitude(maxLon)
    )
  }

  private def destinationPoint(pos: LatLon, azimuth: Angle, dist: Double): LatLon = {
    if (dist == 0) {
      new LatLon(pos)
    } else {
      val θ: Double = azimuth.radians
      val δ: Double = dist / GLOBE_RADIUS
      val φ1: Double = pos.latitude.radians
      val λ1: Double = pos.longitude.radians
      val φ2: Double = Math.asin(Math.sin(φ1) * Math.cos(δ) + Math.cos(φ1) * Math.sin(δ) * Math.cos(θ))
      var λ2: Double = λ1 + Math.atan2(Math.sin(θ) * Math.sin(δ) * Math.cos(φ1), Math.cos(δ) - Math.sin(φ1) * Math.sin(φ2))
      λ2 = (λ2 + 3 * Math.PI) % (2 * Math.PI) - Math.PI
      LatLon.fromRadians(φ2, λ2)
    }
  }

  /**
   * Метод возращает карту координат, с учетом шага
   *
   * @param sector сектор у которого надо веруть карту координат
   * @param step   шаг в координатах
   * @return карта координат
   */
  private def createSectorCoordinates(sector: Sector, step: Int): Array[Array[LatLon]] = {
    val array: ArrayBuffer[ArrayBuffer[LatLon]] = new ArrayBuffer[ArrayBuffer[LatLon]]
    val cornersSector: Array[LatLon] = sector.getCorners
    val northWestCorner: LatLon = cornersSector(3)

    var i = 0
    var exitLine = false
    while (!exitLine) {
      val pos = destinationPoint(northWestCorner, SOUTH, step * i)
      if (sector.getMinLatitude.degrees > pos.latitude.degrees) {
        exitLine = true
      } else {
        val innerArray = new ArrayBuffer[LatLon]
        var exitColumns = false
        var j = 0
        while (!exitColumns) {
          val next = destinationPoint(pos, EAST, step * j)
          if (sector.getMaxLongitude.degrees < next.longitude.degrees) {
            exitColumns = true
          } else {
            innerArray += next
          }
          j += 1
        }
        array += innerArray
      }
      i += 1
    }

    def listOfListToMatrix(sourse: ArrayBuffer[ArrayBuffer[LatLon]]): Array[Array[LatLon]] = {
      var maxHeight = sourse.length
      var maxWidth = sourse.maxBy(list => list.length).length
      val matrix = new Array[Array[LatLon]](maxHeight)
      var i = 0
      for (line <- sourse) {
        var initIndex = (maxWidth - line.length) / 2
        matrix(i) = new Array[LatLon](maxWidth)
        for (column <- line) {
          matrix(i)(initIndex) = column
          initIndex += 1
        }
        i += 1
      }
      matrix
    }
    listOfListToMatrix(array)
  }

  private def createElevationSector(sectorCoordinates: Array[Array[LatLon]], em: ElevationModel, roof: Double): Array[Array[Double]] = {
    val sectorElevation = new Array[Array[Double]](sectorCoordinates.length)
    for (i <- 0 until sectorCoordinates.length) {
      val line = sectorCoordinates(i)
      sectorElevation(i) = new Array[Double](line.length)
      for (j <- 0 until line.length) {
        val latLon = line(j)
        if (latLon != null) {
          val elevation = em.getElevation(latLon.latitude, latLon.longitude)
          sectorElevation(i)(j) = if (elevation < roof) 0 else elevation
        }
      }
    }
    sectorElevation
  }

  private def createGridPower(coordinates: Array[Array[LatLon]], radars: Iterable[Radar], elevation: Double): Array[Array[Double]] = {
    def findMax(doubles: Iterable[Double]): Double = (List(Radar.OpacityValuePower) ++ doubles).max

    def powerFromRadar(pos: LatLon, radars: Iterable[Radar], elevation: Double) = {
      for (radar <- radars) yield {
        val length = LatLon.ellipsoidalDistance(pos, radar.latLon, Earth.WGS84_EQUATORIAL_RADIUS, Earth.WGS84_POLAR_RADIUS)
        if (pos != null) {
          radar.power(length, elevation)
        } else {
          Radar.OpacityValuePower
        }
      }
    }

    (for (i <- 0 until coordinates.length) yield {
      (for (j <- 0 until coordinates(i).length) yield {
        if (coordinates(i)(j) != null) {
          findMax(powerFromRadar(coordinates(i)(j), radars, elevation))
        } else {
          0
        }
      }).toArray
    }).toArray
  }

  /**
   * Метод должен совместить рельеф и
   *
   * @param elevation
   * @param power
   * @return
   */
  private def mergeElevationAndPower(elevation: Array[Array[Double]], power: Array[Array[Double]]): Array[Array[Double]] = {
    // сделаем границы. Обойдем матрицу и везде где есть elevation мощьность сделаем Double.NaN
    for {i <- 0 until power.length
         j <- 0 until power(i).length
         if elevation(i)(j) > 0} {
      power(i)(j) = Double.NaN
    }

    // Нормализуем
    for {i <- 0 until power.length
         j <- 0 until power(i).length
         if power(i)(j) == Double.NaN} {
      power(i)(j) = 1000
    }

    power
  }
}
