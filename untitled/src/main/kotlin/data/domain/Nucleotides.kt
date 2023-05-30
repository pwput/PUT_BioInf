package data.domain

enum class NucleotideNames{


}


sealed interface Nucleotide

enum class Nucleotides(val char: Char): Nucleotide {
    Andenice('A'),
    Guanine('G'),
    Cytosine('C'),
    Tymine('T')
}



sealed class BinaryNucleotide
sealed class StrengthBasedBinaryNucleotide(val char: Char): BinaryNucleotide()
sealed class Weak(): StrengthBasedBinaryNucleotide('W')
sealed class Strong(): StrengthBasedBinaryNucleotide('S')

sealed class NucleobaseBasedBinaryNucleotide(val char: Char): BinaryNucleotide()
sealed class Pyrimidine(): NucleobaseBasedBinaryNucleotide('P')
sealed class Purine(): NucleobaseBasedBinaryNucleotide('R')
