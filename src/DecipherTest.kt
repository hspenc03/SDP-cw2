import org.junit.*
import kotlin.test.assertEquals

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


}