package genetic

import genetic.domain.ExperimentData
import kotlin.random.Random
import kotlin.random.nextInt

internal class Specimen(private val initIndexesList: List<Int>) {

    private val connections = mutableListOf<CellChainConnection>()

    fun MutableList<CellChainConnection>.lastIndex(): Int = this.size - 1
    fun List<Int>.lastElement(): Int = this[this.size - 1]


    fun getSpecimenDna(): String{
        var tmpDna = ""
        for (c in connections){
            tmpDna += c.getUncoveregedText()
        }
        return tmpDna
    }

    init {
        for (i in 0..initIndexesList.size - 2)
            connections.add(
                CellChainConnection(
                    ExperimentData.cellChainList[initIndexesList[i]],
                    ExperimentData.cellChainList[initIndexesList[i]].getBestCoverage(ExperimentData.cellChainList[initIndexesList[i + 1]])
                )
            )
            connections.add(
                CellChainConnection(
                    ExperimentData.cellChainList[initIndexesList.lastElement()],
                    null
                )
            )
    }

    fun mutate() {
        for (i in connections.indices) {
            if (isMutationTime()) {
                swapConnections(i, getOtherRandomConnection(i))
            }
        }
    }

    private fun recalculateConnectionIndex(index: Int) {
        if (index < connections.lastIndex())
            connections[index].recalculateCoverage(connections[index + 1].cellChain)
        else
            connections[index].coverage = null

        if (index != 0) connections[index-1].recalculateCoverage(connections[index].cellChain)
    }

    private fun swapConnections(firstIndex: Int, secondIndex: Int) {
        val tmpConn = connections[firstIndex]
        connections[firstIndex] = connections[secondIndex]
        connections[secondIndex] = tmpConn
        //TODO recalculating coverage
        recalculateConnectionIndex(firstIndex)
        recalculateConnectionIndex(secondIndex)

    }

    private fun getOtherRandomConnection(index: Int): Int {
        var otherIndex = Random.nextInt(connections.indices)
        while (otherIndex == index) {
            otherIndex = Random.nextInt(connections.indices)
        }
        return otherIndex
    }

    private fun isMutationTime(): Boolean {
        val maxNumber = 100
        val rand = Random.nextInt(0, maxNumber)
        return rand < maxNumber * ExperimentConfiguration.fixedMutationChance
    }

    fun getFitness(): Int {
        var tmpCoverageSum = 0

        for (connection in connections) {
            tmpCoverageSum += connection.coverage ?: 0
        }
        return tmpCoverageSum
    }

}