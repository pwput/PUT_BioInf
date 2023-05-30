package data

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import java.io.File

class XmlParser {

    val dnaToParse = "<dna key=\"27405394\" length=\"500\" start=\"TAAATATTGTTGGGAAAAC\">\n" +
            "<probe pattern=\"ZZZZZZZZZZZZZZZZZZN\">\n" +
            "<cell>SSSSSSSSSWSWWSSWSWG</cell>\n" +
            "<cell>SSSSSSSSWSWWSSWSWST</cell>\n" +
            "<cell>SSSSSSSWSWWSSWSWSWC</cell>\n" +
            "<cell>SSSSSSWSWWSSWSWSWST</cell>\n" +
            "</probe>\n" +
            "<probe pattern=\"PPPPPPPPPPPPPPPPPPN\">\n" +
            "<cell>YYYYYYYYYYYYYRRRRYG</cell>\n" +
            "<cell>YYYYYYYYYYYYYYRRRRC</cell>\n" +
            "<cell>YYYYYYYYYYYYYYYRRRA</cell>\n" +
            "<cell>YYYYYYYYYYYYYYYYRRG</cell>\n" +
            "</probe>\n" +
            "</dna>"

    val file = File("bio.xml")

    val xmlMapper = XmlMapper(
        JacksonXmlModule().apply { setDefaultUseWrapper(false) }
    ).apply {
        enable(SerializationFeature.INDENT_OUTPUT)
        enable(SerializationFeature.WRAP_ROOT_VALUE)
    }

    fun parse(): DnaData{
        val xml = xmlMapper.readValue(file,DnaData::class.java)
        return xml
    }
}