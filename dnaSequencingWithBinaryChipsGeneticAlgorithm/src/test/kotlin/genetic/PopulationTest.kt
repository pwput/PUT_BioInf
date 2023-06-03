package genetic

import genetic.domain.CellChain
import genetic.domain.ExperimentData
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PopulationTest {

    private val list = listOf<CellChain>(
        CellChain("ACA"),
        CellChain("ACGA"),
        CellChain("CAAT"),
        CellChain("CAGTT"),
        CellChain("TAC"),
        CellChain("TCA")
    )
    @Test
    fun init() {
        Experiment(list,10)
        val pop = Population(list,10)
    val pop2 = pop
    pop.runGeneration()

    }
}