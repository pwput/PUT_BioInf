package data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import data.domain.Cell

@JacksonXmlRootElement(localName = "probe")
class Probe {
    @field:JacksonXmlProperty(localName = "pattern")
    lateinit var pattern: String

    @field:JacksonXmlProperty(localName = "cell")
    lateinit var cellList: List<Cell>
}