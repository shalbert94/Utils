open class Broadcaster<DataT> {

    private val broadcastListeners: MutableList<(DataT) -> Unit> = mutableListOf()

    fun subscribe(listener: (DataT) -> Unit): Subscription {
        broadcastListeners += listener
        return object : Subscription {
            override fun unsubscribe() {
                broadcastListeners.remove(listener)
            }
        }
    }

    protected open fun broadcast(data: DataT) {
        broadcastListeners.forEach { it(data) }
    }
}

class MutableBroadcaster<DataT> : Broadcaster<DataT>() {
    public override fun broadcast(data: DataT) {
        super.broadcast(data)
    }
}

interface Subscription {
    fun unsubscribe()
}

class SubscriptionCollection : Subscription {
    private val subscriptions = mutableListOf<Subscription>()

    override fun unsubscribe() {
        subscriptions.forEach { it.unsubscribe() }
    }

    operator fun plusAssign(newSubscription: Subscription) {
        subscriptions += newSubscription
    }

}