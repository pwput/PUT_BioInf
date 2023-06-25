package data

import data.domain.Cell
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class DnaDataParserTest {

    val data = DnaData()

    @BeforeEach
    fun setup(){
        val swProbe = Probe()
        swProbe.pattern = "ZZZ"
        swProbe.cellList = listOf(
            Cell("SSA"),
            Cell("SST"),
            Cell("SWA"),
            Cell("SWC"),
            Cell("WSG"),
            Cell("WWC"),
        )

        val ppProbe = Probe()
        ppProbe.pattern = "PPP"
        ppProbe.cellList = listOf(
            Cell("RRC"),
            Cell("RYA"),
            Cell("RYG"),
            Cell("YRA"),
            Cell("YRC"),
            Cell("YRT"),
            Cell("YYG"),
        )

        data.start = "TCG"
        data.probeList = listOf(swProbe,ppProbe)
    }
    @Test
    fun parse() {
        val ret = DnaDataParser.parse(data)
    }
}