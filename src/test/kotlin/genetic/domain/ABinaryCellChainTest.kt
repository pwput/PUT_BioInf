package genetic.domain

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ABinaryCellChainTest {

    val pp1 = PPCellChain("RRC")
    val sw1 = SWCellChain("SSC")
    val sw2 = PPCellChain("SSSSWWWWWSWSSSWWWWT")
    val pp2 = PPCellChain("RRRRYYYYYRYRRRYYYT")
    val pp3 = PPCellChain("RRC")
    val sw3 = SWCellChain("SSA")
    @Test
    fun matches() {
        assertEquals(true, pp1.matches(sw1))
        assertEquals(true, pp2.matches(sw2))
        assertEquals(false, pp3.matches(sw3))
    }
}