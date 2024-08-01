package io.dexnet.marketkit.syncers

import android.util.Log
import io.dexnet.marketkit.managers.ExchangeManager
import io.dexnet.marketkit.models.VerifiedExchange
import io.dexnet.marketkit.providers.HsProvider
import io.dexnet.marketkit.storage.SyncerStateDao
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class VerifiedExchangeSyncer(
    private val exchangeManager: ExchangeManager,
    private val hsProvider: HsProvider,
    private val syncerStateDao: SyncerStateDao
) {
    private val keyLastSyncTimestamp = "verified-exchange-syncer-last-sync-timestamp"
    private var disposable: Disposable? = null

    fun sync(timestamp: Long) {
        val lastSyncTimestamp = syncerStateDao.get(keyLastSyncTimestamp)?.toLong() ?: 0

        if (lastSyncTimestamp == timestamp) return

        disposable = hsProvider.verifiedExchangeUids()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ verifiedExchangeUids ->
                exchangeManager.handleFetchedVerified(verifiedExchangeUids.map { VerifiedExchange(it) })
                syncerStateDao.save(keyLastSyncTimestamp, timestamp.toString())
            }, {
                Log.e("VerifiedExchangeSyncer", "Fetch error", it)
            })
    }

    fun stop() {
        disposable?.dispose()
        disposable = null
    }

}
