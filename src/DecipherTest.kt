import org.junit.*
import kotlin.test.assertEquals
import kotlin.test.expect

class DecipherTest {
    lateinit var deciphering: Caeser
    @Before
    fun initial() {
       deciphering = Caeser()
    }

    @Test
    fun singleWord() {
        val expected = "cat"
        val input = deciphering.decipher("dbu")
        assertEquals(expected, input)
    }

    @Test
    fun testCleanString() {
        val expected = "thisisconfusing"
        assertEquals(expected, deciphering.cleanString("this is confusing!"))
    }

    @Test
    fun testGroupingCounter() {
        val word = "butter"
        val expected = mapOf('b' to 1, 'u' to 1, 't' to 2, 'e' to 1, 'r' to 1)
        assertEquals(expected, deciphering.groupingFrequency(word))
    }

    @Test
    fun testFindOffset() {
        val word = "eeeeeeeeettttaaa"
        val expected = 9
        assertEquals(expected, deciphering.findOffset(word))
    }


}