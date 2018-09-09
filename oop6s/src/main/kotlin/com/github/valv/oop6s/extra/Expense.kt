package com.github.valv.oop6s.extra

import javafx.beans.property.*
import tornadofx.*

class Expense(name: String = "", cost: Float = 0f, category: Category = Category.DEFAULT) {
    var nameProperty = SimpleStringProperty(name)
    var name by nameProperty

    val costProperty = SimpleFloatProperty(cost)
    var cost by costProperty

    val categoryProperty = SimpleObjectProperty<Category>(category)
    var category by categoryProperty
}