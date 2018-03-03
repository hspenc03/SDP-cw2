import org.junit.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class DecipherTest {
    lateinit var deciphering: Caesar
    @Before
    fun initial() {
       deciphering = Caesar()
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
        val input = "eeeeehhhhfff"
        val result = deciphering.findLikeliestE(input)
        assertEquals('e', result)
    }

    @Test
    fun testEFrequencyCipheredUp() {
        val result = deciphering.decipher("Vjg gpf qh vjg yqtnf")
        assertEquals("The end of the world", result)
    }

    @Test
    fun testEFrequencyCipheredDown() {
        val result = deciphering.decipher("Pda ajz kb pda sknhz")
        assertEquals("The end of the world", result)
    }

    @Test
    fun testReadingDictionaryFile() {
        val dictionary = deciphering.readDictionary()
        assertNotNull(dictionary)
        assertTrue(dictionary.size == 100)
        assertTrue(dictionary.contains("the"))
    }

    @Test
    fun testReturnEncipheredWord() {
        val inputString = "this is a test string"
        val result = deciphering.decipher(inputString)
        assertEquals("this is a test string", result)
    }

    @Test
    fun testDictionaryCheck() {
        deciphering.dictionary = deciphering.readDictionary()
        val input = mutableListOf("time", "to", "use", "people")
        val expected = 4
        val result = deciphering.checkAgainstDictionary(input)
        assertEquals(expected, result)
    }

    @Test
    fun testDecipher() {
        val expected = "time to use people"
        val input = deciphering.encipher(expected, 1)
        val result = deciphering.decipher(input)
        assertEquals(expected, result)
    }

    @Test
    fun testDecipherMixedCase() {
        val expected = "Time to use People"
        val input = deciphering.encipher(expected, 1)
        val result = deciphering.decipher(input)
        assertEquals(expected, result)
    }

    @Test
    fun testDecipherMixedTypes() {
        val expected = "Time to use 5 People!?"
        val input = deciphering.encipher(expected, 1)
        val result = deciphering.decipher(input)
        assertEquals(expected, result)
    }

}