package com.example.android_programming.api.repository

import com.example.android_programming.api.BackendService
import com.example.android_programming.api.model.ReportRemote
import com.example.android_programming.businessLogic.repo.ReportRepository

class RestReportRepository(private var service: BackendService) : ReportRepository {
    override suspend fun getReportData(dateFrom: Long, dateTo: Long): ReportRemote {
        return service.getReport(dateFrom, dateTo)
    }
}