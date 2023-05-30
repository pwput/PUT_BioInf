package genetic.domain

import genetic.CellChainConnection
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CellChainConnectionTest {

    private val ccc = CellChainConnection(CellChain("AAAAAD"), 2)
    private val cccl = CellChainConnection(CellChain("AAAAAD"), null)

    @Test
    fun getCoverageWithChainToRight1() {
        assertEquals(4,ccc.getCoverageWithChainToRight())
    }
    @Test
    fun getCoverageWithChainToRight2() {
        assertEquals(null,cccl.getCoverageWithChainToRight())
    }
}