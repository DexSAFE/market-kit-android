package io.dexnet.marketkit.managers

import io.dexnet.marketkit.models.Post
import io.dexnet.marketkit.providers.CryptoCompareProvider
import io.reactivex.Single

class PostManager(
    private val provider: CryptoCompareProvider
) {
    fun postsSingle(): Single<List<Post>> {
        return provider.postsSingle()
    }
}
