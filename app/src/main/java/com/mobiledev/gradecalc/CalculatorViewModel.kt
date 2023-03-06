package com.mobiledev.gradecalc

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mobiledev.CalculatorState
import java.math.RoundingMode

class CalculatorViewModel : ViewModel() {

    var calculatorState by mutableStateOf(CalculatorState())

    fun onCalculatorAction(gradeCalculatorAction: GradeCalculatorAction) {
        when (gradeCalculatorAction) {
            is GradeCalculatorAction.Input -> inputValue(
                gradeCalculatorAction.input,
                gradeCalculatorAction.type
            )
            is GradeCalculatorAction.Calculate -> calculateGrade()
        }
    }

    private fun inputValue(value: String, type: String) {
        when (type) {
            "Homework 1" -> calculatorState = calculatorState.copy(homework1 = value)
            "Homework 2" -> calculatorState = calculatorState.copy(homework2 = value)
            "Homework 3" -> calculatorState = calculatorState.copy(homework3 = value)
            "Homework 4" -> calculatorState = calculatorState.copy(homework4 = value)
            "Homework 5" -> calculatorState = calculatorState.copy(homework5 = value)
            "PART" -> calculatorState = calculatorState.copy(participation = value)
            "PRES" -> calculatorState = calculatorState.copy(presentation = value)
            "MID1" -> calculatorState = calculatorState.copy(midtermOne = value)
            "MID2" -> calculatorState = calculatorState.copy(midtermTwo = value)
            "FP" -> calculatorState = calculatorState.copy(finalProject = value)
        }
    }

    private fun calculateGrade() {

        val hws = (calculatorState.homework1.toInt() +
                calculatorState.homework2.toInt() +
                calculatorState.homework3.toInt() +
                calculatorState.homework4.toInt() +
                calculatorState.homework5.toInt()).div(5).times(HW)

        val midtermOne = calculatorState.midtermOne.toInt().times(MIDTERM_1)
        val midtermTwo = calculatorState.midtermTwo.toInt().times(MIDTERM_2)
        val participation = calculatorState.participation.toInt().times(PARTICIPATION)
        val presentation = calculatorState.presentation.toInt().times(PRESENTATION)
        val finalProject = calculatorState.finalProject.toInt().times(FINAL_PROJECT)

        val finalGrade = hws + midtermOne + midtermTwo + participation + presentation + finalProject

        calculatorState = calculatorState.copy(
            finalGrade = finalGrade.toBigDecimal().setScale(1, RoundingMode.UP).toString()
        )
    }

    fun getHomeworkValue(type: String): String {
        when (type) {
            "Homework 1" -> return calculatorState.homework1
            "Homework 2" -> return calculatorState.homework2
            "Homework 3" -> return calculatorState.homework3
            "Homework 4" -> return calculatorState.homework4
            "Homework 5" -> return calculatorState.homework5
        }
        return calculatorState.homework1
    }

    fun validateCalculatorsState(): Boolean {
        return isValid(calculatorState.homework1)
                && isValid(calculatorState.homework2)
                && isValid(calculatorState.homework3)
                && isValid(calculatorState.homework4)
                && isValid(calculatorState.homework5)
                && isValid(calculatorState.participation)
                && isValid(calculatorState.presentation)
                && isValid(calculatorState.midtermOne)
                && isValid(calculatorState.midtermTwo)
                && isValid(calculatorState.finalProject)
    }

    fun isValid(input: String): Boolean {
        val text = input.toIntOrNull() ?: return false
        return text in 0..100
    }

    companion object Percents {
        private const val HW = 0.2
        private const val PARTICIPATION = 0.1
        private const val PRESENTATION = 0.1
        private const val MIDTERM_1 = 0.1
        private const val MIDTERM_2 = 0.2
        private const val FINAL_PROJECT = 0.3
    }
}