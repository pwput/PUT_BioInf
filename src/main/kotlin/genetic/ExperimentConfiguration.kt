package genetic

object ExperimentConfiguration {
    const val populationSize = 120
    const val maxGenerations = 50000
    const val sameLengthBonus = 30
    const val sameStartBonus = 30
    const val maxGenerationsWithoutFitnessImprovement = 400
    const val fixedMutationChance = 0.1f
    const val partOfAllIndexesToBeUsedInPopulationGeneration = 1
    const val partOfPopulationThatCanBreed = 0.75f
}