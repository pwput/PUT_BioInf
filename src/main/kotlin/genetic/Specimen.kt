package genetic

import genetic.domain.ExperimentData
import kotlin.random.Random
import kotlin.random.nextInt

internal class Specimen(private val initIndexesList: List<Int>) {

    private val connections = mutableListOf<CellChainConnection>()

    fun MutableList<CellChainConnection>.lastIndex(): Int = this.size - 1
    fun List<Int>.lastElement(): Int = this[this.size - 1]

    fun isSame(other: Specimen) = this.getSpecimenDna() == other.getSpecimenDna()

    fun getChildren(partner: Specimen): List<Specimen> {
        val list = mutableListOf<Specimen>()

        for (i in IntRange(0, 5)) {
            list.addAll(getChildrenForRandomCrossPoints(partner))
        }
        return list
    }

    private fun getTmpChild( part1 : List<CellChainConnection>,part2 : List<CellChainConnection>,part3 : List<CellChainConnection>,part4 : List<CellChainConnection>): Specimen{
        val childCellChainConnection = mutableListOf<CellChainConnection>()
        childCellChainConnection.addAll(part1)
        childCellChainConnection.addAll(part2)
        childCellChainConnection.addAll(part3)
        childCellChainConnection.addAll(part4)
        val tmp = mutableListOf<Int>()
        for (c in childCellChainConnection) {
            tmp.add(c.dataIndex)
        }
        return Specimen(tmp)
    }

    private fun getChildrenForRandomCrossPoints(partner: Specimen): List<Specimen> {
        val myParts = sliceMe(this.getRandomCrossPoints())
        val hisParts = partner.sliceMe(partner.getRandomCrossPoints())

        val tmpRet = mutableListOf<Specimen>()

        tmpRet.add(getTmpChild(myParts[0],myParts[1],myParts[2],hisParts[3]))
        tmpRet.add(getTmpChild(myParts[0],myParts[1],hisParts[2],myParts[3]))
        tmpRet.add(getTmpChild(myParts[0],myParts[1],hisParts[2],hisParts[3]))
        tmpRet.add(getTmpChild(myParts[0],hisParts[1],myParts[2],myParts[3]))
        tmpRet.add(getTmpChild(myParts[0],hisParts[1],myParts[2],hisParts[3]))
        tmpRet.add(getTmpChild(myParts[0],hisParts[1],hisParts[2],myParts[3]))
        tmpRet.add(getTmpChild(myParts[0],hisParts[1],hisParts[2],hisParts[3]))
        tmpRet.add(getTmpChild(hisParts[0],myParts[1],myParts[2],myParts[3]))
        tmpRet.add(getTmpChild(hisParts[0],myParts[1],myParts[2],hisParts[3]))
        tmpRet.add(getTmpChild(hisParts[0],myParts[1],hisParts[2],myParts[3]))
        tmpRet.add(getTmpChild(hisParts[0],myParts[1],hisParts[2],hisParts[3]))
        tmpRet.add(getTmpChild(hisParts[0],hisParts[1],myParts[2],myParts[3]))
        tmpRet.add(getTmpChild(hisParts[0],hisParts[1],myParts[2],hisParts[3]))
        tmpRet.add(getTmpChild(hisParts[0],hisParts[1],hisParts[2],myParts[3]))

        //delete duplications
        for (i in tmpRet) {
            i.deleteDuplications()
            i.mutate()
            i.recalculateCoverage()
        }
        return tmpRet
    }

    private fun deleteDuplications() {
        for (i in connections.indices) {
            var j = i + 1
            while (j < connections.size) {
                if (connections[i].isSame(connections[j]))
                    connections.removeAt(j)
                else
                    j += 1
            }
        }
    }

    fun sliceMe(list: List<Int>): MutableList<List<CellChainConnection>> {
        val slist = list.sorted()
        val tmpList = mutableListOf<List<CellChainConnection>>()

        tmpList.add(this.connections.slice(0..slist[0]))
        tmpList.add(this.connections.slice(slist[0] + 1..slist[1]))
        tmpList.add(this.connections.slice(slist[1] + 1..slist[2]))
        tmpList.add(this.connections.slice(slist[2] + 1 until connections.size))


        return tmpList
    }

    fun getRandomCrossPoints(): List<Int> {
        val tmpList = mutableListOf<Int>()
        var newPoint = Random.nextInt(0, connections.lastIndex())
        tmpList.add(newPoint)

        while (tmpList.size < 3) {
            newPoint = Random.nextInt(0, connections.lastIndex())
            if (!tmpList.contains(newPoint)) tmpList.add(newPoint)
        }
        return tmpList
    }

    fun getSpecimenDna(): String {
        var tmpDna = ""
        for (c in connections) {
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

    private fun recalculateCoverage() {
        for (i in 0..connections.size - 2) {
            connections[i].coverage =
                ExperimentData.cellChainList[connections[i].dataIndex].getBestCoverage(ExperimentData.cellChainList[connections[i + 1].dataIndex])
        }
        connections[connections.lastIndex()].coverage = null
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

        if (index != 0) connections[index - 1].recalculateCoverage(connections[index].cellChain)
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

        if (this.getSpecimenDna().length == ExperimentData.dnaLength)
            tmpCoverageSum += ExperimentConfiguration.sameLengthBonus

        if (this.getSpecimenDna().startsWith(ExperimentData.startString))
            tmpCoverageSum += ExperimentConfiguration.sameStartBonus

        return tmpCoverageSum
    }
}