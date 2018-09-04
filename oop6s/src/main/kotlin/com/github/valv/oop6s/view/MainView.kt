package com.github.valv.oop6s.view

import com.github.valv.components.controls.ProductBox
import com.github.valv.oop6s.app.Expense
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.Label
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.BorderPane
import tornadofx.*

class MainView : View("IS 2018.6s VVV OOP") {
    override val root: BorderPane by fxml()
    private val editTotal: TextField by fxid()
    private val tableExpenses: TableView<Expense> by fxid()
    private val columnName: TableColumn<Expense, String> by fxid()
    private val columnCost: TableColumn<Expense, Float> by fxid()
    private val columnCategory: TableColumn<Expense, String> by fxid()

    init {
        Platform.runLater { root.requestFocus() }
        var expenseList: ObservableList<Expense> = FXCollections.observableArrayList()

        tableExpenses.items = expenseList
        columnName.setCellValueFactory { it.value.nameProperty() }
        columnCost.setCellValueFactory { it.value.costProperty().asObject() }
        columnCategory.setCellValueFactory { it.value.categoryProperty() }

        expenseList.add(Expense("Bakery", 15f, "Food"))
        expenseList.add(Expense("Water", 10f, "Housing"))
        expenseList.add(Expense("Transport", 20f, "Travel"))
    }

    fun totalUpdate(event: javafx.event.Event) {
        fun updateFields() {
            var sum = 0f
            tableExpenses.items.forEach { sum += it.getCost() }
            editTotal.text =  sum.toString()
        }
        updateFields()
    }

}