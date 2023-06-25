package genetic.domain

import data.domain.ACharChain
import extensions.lastChar

class CellChain(text: String) : ACharChain(text) {
}

sealed class ABinaryCellChain(text: String):ACharChain(text){
    abstract fun matches(other: ACharChain):Boolean
}

class PPCellChain(text: String): ABinaryCellChain(text) {
    override fun matches(other: ACharChain): Boolean{
        if (other.text.lastChar() != this.text.lastChar()) return false
        if (other.length() != this.length()) return false
        if (other !is SWCellChain) return false
        for (i in 0 until this.text.lastIndex){
            if(this.text[i] == 'R' && other.text[i] != 'W') return false
            if(this.text[i] == 'Y' && other.text[i] != 'S') return false
        }
        return true
    }
}

class SWCellChain(text: String): ABinaryCellChain(text) {
    override fun matches(other: ACharChain):  Boolean{
        if (other.text.lastChar() != this.text.lastChar()) return false
        if (other.length() != this.length()) return false
        if (other !is PPCellChain) return false
        for (i in 0 until this.text.lastIndex){
            if(this.text[i] == 'W' && other.text[i] != 'R') return false
            if(this.text[i] == 'S' && other.text[i] != 'Y') return false
        }
        return true
    }
}