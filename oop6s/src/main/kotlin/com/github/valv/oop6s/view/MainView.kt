package com.github.valv.oop6s.view

import com.github.valv.components.controls.ProductBox
import javafx.application.Platform
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import tornadofx.*

class MainView : View("IS 2018.6s VVV OOP") {
    override val root: BorderPane by fxml()
    private val editTotal: TextField by fxid()
    private val lastChanged: Label by fxid()
    private val boxA: ProductBox by fxid()
    private val boxB: ProductBox by fxid()
    private val boxAvg: ProductBox by fxid()

    init {
        Platform.runLater { root.requestFocus() }
    }

    fun handleReset() {
        boxA.field = "0.0"
        boxB.field = "0.0"
        boxAvg.field = "0.0"
        editTotal.text = "0.0"
        lastChanged.text = "..."
    }

    fun totalUpdate(event: javafx.event.ActionEvent) {
        fun updateFields() {
            val sum = boxA.field.toFloat() + boxB.field.toFloat()
            boxAvg.field = (sum / 2).toString()
            editTotal.text = sum.toString()
        }

        when (event.source) {
            boxA -> updateFields()
            boxB -> updateFields()
        }

        lastChanged.text = event.source.toString()
    }
}