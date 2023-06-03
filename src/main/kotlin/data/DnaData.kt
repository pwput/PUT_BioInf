package data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "dna")
class DnaData {
    @field:JacksonXmlProperty(localName = "key")
    var key: String? = null

    @field:JacksonXmlProperty(localName = "length")
    var length: String? = null

    @field:JacksonXmlProperty(localName = "start")
    var start: String? = null

    @field:JacksonXmlProperty(localName = "probe")
    lateinit var probeList: List<Probe>
}