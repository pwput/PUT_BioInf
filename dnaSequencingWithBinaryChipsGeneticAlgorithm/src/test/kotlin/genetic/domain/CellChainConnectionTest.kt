package genetic.domain

import genetic.CellChainConnection
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CellChainConnectionTest {

    private val ccc = CellChainConnection(0,CellChain("AAAAAD"), 2)
    private val cccl = CellChainConnection(1,CellChain("AAAAAD"), null)

    @Test
    fun getCoverageWithChainToRight1() {
        assertEquals(2,ccc.coverage)
    }
    @Test
    fun getCoverageWithChainToRight2() {
        assertEquals(null,cccl.coverage)
    }
}