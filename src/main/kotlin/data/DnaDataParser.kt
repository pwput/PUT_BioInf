package data

import data.domain.ACharChain
import data.domain.Cell
import extensions.lastChar
import extensions.lastChars
import extensions.withoutFirst
import extensions.withoutLast
import genetic.domain.CellChain
import genetic.domain.ExperimentData
import java.lang.Exception

object DnaDataParser {

    private val remainingSW = mutableListOf<Cell>()
    private val remainingPP = mutableListOf<Cell>()

    private var search = Cell("SSS")
    fun parse(data: DnaData):List<CellChain>{
        var previousChain = ""
        val cellLength = data.start!!.length
        val tmpList = mutableListOf<CellChain>()

        ExperimentData.startString = data.start!!
        ExperimentData.dnaLength = data.length!!.toInt()

        remainingPP.addAll(data.probeList.first { it.pattern[0] == 'P' }.cellList)
        remainingSW.addAll(data.probeList.first { it.pattern[0] == 'Z' }.cellList)

        var nextChain = ExperimentData.startString

        //println("Start: ${ExperimentData.startString}")

        while (remainingSW.isNotEmpty() && remainingPP.isNotEmpty()){

            search = Cell(nextChain.lastChars(cellLength))
            var newChar = getNextCellChainChar()
            while (newChar != null){
                nextChain += newChar
                search = Cell(nextChain.lastChars(cellLength- 1))
               // println("Next search: ${search.text}")
                newChar = getNextCellChainChar()
            }
            //search = Cell(search.text.withoutFirst())
            if(!previousChain.endsWith(nextChain)){
                //println("Next chain: $nextChain")
                tmpList.add(CellChain(nextChain))
            }
            previousChain = nextChain
            nextChain = search.text.withoutFirst()
        }


        println("#### Chainy: ${tmpList.size}####")
        //tmpList.forEachIndexed { _, cellChain -> println(cellChain.text) }
        return tmpList
    }

    private fun getNextCellChainChar(coverageOff: Int = 0): Char
    ?{
        var tmpRet : Char

        var temSW = getStrongWeak(search)
        var temPP = getPurinePyrimidinesChain(search)

        val possibleSW = remainingSW.filter { temSW.binaryMatch(it) }
        val possiblePP = remainingPP.filter { temPP.binaryMatch(it)  }

        if (possibleSW.isEmpty() && possiblePP.isEmpty()) //Nie ma więcej
            return null
        if (possibleSW.isEmpty()) {
            tmpRet = possiblePP[0].text.lastChar()
            remainingPP.remove(possiblePP[0])
            return tmpRet
        }
        if (possiblePP.isEmpty()){
            tmpRet = possibleSW[0].text.lastChar()
            remainingSW.remove(possibleSW[0])
            return tmpRet
        }

        var todelpp : ACharChain? = null
        var todelsw : ACharChain? = null

        for (pp in possiblePP){
            todelsw = possibleSW.find { it.text.lastChar() == pp.text.lastChar() }
                if ( todelsw != null) {
                    todelpp = pp
                    break
                }
            }

        todelpp?.let {
            tmpRet = it.text.lastChar()
            remainingPP.remove(todelpp)
            remainingSW.remove(todelsw)
        }

        tmpRet = possiblePP[0].text.lastChar()
        remainingPP.remove(possiblePP[0])
        return tmpRet

    }



    private fun getStrongWeak(oli: ACharChain):ACharChain{
        var temSW = ""
        for (char in oli.text) {
            when (char) {
                'C' -> {
                    temSW += 'S'
                }
                'G' -> {
                    temSW += 'S'
                }
                'A' -> {
                    temSW += 'W'
                }
                'T' -> {
                    temSW += 'W'
                }
            }
        }
        return CellChain(temSW)
    }

    private fun getPurinePyrimidinesChain(oli: ACharChain):ACharChain{
        var temPP = ""
        for (char in oli.text) {
            temPP += when (char) {
                'C' -> {
                    'Y'
                }
                'G' -> {
                    'R'
                }
                'A' -> {
                    'R'
                }
                'T' -> {
                    'Y'
                }
                else ->throw Exception("Wrong data! Char: \'$char\' not expected")
            }
        }
        return CellChain(temPP)
    }
}