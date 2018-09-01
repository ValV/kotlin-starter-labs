package com.github.valv.components.controls

import javafx.beans.NamedArg
import javafx.beans.property.ObjectProperty
import javafx.beans.property.ObjectPropertyBase
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.Region

class ProductBox (@NamedArg("text") val text: String): HBox() {
    private val textLabel = Label(text)
    private val textField = FloatField()

    var label: String
        get() = textLabel.text
        set(value) {textLabel.text = value}
    var field: String
        get() = textField.text
        set(value) {textField.text = value}

    private val onAction: ObjectProperty<EventHandler<ActionEvent>> = object: ObjectPropertyBase<EventHandler<ActionEvent>>() {
        override fun invalidated() = setEventHandler(ActionEvent.ACTION, get())
        override fun getBean(): Any = this
        override fun getName(): String = "onAction"
    }

    fun onActionProperty(): ObjectProperty<EventHandler<ActionEvent>> = onAction
    fun getOnAction(): EventHandler<ActionEvent> = onActionProperty().get()
    fun setOnAction(value: EventHandler<ActionEvent>) = onActionProperty().set(value)

    init {
        textLabel.minWidth = Region.USE_PREF_SIZE
        textLabel.maxWidth = Region.USE_PREF_SIZE
        textField.addEventHandler(ActionEvent.ACTION) { e -> this.fireEvent(e) }
        textField.minWidth = textField.height * 3 / 2
        textField.maxWidth = Region.USE_PREF_SIZE
        this.alignment = Pos.CENTER
        this.spacing = 4.0
        this.children.addAll(textLabel, textField)
    }
}