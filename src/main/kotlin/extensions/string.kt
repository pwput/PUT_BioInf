package extensions

fun String.withoutLast() = this.slice(0 until this.lastIndex)
fun String.withoutFirst() = this.slice(1 .. this.lastIndex)

fun String.lastChar() = this[lastIndex]

fun String.lastChars(count: Int):String{
    return if (this.length< count)
        this
    else
        this.substring(this.length -count)
}