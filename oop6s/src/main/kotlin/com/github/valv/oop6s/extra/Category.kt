package com.github.valv.oop6s.extra

enum class Category (private val value: String) {
    DEFAULT("Misc"),
    FOOD("Food"),
    HOUSING("Housing"),
    TRAVEL("Travel");

    override fun toString(): String {
        return value
    }
}