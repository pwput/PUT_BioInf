package genetic

import genetic.domain.CellChain
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CellChainConnectionTest {

    private val ccc1 = CellChainConnection(CellChain("ASDFG"), 2)
    private val ccc2 = CellChainConnection(CellChain("ASDFG"), null)
    private val ccc3 = CellChainConnection(CellChain("ASDFG"), 0)

    @Test
    fun getUncoveregedText1() {
        assertEquals("ASD",ccc1.getUncoveregedText())
    }

    @Test
    fun getUncoveregedText2() {
        assertEquals("ASDFG",ccc2.getUncoveregedText())
    }

    @Test
    fun getUncoveregedText3() {
        assertEquals("ASDFG",ccc3.getUncoveregedText())
    }
}