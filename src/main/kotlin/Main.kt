import data.DnaDataParser
import data.domain.ACharChain
import data.XmlParser
import genetic.Experiment

fun main(args: Array<String>) {
    println("Hello World!")

    val parser = XmlParser()

    val dna = parser.parse()

    val chainlist = DnaDataParser.parse(dna)

    chainlist.forEachIndexed { index, cellChain -> println(cellChain.text) }

    val experiment = Experiment(chainlist)

    experiment.start(5000)
}