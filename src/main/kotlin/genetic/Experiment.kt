package genetic

import genetic.domain.CellChain
import genetic.domain.ExperimentData

class Experiment(cellChainList: List<CellChain>, dnaLength: Int) {

    init {
        ExperimentData.cellChainList.clear()
        ExperimentData.cellChainList.addAll(cellChainList)

        ExperimentData.dnaLength = dnaLength
    }

    private val population = Population(cellChainList, dnaLength)

    var generation = 1

    var generationsWithoutFitnessImprovement = 0

}