package labs.mamangkompii.jokenorris.view.search

data class SearchResultVModel(val joke: String, val categories: List<String> = ArrayList()) {
    fun getReadableCategories(): String {
        var result = ""
        categories.forEachIndexed { index, category ->
            result += category
            if (index != categories.size - 1) {
                result += ", "
            }
        }
        return result
    }
}