package com.github.valv.oop6s.view

import javafx.application.Platform
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import tornadofx.*

class MainView : View("IS 2018.6s VVV OOP") {
    override val root: BorderPane by fxml()
    private val editProductA: TextField by fxid()
    private val editProductB: TextField by fxid()
    private val editTotal: TextField by fxid()
    private val editAverage: TextField by fxid()
    private val lastChanged: Label by fxid()

    init {
        Platform.runLater { root.requestFocus() }
        //editProductA.setOnAction { totalUpdate() }
        //editProductB.setOnAction { totalUpdate() }
        editTotal.setOnAction { totalUpdate(it) }
        editAverage.setOnAction { totalUpdate(it) }
    }

    fun handleReset() {
        editProductA.text = "0"
        editProductB.text = "0"
        editTotal.text = "0"
        editAverage.text = "0"
        lastChanged.text = "..."
    }

    fun totalUpdate(event: javafx.event.ActionEvent) {
        fun updateFields() {
            val sum = editProductA.text.toFloat() + editProductB.text.toFloat()
            editTotal.text = sum.toString()
            editAverage.text = (sum / 2).toString()
        }

        when (event.source) {
            editProductA -> updateFields()
            editProductB -> updateFields()
        }

        lastChanged.text = event.source.toString()
    }
}