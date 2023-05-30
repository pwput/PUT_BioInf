package genetic

import genetic.domain.CellChain
import kotlin.random.Random
import kotlin.random.nextInt

internal class Specimen(private val initIndexesList: List<Int>) {

    private val connections = mutableListOf<CellChainConnection>()

    init {
        for (i in 0..initIndexesList.size - 2)
            connections.add(
                CellChainConnection(
                    Experiment.Data.cellChainList[i],
                    Experiment.Data.cellChainList[i].getBestCoverage(Experiment.Data.cellChainList[i + 1])
                )
            )
    }

    fun mutate(){
        for (i in connections.indices){
            if (isMutationTime()){
                swapConnections(i,getOtherRandomConnection(i))
            }
        }
    }


    private fun swapConnections(firstIndex: Int, secondIndex: Int){
        val tmpConn = connections[firstIndex]
        connections[firstIndex] = connections[secondIndex]
        connections[secondIndex] = tmpConn
        //TODO recalculating coverage

    }

    private fun getOtherRandomConnection(index: Int): Int{
        var otherIndex = Random.nextInt(connections.indices)
        while (otherIndex == index){
            otherIndex = Random.nextInt(connections.indices)
        }
        return otherIndex
    }

    private fun isMutationTime() : Boolean{
        val maxNumber = 100
        val rand = Random.nextInt(0,maxNumber)
        return rand< maxNumber*ExperimentConfiguration.fixedMutationChance
    }

    fun getFitness(): Int {
        var tmpCoverageSum = 0

        for (connection in connections){
            tmpCoverageSum += connection.getCoverageWithChainToRight()?: 0
        }
        return tmpCoverageSum
    }

}