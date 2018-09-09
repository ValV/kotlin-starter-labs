package com.github.valv.oop6s.view

import com.github.valv.oop6s.extra.Category
import com.github.valv.oop6s.extra.ComboCell
import com.github.valv.oop6s.extra.Expense
import com.github.valv.oop6s.extra.FloatCell
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import tornadofx.*

class MainView : View("IS 2018.6s VVV OOP") {
    override val root: BorderPane by fxml()
    private val editTotal: TextField by fxid()
    private val tableExpenses: TableView<Expense> by fxid()
    private val columnName: TableColumn<Expense, String> by fxid()
    private val columnCost: TableColumn<Expense, Float> by fxid()
    private val columnCategory: TableColumn<Expense, Category> by fxid()
    private val buttonEdit: Button by fxid()

    init {
        Platform.runLater { root.requestFocus() }
        val expenseList: ObservableList<Expense> = FXCollections.observableArrayList()
        val dialog = DialogView("TableView Editor", expenseList)

        tableExpenses.items = expenseList
        columnName.setCellValueFactory { it.value.nameProperty }
        columnCost.setCellFactory { FloatCell(expenseList) }
        columnCost.setCellValueFactory { it.value.costProperty.asObject() }
        columnCategory.setCellFactory { ComboCell(expenseList) }
        columnCategory.setCellValueFactory { it.value.categoryProperty }

        expenseList.add(Expense("Bakery", 15f, Category.FOOD))
        expenseList.add(Expense("Water", 10f, Category.HOUSING))
        expenseList.add(Expense("Transport", 20f, Category.TRAVEL))

        buttonEdit.setOnAction { dialog.openModal(tableExpenses.selectionModel.focusedIndex) }
    }

    fun totalUpdate(event:  javafx.event.Event) {
        fun updateFields() {
            var sum = 0f
            tableExpenses.items.forEach { sum += it.cost }
            editTotal.text =  sum.toString()
        }
        updateFields()
    }

}