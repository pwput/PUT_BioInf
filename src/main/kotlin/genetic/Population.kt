package genetic

import genetic.domain.CellChain
import kotlin.random.Random

internal class Population(private val cellChainList: List<CellChain>) {
    private val populationList = mutableListOf<Specimen>()

    init {
        generateRandomPopulation()
    }

    fun runGeneration(){
        sortPopulationByFitness()
        // populationList.forEachIndexed { index, specimen ->  println("Spno: $index, fitness:${specimen.getFitness()}")}
        replaceBottomSpecimens(crossTopSpecimens())
    }

    fun getBestSpeciment():String{
        sortPopulationByFitness()
        return populationList[0].getSpecimenDna()
    }

    fun getBestSpecimentFitness():Int{
        sortPopulationByFitness()
        return populationList[0].getFitness()
    }

    private fun replaceBottomSpecimens(new:List<Specimen>){
        var ret = removeParentsClones(new)

        ret = ret.filter { it.getFitness() >= populationList[0].getFitness() }
        //println("New speciments in this generation: ${ret.size}")

        if (ret.isEmpty()) return
        for ( i in ret.indices)
        {
            populationList[populationList.lastIndex - i] = ret[i]
        }
    }

    private fun crossTopSpecimens():List<Specimen>{
        return populationList[0].getChildren(populationList.filter { it.getFitness() > populationList[1].getFitness() * (1-ExperimentConfiguration.partOfPopulationThatCanBreed) }.random())
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
        populationList.sortByDescending { it.getFitness() }
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