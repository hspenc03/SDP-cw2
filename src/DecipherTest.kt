import org.junit.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import java.util.Random


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

    @Test
    fun testRealExamplesLong() {
        val rand = Random()
        for (line in longList) {
            val input = deciphering.encipher(line, rand.nextInt(26))
            val result = deciphering.decipher(input)
            assertEquals(line, result)
        }
    }

    @Test
    fun testRealExamplesMid() {
        val rand = Random()
        for (line in midList) {
            val input = deciphering.encipher(line, rand.nextInt(26))
            val result = deciphering.decipher(input)
            assertEquals(line, result)
        }
    }

    @Test
    fun testRealExamplesShort() {
        val rand = Random()
        for (line in shortList) {
            val input = deciphering.encipher(line, rand.nextInt(26))
            val result = deciphering.decipher(input)
            assertEquals(line, result)
        }
    }

    private val longList = listOf("A certain king had a beautiful garden, and in the garden stood a tree which bore " +
            "golden apples", "These apples were always counted, and about the time when they began to grow ripe it " +
            "was found that every night one of them was gone", "The king became very angry at this, and ordered the " +
            "gardener to keep watch all night under the tree", "The gardener set his eldest son to watch; but about " +
            "twelve o’clock he fell asleep, and in the morning another of the apples was missing", "Then the second " +
            "son was ordered to watch; and at midnight he too fell asleep, and in the morning another apple was gone",
            "Then the third son offered to keep watch; but the gardener at first would not let him, for fear some " +
                    "harm should come to him: however, at last he consented, and the young man laid himself under the " +
                    "tree to watch.", "As the clock struck twelve he heard a rustling noise in the air, and a " +
            "bird came flying that was of pure gold; and as it was snapping at one of the apples with its beak, " +
            "the gardener’s son jumped up and shot an arrow at it.", "But the arrow did the bird no harm; only it " +
            "dropped a golden feather from its tail, and then flew away.", "The golden feather was brought to the " +
            "king in the morning, and all the council was called together", "Everyone agreed that it was worth " +
            "more than all the wealth of the kingdom: but the king said, ‘One feather is of no use to me, I must have " +
            "the whole bird’.")

    private val midList = listOf("The trouble with geraniums is that they’re much too red!",
            "The trouble with my toast is that it’s far too full of bread.",
            "The trouble with a diamond is that it’s much too bright.",
            "The same applies to fish and stars and the electric light.",
            "The troubles with the stars I see lies in the way they fly.",
            "The trouble with myself is all self-centred in the eye.",
            "The trouble with my looking-glass is that it shows me, me;",
            "there’s trouble in all sorts of things where it should never be.")

    private val shortList = listOf("I cannot give the reasons,", "I only sing the tunes:", "the sadness of the seasons",
            "the madness of the moons.", "I cannot be didactic", "or lucid, but I can",
            "be quite obscure and practically marzipan", "In gorgery and gushness", "and all that's squishified.",
            "My voice has all the lushness", "of what I can't abide", "And yet it has a beauty",
            "most proud and terrible", "denied to those whose duty", "is to be cerebral.",
            "Among the antlered mountains", "I make my viscous way", "and watch the sepia mountains",
            "throw up their lime-green spray.")

}