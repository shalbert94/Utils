open class BaseFragmentViewModel(private val mainViewModel: MainViewModel) : BaseViewModel() {
    val lifecycleObserver = FragmentLifecycleObserver()

    private var loadingCount = 0
        set(value) {
            field = value
            updateProgressBarVisibility()
        }

    private var progressBarVisibility: Visibility?
        get() = mainViewModel.progressBarVisibility.get()
        set(value) {
            mainViewModel.progressBarVisibility.set(value)
        }

    private val isLoading get() = loadingCount > 0

    protected fun load(work: suspend () -> Unit) = viewModelScope.launch {
        loadingCount++
        work.invoke()
        loadingCount--
    }

    fun showProgressBar() {
        loadingCount++
    }

    fun hideProgressBar() {
        if (isLoading) loadingCount--
    }

    private fun updateProgressBarVisibility() {
        when {
            loadingCount > 0 -> progressBarVisibility = Visibility.VISIBLE
            loadingCount == 0 -> progressBarVisibility = Visibility.GONE
            loadingCount < 0 -> Timber.e("Loading count is lower than 0 with a value of $loadingCount")
        }
    }

    inner class FragmentLifecycleObserver : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause() {
            progressBarVisibility = Visibility.GONE
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() {
            if (loadingCount > 0) {
                progressBarVisibility = Visibility.VISIBLE
            }
        }
    }
}
