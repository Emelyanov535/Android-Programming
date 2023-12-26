package com.example.android_programming.composeui.Screens.Report

import android.app.DatePickerDialog
import android.text.format.DateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_programming.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun DatePicker(
    selectedDate: MutableState<Long>,
    onDateSelected: (Long) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = remember { mutableStateOf(DatePickerDialog(context)) }
    val dateFormatter = remember { SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Selected Date: ${dateFormatter.format(selectedDate.value)}")
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.figma_blue),
                contentColor = Color.White
            ),
            onClick = {
                datePickerDialog.value = DatePickerDialog(
                    context,
                    { _, year: Int, month: Int, dayOfMonth: Int ->
                        val selectedDateInMillis = Calendar.getInstance().apply {
                            set(year, month, dayOfMonth)
                        }.timeInMillis

                        selectedDate.value = selectedDateInMillis
                        onDateSelected(selectedDateInMillis)
                    },
                    year,
                    month,
                    day
                )
                datePickerDialog.value.show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Open Date Picker")
        }
    }
}
