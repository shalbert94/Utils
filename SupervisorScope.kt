class SupervisorScope(dispatcher: CoroutineDispatcher) : CoroutineScope {

    override val coroutineContext: CoroutineContext = SupervisorJob() + dispatcher
}
