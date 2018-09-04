package com.github.valv.oop6s.app

import javafx.beans.property.*

class Expense(name: String = "", cost: Float = 0f, category: String = "none") {
    private var name: StringProperty = SimpleStringProperty(name)
    private var cost: FloatProperty = SimpleFloatProperty(cost)
    private var category: StringProperty = SimpleStringProperty(category)

    fun getName() = name.get()
    fun nameProperty() = name
    fun setName(value: String) = name.set(value)

    fun getCost() = cost.get()
    fun costProperty() = cost
    fun setCost(value: Float) = cost.set(value)

    fun getCategory() = category.get()
    fun categoryProperty() = category
    fun setCategory(value: String) = category.set(value)
}