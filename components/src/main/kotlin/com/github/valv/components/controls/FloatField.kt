package com.github.valv.components.controls

import javafx.scene.control.TextField
import javafx.scene.control.TextFormatter

class FloatField (text: String = "0.0"): TextField(text) {
    init {
        this.textFormatter = TextFormatter<String> {
            if (it.controlNewText.isEmpty()) {
                it
            } else {
                try {
                    it.controlNewText.toFloat()
                    it
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }
    }
}