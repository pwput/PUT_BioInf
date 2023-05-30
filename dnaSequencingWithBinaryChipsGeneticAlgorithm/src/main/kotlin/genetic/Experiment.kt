package genetic

import genetic.domain.ExperimentData
import genetic.Experiment.Data.cellChainList
import genetic.domain.CellChain

class Experiment( cellChainList: List<CellChain>) {
    object Data: ExperimentData(cellChainList)

    private val population = Population()

    var generation = 1

    var generationsWithoutFitnessImprovement = 0

}