open class BaseFragment : Fragment() {

    fun <T> LiveData<T>.observe(useViewLifecycle: Boolean = true, observer: (T) -> Unit) =
        observe(
            if (useViewLifecycle) viewLifecycleOwner else this@BaseFragment,
            Observer { observer(it) }
        )

}

inline fun <reified T : BaseFragmentViewModel> BaseFragment.bindViewModelToMain(vararg otherParameters: Any): T =
    getViewModel<T> {
        val mainViewModel by sharedViewModel<MainViewModel>()
        parametersOf(mainViewModel, *otherParameters)
    }.also { lifecycle.addObserver(it.lifecycleObserver) }
