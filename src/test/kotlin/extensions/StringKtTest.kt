package extensions

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class StringKtTest {


    val str1 = "asdgdsfagadfg"
    val str2 = "aa"

    @Test
    fun lastChars() {
        assertEquals("fg",str1.lastChars(2))
        assertEquals("aa", str2.lastChars(3))
    }
}