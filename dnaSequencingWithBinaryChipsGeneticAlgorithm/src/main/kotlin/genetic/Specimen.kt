package genetic

import genetic.domain.ExperimentData
import kotlin.random.Random
import kotlin.random.nextInt

internal class Specimen(private val initIndexesList: List<Int>) {

    private val connections = mutableListOf<CellChainConnection>()

    fun MutableList<CellChainConnection>.lastIndex(): Int = this.size - 1
    fun List<Int>.lastElement(): Int = this[this.size - 1]


//    fun getChildren(partner: Specimen):List<Specimen>{
//        val myListOfParts = sliceMe(getRandomCrossPoints(this))
//        val partnersListOfParts = sliceMe(getRandomCrossPoints(partner))
//
//        val childCellChainConnection = myListOfParts[0] as MutableList
//        childCellChainConnection.addAll(partnersListOfParts[1])
//        childCellChainConnection.addAll(myListOfParts[2])
//        childCellChainConnection.addAll(partnersListOfParts[3])
//
//        val tmp = mutableListOf<Int>()
//
//        for (c in childCellChainConnection){
//            tmp.add(c.cellChain)
//        }
//
//
//
//    }



     fun sliceMe(list: List<Int>): List<List<CellChainConnection>>{
        val tmpList = mutableListOf<List<CellChainConnection>>()

        tmpList.add(this.connections.slice(0 until list[0]))
        tmpList.add(this.connections.slice(list[0] until list[1]))
        tmpList.add(this.connections.slice(list[1] until list[2]))
        tmpList.add(this.connections.slice(list[2] until connections.size))

        return tmpList
    }

     fun getRandomCrossPoints(specimen: Specimen): List<Int>{
        val tmpList = mutableListOf<Int>()
        var newPoint = Random.nextInt(1,specimen.connections.lastIndex())
        tmpList.add(newPoint)

        while (tmpList.size < 3){
            newPoint = Random.nextInt(1,specimen.connections.lastIndex())
            if (!tmpList.contains(newPoint)) tmpList.add(newPoint)
        }
        return tmpList
    }

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
                    initIndexesList[i],
                    ExperimentData.cellChainList[initIndexesList[i]],
                    ExperimentData.cellChainList[initIndexesList[i]].getBestCoverage(ExperimentData.cellChainList[initIndexesList[i + 1]])
                )
            )
            connections.add(
                CellChainConnection(
                    initIndexesList.lastElement(),
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