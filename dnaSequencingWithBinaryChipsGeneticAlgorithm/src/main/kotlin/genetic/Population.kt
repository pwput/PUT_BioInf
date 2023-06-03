package genetic

import genetic.domain.CellChain
import kotlin.random.Random

internal class Population(private val cellChainList: List<CellChain>, private val dnaLength: Int) {
    private val populationList = mutableListOf<Specimen>()

    init {
        generateRandomPopulation()
    }

    fun runGeneration(){
        sortPopulationByFitness()
        replaceBottomSpecimens(crossTopSpecimens())
    }

    private fun replaceBottomSpecimens(new:List<Specimen>){
        val ret = removeParentsClones(new)

        if (ret.isEmpty()) return

        for ( i in ret.indices)
        {
            populationList[populationList.lastIndex - i] = ret[i]
        }
    }

    private fun crossTopSpecimens():List<Specimen>{
        return populationList[0].getChildren(populationList[1])
    }

    private fun removeParentsClones(new:List<Specimen>):List<Specimen>{
        val ret = mutableListOf<Specimen>()
        ret.addAll(new)
        var i =0
        while (i < ret.lastIndex){
            if (ret[i].isSame(populationList[0]) || ret[i].isSame(populationList[1]))
                ret.removeAt(i)
            else
                i+=1
        }
        return ret
    }

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