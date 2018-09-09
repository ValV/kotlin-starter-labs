package com.github.valv.oop6s.extra

import com.github.valv.components.controls.FloatField
import javafx.collections.ObservableList
import javafx.scene.control.TableCell

class FloatCell (private val expenseList: ObservableList<Expense>): TableCell<Expense, Float>() {
    private val number = FloatField()
    lateinit var expense: Expense

    override fun startEdit() {
        super.startEdit()
        expense = tableView.selectionModel.selectedItem
        number.text = expense.cost.toString()
        graphic = number
        text = null
    }

    override fun cancelEdit() {
        super.cancelEdit()
        val newCost = number.text.toFloat()
        val newExpense = Expense(expense.name, newCost, expense.category)
        expenseList[expenseList.indexOf(expense)] = newExpense
        graphic = null
    }

    override fun updateItem(item: Float?, empty: Boolean) {
        super.updateItem(item, empty)
        when {
            empty -> { text = null; graphic = null }
            isEditing -> { graphic = number }
            else -> { text = getItem().toString(); graphic = null }
        }
    }
}