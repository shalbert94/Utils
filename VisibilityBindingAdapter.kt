@BindingAdapter("visibility")
fun View.setVisibility(visibility: Visibility) {
    setVisibility(visibility.viewConstant)
}

enum class Visibility(val viewConstant: Int) {
    VISIBLE(View.VISIBLE),
    INVISIBLE(View.INVISIBLE),
    GONE(View.GONE)
}
