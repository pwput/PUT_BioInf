import data.domain.ACharChain
import data.XmlParser

fun main(args: Array<String>) {
    println("Hello World!")

    val parser = XmlParser()

    val dna = parser.parse()

    println(dna.toString())


    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}