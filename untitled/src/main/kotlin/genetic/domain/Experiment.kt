package genetic.domain

import data.DnaData

class Experiment(val dnaData: DnaData) {
    val population = Population()

    var generation = 1

    var generationsWithoutFitnessImprovement = 0

}