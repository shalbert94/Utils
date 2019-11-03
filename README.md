# Utils

**The following is a list of classes in this repository, with breif descriptions of what they are and how to use them.**

*Broadcaster.kt*: A Rx-like implementation of the broadcaster design pattern. Make sure to unsubscribe listeners.

*SupervisorScope.kt*: A simple implementation of a `CoroutineScope`. Prefer instantiating as a global property, instead of constructor injection. A `CoroutineDispatcher` should be injected into your class and used for its initialization.

*BaseFragment.kt*: A base class that simplifies observing `LiveData` and dynamically passing a Koin parameter via `parametersOf()`.

*BaseFragmentViewModel.kt*: A base class that simplifies enabling a `Fragment`'s `ViewModel` to control a host `Activity`'s `ProgressBar`'s Visibility.

*VisibilityBindingAdapter.kt*: An implementation for enabling data-binding to control a `View`'s visibility.
