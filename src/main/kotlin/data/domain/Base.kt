package data.domain

internal interface IBase{
    fun toPP():Char
    fun toSW():Char
}

enum class Base (char: Char):IBase{
    Adenine('A') {
        override fun toPP() = 'R'
        override fun toSW() = 'W'
    },
    Guanine('G') {
        override fun toPP() = 'R'
        override fun toSW() = 'S'
    },
    Cytosine('C') {
        override fun toPP() = 'Y'
        override fun toSW() = 'S'
    },
    Thymidine('T') {
        override fun toPP() = 'Y'
        override fun toSW() = 'W'
    },
}

