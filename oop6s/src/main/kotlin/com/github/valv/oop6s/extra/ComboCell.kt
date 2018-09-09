package com.github.valv.oop6s.extra

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.ComboBox
import javafx.scene.control.TableCell
import tornadofx.*

class ComboCell (private val expenseList: ObservableList<Expense>): TableCell<Expense, Category>() {
    private val enumeration = ComboBox<Category>(FXCollections.observableList(Category.values().asList()))
    lateinit var expense: Expense

    override fun startEdit() {
        super.startEdit()
        expense = tableView.selectionModel.selectedItem
        enumeration.selectionModel.select(expense.category.ordinal)
        graphic = enumeration
        text = null
    }

    override fun cancelEdit() {
        super.cancelEdit()
        val newCategory = enumeration.selectedItem ?: expense.category
        val newExpense = Expense(expense.name, expense.cost, newCategory)
        expenseList[expenseList.indexOf(expense)] = newExpense
        graphic = null
    }

    override fun updateItem(item: Category?, empty: Boolean) {
        super.updateItem(item, empty)
        when {
            empty -> { text = null; graphic = null }
            isEditing -> { graphic = enumeration }
            else -> { text = item.toString(); graphic = null }
        }
    }
}