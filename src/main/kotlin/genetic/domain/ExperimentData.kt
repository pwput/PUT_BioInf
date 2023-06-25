package genetic.domain

import genetic.domain.CellChain

internal object ExperimentData {
    val cellChainList = mutableListOf<CellChain>()
    var startString = ""
    var dnaLength = 0
}