package com.example.android_programming.composeui.Screens.Report


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.android_programming.R
import com.example.android_programming.businessLogic.vmodel.AppViewModelProvider
import com.example.android_programming.businessLogic.vmodel.ReportViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ReportCard(reportViewModel: ReportViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.figma))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DatePicker(
                selectedDate = reportViewModel.dateFrom,
                onDateSelected = { date ->
                    reportViewModel.dateFrom.value = date
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            DatePicker(
                selectedDate = reportViewModel.dateTo,
                onDateSelected = { date ->
                    reportViewModel.dateTo.value = date
                }
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.figma_blue),
                    contentColor = Color.White
                ),
                onClick = {
                    if(reportViewModel.dateTo.value <= reportViewModel.dateFrom.value){
                        Toast.makeText(context, "Inccorect date range", Toast.LENGTH_SHORT).show()
                    }else{
                        reportViewModel.updateReportData(reportViewModel.dateFrom.value, reportViewModel.dateTo.value)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Get report")
            }
        }
    }
}


