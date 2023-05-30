package data

import data.domain.Oligonucleotide
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CharChainTest {

    private val oli1 = Oligonucleotide("AAAAAD")
    private val oli2 = Oligonucleotide("ABCDEF")
    private val oli3 = Oligonucleotide("BCDEFG")
    private val oli4 = Oligonucleotide("DDDDDD")
    private val oli5 = Oligonucleotide("DD")

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