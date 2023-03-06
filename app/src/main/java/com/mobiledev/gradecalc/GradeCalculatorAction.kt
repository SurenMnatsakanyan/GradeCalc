package com.mobiledev.gradecalc

sealed class GradeCalculatorAction {
    data class Input(val input: String, val type: String) : GradeCalculatorAction()
    object Calculate : GradeCalculatorAction()
}
