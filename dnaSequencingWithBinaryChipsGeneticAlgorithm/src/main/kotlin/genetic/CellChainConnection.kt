package genetic

import genetic.domain.CellChain

internal data class CellChainConnection(
    val cellChain: CellChain,
    //how much next CellChain is covering this one
    var coverage: Int?
){

    fun getUncoveregedText(): String {
        return cellChain.text.slice(0 until cellChain.text.length- (coverage ?: 0))
    }

    fun recalculateCoverage(top: CellChain){
        coverage = cellChain.getBestCoverage(top)
    }
}