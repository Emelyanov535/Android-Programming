package com.example.android_programming.api.model

import kotlinx.serialization.Serializable

@Serializable
data class ReportRemote(
    val countOrder: Int,
    val avvSum: Double? = 0.0,
    val listPopularSneaker: List<SneakerCountPair>,
    val listOrder: List<ExpandedOrderRemote>
)