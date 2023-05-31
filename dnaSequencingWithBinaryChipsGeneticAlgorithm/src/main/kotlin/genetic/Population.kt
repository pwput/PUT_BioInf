package genetic

import genetic.domain.CellChain
import kotlin.random.Random

internal class Population(private val cellChainList: List<CellChain>, private val dnaLength: Int) {
    private val populationList = mutableListOf<Specimen>()

    init {
        generateRandomPopulation()
        runGeneration()
    }

    private fun runGeneration(){
        sortPopulationByFitness()
//        replaceBottomSpecimens(crossTopSpecimens())
    }

    private fun replaceBottomSpecimens(new:List<Specimen>){
        if (new.isEmpty()) return
    }

//    private fun crossTopSpecimens():List<Specimen>{
//
//    }

    private fun sortPopulationByFitness(){
        populationList.sortBy { it.getFitness() }
    }

    private fun generateRandomPopulation(){
        for (i in 0 until ExperimentConfiguration.populationSize){
            populationList.add(Specimen(getRandomIndexes()))
        }
    }

    private fun getRandomIndexes():List<Int>{
        val setSize = (cellChainList.size * ExperimentConfiguration.partOfAllIndexesToBeUsedInPopulationGeneration).toInt()

        val tmpIndexList = mutableListOf<Int>()

        while (tmpIndexList.size < setSize){
            val tmpIndex = Random.nextInt(0,cellChainList.size)
            if (!tmpIndexList.contains(tmpIndex)) tmpIndexList.add(tmpIndex)
        }

        return tmpIndexList
    }

    fun getPopulationFitness():Int{
        var tmpSumFitness = 0
        for (specimen in populationList){
            tmpSumFitness += specimen.getFitness()
        }
        return tmpSumFitness
    }
}