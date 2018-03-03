import org.junit.*
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.test.expect

class DecipherTest {
    lateinit var deciphering: Caeser
    @Before
    fun initial() {
       deciphering = Caeser()
    }

    @Test
    fun singleWord() {
        val expected = "telescope"
        val input = deciphering.decipher("ufmftdpqf")
        assertEquals(expected, input)
    }

    @Test
    fun testCleanString() {
        val expected = "thisisconfusing"
        assertEquals(expected, deciphering.cleanString("this is confusing!"))
    }

    @Test
    fun testFindLikeliestE() {
        var input = "eeeeehhhhfff"
        var result = deciphering.findLikeliestE(input)
        assertEquals('e', result)
    }

    @Test
    fun testEFrequencyCipheredUp() {
        var result = deciphering.decipher("Vjg gpf qh vjg yqtnf")
        assertEquals("The end of the world", result)
    }

    @Test
    fun testEFrequencyCipheredDown() {
        var result = deciphering.decipher("Pda ajz kb pda sknhz")
        assertEquals("The end of the world", result)
    }

    @Test
    fun testReadingDictionaryFile() {
        val dictionary = deciphering.readDictionary();
        assertNotNull(dictionary)
        assertTrue(dictionary.size == 100)
        assertTrue(dictionary.contains("the"))

    }



}