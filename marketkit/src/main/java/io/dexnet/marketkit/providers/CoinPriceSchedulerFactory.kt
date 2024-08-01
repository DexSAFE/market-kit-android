package io.dexnet.marketkit.providers

import io.dexnet.marketkit.managers.CoinPriceManager
import io.dexnet.marketkit.managers.ICoinPriceCoinUidDataSource
import io.dexnet.marketkit.Scheduler

class CoinPriceSchedulerFactory(
    private val manager: CoinPriceManager,
    private val provider: HsProvider
) {
    fun scheduler(currencyCode: String, coinUidDataSource: ICoinPriceCoinUidDataSource): Scheduler {
        val schedulerProvider = CoinPriceSchedulerProvider(currencyCode, manager, provider)
        schedulerProvider.dataSource = coinUidDataSource
        return Scheduler(schedulerProvider)
    }
}
