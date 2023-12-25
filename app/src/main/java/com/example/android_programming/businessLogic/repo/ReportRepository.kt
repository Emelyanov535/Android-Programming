package com.example.android_programming.businessLogic.repo

interface ReportRepository {
    suspend fun getReportData()
}