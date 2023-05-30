package data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import data.domain.ACharChain
import data.domain.Oligonucleotide

@JacksonXmlRootElement(localName = "probe")
class Probe {
    @field:JacksonXmlProperty(localName = "pattern")
    var pattern: String? = null

    @field:JacksonXmlProperty(localName = "cell")
    lateinit var cellList: List<Oligonucleotide>
}