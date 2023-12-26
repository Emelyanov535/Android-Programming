package com.example.android_programming.businessLogic.repo

import com.example.android_programming.api.model.ReportRemote

interface ReportRepository {
    suspend fun getReportData(dateFrom: Long, dateTo: Long): ReportRemote
}