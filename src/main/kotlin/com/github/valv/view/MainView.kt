package com.github.valv.view

import javafx.application.Platform
import javafx.scene.layout.BorderPane
import tornadofx.*

class MainView : View("IS 2018.6s VVV OOP") {
    override val root: BorderPane by fxml()

    init {
        Platform.runLater { root.requestFocus() }
    }
}