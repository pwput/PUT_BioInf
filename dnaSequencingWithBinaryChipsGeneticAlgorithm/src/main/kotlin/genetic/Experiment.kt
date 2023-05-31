package genetic

import genetic.domain.ExperimentData
import genetic.domain.CellChain

class Experiment( cellChainList: List<CellChain>) {

    init {
        ExperimentData.cellChainList.clear()
        ExperimentData.cellChainList.addAll(cellChainList)
    }

    private val population = Population()

    var generation = 1

    var generationsWithoutFitnessImprovement = 0

}