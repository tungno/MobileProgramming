package com.example.levelmeasurementapp.ui

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class OrientationData(val pitch: Float, val roll: Float)

class LevelViewModel(application: Application) : AndroidViewModel(application), SensorEventListener {

    private val sensorManager: SensorManager =
        application.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val magnetometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    private val _orientation = MutableStateFlow(OrientationData(0f, 0f))
    val orientation: StateFlow<OrientationData> = _orientation

    private var gravity: FloatArray? = null
    private var geomagnetic: FloatArray? = null

    init {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            when (it.sensor.type) {
                Sensor.TYPE_ACCELEROMETER -> gravity = it.values.clone()
                Sensor.TYPE_MAGNETIC_FIELD -> geomagnetic = it.values.clone()
            }

            if (gravity != null && geomagnetic != null) {
                val R = FloatArray(9)
                val I = FloatArray(9)
                val success = SensorManager.getRotationMatrix(R, I, gravity, geomagnetic)
                if (success) {
                    val orientationValues = FloatArray(3)
                    SensorManager.getOrientation(R, orientationValues)
                    val pitch = orientationValues[1]
                    val roll = orientationValues[2]

                    // Update the state flow
                    _orientation.value = OrientationData(pitch, roll)
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Handle accuracy changes if necessary
    }

    override fun onCleared() {
        super.onCleared()
        sensorManager.unregisterListener(this)
    }
}
