package data.domain

abstract class ACharChain(val text: String) {

    fun length() = this.text.length

    fun hasBestCoverage(top: ACharChain):Boolean{
        return getBestCoverage(top) == this.text.length - 1
    }
    fun getBestCoverage(top: ACharChain): Int {
        var coverage = top.text.length

        var skip = if (this.text.length >= top.text.length)
            this.text.length - top.text.length + 1
        else
            1

        while (skip < this.text.length) {
            val bot = this.text.slice(IntRange(skip, this.text.length - 1))

            var isCoverage = true

            for (i in bot.indices) {
                if (bot[i] != top.text[i]) {
                    isCoverage = false
                    break
                }
            }

            if (isCoverage)
                return bot.length

            skip += 1

        }

        return 0
    }
}

