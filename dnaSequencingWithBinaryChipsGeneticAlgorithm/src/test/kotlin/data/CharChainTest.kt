package data

import data.domain.Cell
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CharChainTest {

    private val oli1 = Cell("AAAAAD")
    private val oli2 = Cell("ABCDEF")
    private val oli3 = Cell("BCDEFG")
    private val oli4 = Cell("DDDDDD")
    private val oli5 = Cell("DD")

    @Test
    fun hasBestCoverage1() {
        assertEquals(oli2.hasBestCoverage(oli3),true)
    }
    @Test
    fun hasBestCoverage2() {
        assertEquals(oli1.hasBestCoverage(oli4),false)
    }

    @Test
    fun getCoverage5() {
        assertEquals(oli4.getBestCoverage(oli5),1)
    }

    @Test
    fun getCoverage6() {
        assertEquals(oli5.getBestCoverage(oli4),1)
    }


    @Test
    fun getCoverage() {
        assertEquals(oli2.getBestCoverage(oli3),5)
    }

    @Test
    fun getCoverage2() {
        assertEquals(oli1.getBestCoverage(oli4),1)
    }

    @Test
    fun getCoverage3() {
        assertEquals(oli1.getBestCoverage(oli2),0)
    }
}