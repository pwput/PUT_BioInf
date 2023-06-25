package genetic

import genetic.domain.CellChain
import genetic.domain.ExperimentData
import kotlin.math.min

class Experiment(private val cellChainList: List<CellChain>) {

    init {
        ExperimentData.cellChainList.clear()
        ExperimentData.cellChainList.addAll(cellChainList)
    }

    private var population = Population(cellChainList)
    private var generationNumber = 1
    private var previousGenerationFitness = 0
    private var generationsWithoutFitnessImprovement = 0

    private fun reset(){
        population= Population(cellChainList)
        generationNumber = 1
        previousGenerationFitness =0
        generationsWithoutFitnessImprovement = 0
    }

    fun start(minGenerations: Int,maxGenerations: Int = ExperimentConfiguration.maxGenerations){
        while (generationNumber < minGenerations){
            reset()
            run(maxGenerations)
        }
    }

    private fun run(maxGenerations: Int = ExperimentConfiguration.maxGenerations){
        generationNumber = 1
        previousGenerationFitness = population.getPopulationFitness()
        while (generationNumber < maxGenerations && generationsWithoutFitnessImprovement <= ExperimentConfiguration.maxGenerationsWithoutFitnessImprovement){
            //println("Generation $generationNumber, PopulationFitness: ${population.getPopulationFitness()}, gwfi: ${this.generationsWithoutFitnessImprovement}")
            population.runGeneration()
            val newFitness = population.getPopulationFitness()
            if (newFitness == previousGenerationFitness){
                generationsWithoutFitnessImprovement += 1
            }
            else
                generationsWithoutFitnessImprovement = 0
            previousGenerationFitness = newFitness
            generationNumber +=1
        }
        println("Generations: $generationNumber")
        println("Best specimen: ${population.getBestSpeciment()}")
        println("Best specimen length: ${population.getBestSpeciment().length}")
        println("Best specimen fitness: ${population.getBestSpecimentFitness()}")
    }
}