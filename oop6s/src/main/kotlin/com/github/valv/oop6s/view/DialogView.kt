package com.github.valv.oop6s.view

import com.github.valv.components.controls.FloatField
import com.github.valv.oop6s.extra.Category
import com.github.valv.oop6s.extra.Expense
import javafx.collections.ObservableList
import javafx.scene.control.ComboBox
import javafx.scene.layout.GridPane
import javafx.scene.control.TextField
import javafx.stage.Modality
import javafx.stage.StageStyle
import tornadofx.*

class DialogView (title: String = "", list: ObservableList<Expense>): Fragment(title) {
    override val root: GridPane by fxml()
    private val editName: TextField by fxid()
    private val editCost: FloatField by fxid()
    private val editCategory: ComboBox<Category> by fxid()

    private val expenseList: ObservableList<Expense> = list
    private var editIndex: Int = -1

    init {
        root.setOnMouseExited { exit() }
        editCategory.items.addAll(Category.values())
        editCategory.setOnScroll {
            when {
                it.deltaY < 0 -> editCategory.selectionModel.selectNext()
                it.deltaY > 0 -> editCategory.selectionModel.selectPrevious()
            }
        }
    }

    fun exit() {
        if (editIndex >= 0) {
            val expense = Expense(editName.text, editCost.text.toFloat(),
                    editCategory.selectedItem ?: Category.DEFAULT)
            expenseList[editIndex] = expense
        }
        editIndex = -1
        this.closeModal()
    }

    fun openModal(index: Int) {
        editIndex = index

        val expense: Expense = expenseList[editIndex]

        editName.text = expense.name
        editCost.text = expense.cost.toString()
        editCategory.selectionModel.select(expense.category)
        super.openModal(StageStyle.UTILITY, Modality.APPLICATION_MODAL,
                true, currentStage,  true, false)
    }
}
