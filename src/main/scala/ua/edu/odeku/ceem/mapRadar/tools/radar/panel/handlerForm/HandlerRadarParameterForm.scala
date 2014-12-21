/*
 * Odessa State environmental University
 * Copyright (C) 2014
 */

package ua.edu.odeku.ceem.mapRadar.tools.radar.panel.handlerForm

import java.awt.Dimension
import javax.swing.{JSpinner, SpinnerNumberModel}

import ua.edu.odeku.ceem.mapRadar.settings.Settings
import ua.edu.odeku.ceem.mapRadar.tools.radar.models.Radar
import ua.edu.odeku.ceem.mapRadar.tools.radar.panel.RadarParameterForm

/**
 * Обработчик форми з параметрами
 * Created by Aleo on 29.07.2014.
 */
class HandlerRadarParameterForm(val form: RadarParameterForm) {

  initSpinners()

  private def initSpinners() {

    implicit def AnyValToAnyRef(v: AnyVal) = v.asInstanceOf[AnyRef]

    val spinners = Array(form.altitudeSpinner,
      form.pulsePowerSpinner,
      form.waveLengthSpinner,
      form.antennaDiameterSpinner,
      form.reflectivityMeteoGoalsSpinner,
      form.attenuationSpinner,
      form.radiusSpinner,
      form.grainFactorSpinner,
      form.durationPulseSpinner
    )

    form.altitudeSpinner.setModel(new SpinnerNumberModel(
      Settings.Program.Tools.Radar.defaultAltitude,
      Settings.Program.Tools.Radar.minAltitude,
      Settings.Program.Tools.Radar.maxAltitude,
      Settings.Program.Tools.Radar.stepAltitude)
    )
    form.pulsePowerSpinner.setModel(new SpinnerNumberModel(Settings.Program.Tools.Radar.pulsePower, Double.MinValue, Double.MaxValue, 0.5))
    form.waveLengthSpinner.setModel(new SpinnerNumberModel(Settings.Program.Tools.Radar.pulsePower, Double.MinValue, Double.MaxValue, 0.5))
    form.antennaDiameterSpinner.setModel(new SpinnerNumberModel(Settings.Program.Tools.Radar.antennaDiameter, 0.001, 10000, 0.1))
    form.reflectivityMeteoGoalsSpinner.setModel(new SpinnerNumberModel(Settings.Program.Tools.Radar.reflectivityMeteoGoals, Double.MinValue, Double.MaxValue, 0.5))
    form.attenuationSpinner.setModel(new SpinnerNumberModel(Settings.Program.Tools.Radar.attenuation, Double.MinValue, Double.MaxValue, 0.5))
    form.radiusSpinner.setModel(new SpinnerNumberModel(Settings.Program.Tools.Radar.radius, 1.0, 10000000.0, 100))
    form.grainFactorSpinner.setModel(new SpinnerNumberModel(Settings.Program.Tools.Radar.grainFactor, Double.MinValue, Double.MaxValue, 100))
    form.grainFactorSpinner.setValue(Settings.Program.Tools.Radar.grainFactor)
    form.durationPulseSpinner.setModel(new SpinnerNumberModel(Settings.Program.Tools.Radar.durationPulse, Double.MinValue, Double.MaxValue, 10))
    form.durationPulseSpinner.setValue(Settings.Program.Tools.Radar.durationPulse)

    spinners.foreach( spinner=> spinner.getEditor.asInstanceOf[JSpinner.DefaultEditor].getTextField.setColumns(25))

  }

  def updateFields(radar: Radar) = {

    form.nameTextField.setText(radar.name)

    form.altitudeSpinner.setValue(radar.altitude)
    form.pulsePowerSpinner.setValue(radar.pulsePower)
  }

  def name = form.nameTextField.getText

  def altitude = form.altitudeSpinner.getValue.asInstanceOf[Double].toInt

  def pulsePower = form.pulsePowerSpinner.getValue.asInstanceOf[Double]

  def waveLength = form.waveLengthSpinner.getValue.asInstanceOf[Double]

  def antennaDiameter = form.antennaDiameterSpinner.getValue.asInstanceOf[Double]

  def reflectivityMeteoGoals = form.reflectivityMeteoGoalsSpinner.getValue.asInstanceOf[Double]

  def attenuation = form.attenuationSpinner.getValue.asInstanceOf[Double]

  def radius = form.radiusSpinner.getValue.asInstanceOf[Double]

  def grainFactor = form.grainFactorSpinner.getValue.asInstanceOf[Double]

  def durationPulse = form.durationPulseSpinner.getValue.asInstanceOf[Double]
}
