package genetic

import genetic.domain.CellChain

internal data class CellChainConnection(
    val cellChain: CellChain,
    // connection point is an index in cellChain where connection with next cellChain to the left starts
    // in other words connecionPoint is index in CellChain where next CellChain has element with index 0,
    // so it has to be in range <1 - best coverage, this.len-1 - no coverage>
    //or null if this CellChain has no connection with next Chain on the right side and is last
    var connectionIndex: Int?
){
    fun getCoverageWithChainToRight() = connectionIndex?.let { cellChain.length() - it }
}