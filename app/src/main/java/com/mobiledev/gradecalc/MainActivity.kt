package com.mobiledev.gradecalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobiledev.DropDownMenu
import com.mobiledev.gradecalc.ui.theme.GradeCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GradeCalcTheme {
                val calculatorViewModel = viewModel<CalculatorViewModel>()
                val calculatorState = calculatorViewModel.calculatorState
                var type by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                ) {

                    Column {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            CustomTextField(
                                input = calculatorViewModel.getHomeworkValue(type),
                                label = type,
                                isValid = calculatorViewModel.isValid(
                                    calculatorViewModel.getHomeworkValue(
                                        type
                                    )
                                ),
                                onValueChange = {
                                    calculatorViewModel.onCalculatorAction(
                                        GradeCalculatorAction.Input(it, type)
                                    )
                                },
                                modifier = Modifier.width(250.dp)
                            )
                            DropDownMenu(
                                onTextChange = { type = it }
                            )
                        }
                        Text(
                            text = "Homework 1: ${calculatorState.homework1}        Homework 2: ${calculatorState.homework2} \n" +
                                    "Homework 3: ${calculatorState.homework3}        Homework 4: ${calculatorState.homework4} \n" +
                                    "Homework 5: ${calculatorState.homework5} \n"
                        )
                    }
                    CustomTextField(
                        input = calculatorState.midtermOne,
                        label = "Midterm 1",
                        isValid = calculatorViewModel.isValid(calculatorState.midtermOne),
                        onValueChange = {
                            calculatorViewModel.onCalculatorAction(
                                GradeCalculatorAction.Input(it, "MID1")
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(180.dp)
                    )

                    CustomTextField(
                        input = calculatorState.midtermTwo,
                        label = "Midterm 2",
                        isValid = calculatorViewModel.isValid(calculatorState.midtermTwo),
                        onValueChange = {
                            calculatorViewModel.onCalculatorAction(
                                GradeCalculatorAction.Input(it, "MID2")
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(180.dp)
                    )

                    CustomTextField(
                        input = calculatorState.participation,
                        label = "Participation",
                        isValid = calculatorViewModel.isValid(calculatorState.participation),
                        onValueChange = {
                            calculatorViewModel.onCalculatorAction(
                                GradeCalculatorAction.Input(it, "PART")
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(180.dp)
                    )

                    CustomTextField(
                        input = calculatorState.presentation,
                        label = "Group Presentation",
                        isValid = calculatorViewModel.isValid(calculatorState.presentation),
                        onValueChange = {
                            calculatorViewModel.onCalculatorAction(
                                GradeCalculatorAction.Input(it, "PRES")
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(180.dp)
                    )

                    CustomTextField(
                        input = calculatorState.finalProject,
                        label = "Final Project",
                        isValid = calculatorViewModel.isValid(calculatorState.finalProject),
                        onValueChange = {
                            calculatorViewModel.onCalculatorAction(
                                GradeCalculatorAction.Input(it, "FP")
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(180.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(60.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                text = "Final Grade: ${calculatorState.finalGrade}"
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                enabled = calculatorViewModel.validateCalculatorsState(),
                                onClick = {
                                    calculatorViewModel.onCalculatorAction(
                                        GradeCalculatorAction.Calculate
                                    )
                                }
                            ) {
                                Text(
                                    text = "Calculate",
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}