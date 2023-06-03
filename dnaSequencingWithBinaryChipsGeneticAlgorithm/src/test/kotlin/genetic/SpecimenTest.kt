package genetic

import data.domain.Cell
import genetic.domain.CellChain
import genetic.domain.ExperimentData
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SpecimenTest {

    private val list = listOf<CellChain>(
        CellChain("ACA"),
        CellChain("ACGA"),
        CellChain("CAAT"),
        CellChain("CAGTT"),
        CellChain("TAC"),
        CellChain("TCA")
    )
    @BeforeEach
    fun setUp(){
        ExperimentData.cellChainList.clear()
        ExperimentData.cellChainList.addAll(list)
    }

    @Test
    fun getFitness() {
    }

    @Test
    fun getSpecimenDna() {
        val sp = Specimen(listOf(0,3,4,1))
        assertEquals("ACAGTTACGA", sp.getSpecimenDna())
    }

    @Test
    fun getSpecimenDna2() {
        val sp = Specimen(listOf(1,2,0,3))
        assertEquals("ACGACAATACAGTT", sp.getSpecimenDna())
    }
    @Test
    fun getSpecimenDna3() {
        val sp = Specimen(listOf(2,4,0,3,5))
        assertEquals("CAATACAGTTCA", sp.getSpecimenDna())
    }
    @Test
    fun getSpecimenDna4() {
        val sp = Specimen(listOf(3,4,0,2,5))
        assertEquals("CAGTTACAATCA", sp.getSpecimenDna())
    }
    @Test
    fun getSpecimenDna5() {
        val sp = Specimen(listOf(4,0,3,5,2))
        assertEquals("TACAGTTCAAT", sp.getSpecimenDna())
    }
    @Test
    fun getSpecimenDna6() {
        val sp = Specimen(listOf(5,3,4,1,0))
        assertEquals("TCAGTTACGACA", sp.getSpecimenDna())
    }


    @Test
    fun mutate(){
        val sp = Specimen(listOf(5,3,4,1,0))
        sp.mutate()
        sp.mutate()
        sp.mutate()
        sp.mutate()
        sp.mutate()
        sp.mutate()
        sp.getSpecimenDna()
    }

    @Test
    fun sliceMe() {
        val sp = Specimen(listOf(5,3,4,1,0))
        val list = listOf(0,1,3)
        val slices = sp.sliceMe(list)
        val slices2= slices
    }
}