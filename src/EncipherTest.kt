import org.junit.*
import kotlin.test.assertEquals

class EncipherTest {
    lateinit var enciphering: Caeser
    @Before
    fun initial() {
       enciphering = Caeser()
    }

    @Test
    fun testHelper() {
        val result = enciphering.helperFunction('a', 1);
        assertEquals('b', result)

    }
    @Test
    fun testHelperWrapAround() {
        val result = enciphering.helperFunction('z', 1);
        assertEquals('a', result)

    }

    @Test
    fun testHelperUpper() {
        val result = enciphering.helperFunction('A', 1);
        assertEquals('B', result)
    }

    @Test
    fun testHelperWrapAroundUpper() {
        val result = enciphering.helperFunction('Z', 1);
        assertEquals('A', result)

    }

    @Test (expected = IllegalArgumentException::class)
    fun throwsExceptionforIllegalArgumentOffsetLimit() {
        enciphering.encipher("abc", 26)
    }

    @Test
    fun numberIgnore() {
        var result = enciphering.helperFunction('1', 21)
        assertEquals('1', result)
    }

    @Test
    fun punctuationIgnore() {
        var result = enciphering.helperFunction('!', 21)
        assertEquals('!', result)
    }

    @Test
    fun mixedStringIgnore() {
        var result = enciphering.encipher("1 ab", 1)
        var expected = "1 bc"
        assertEquals(expected, result)
    }

    @Test
    fun offsetsString() {
        var expected = "bcd"
        var result = enciphering.encipher("abc", 1)
        assertEquals(expected, result)
    }

    @Test
    fun recursiveBaseCase() {
        var expected = "b"
        var result = enciphering.encipher("a", 1)
        assertEquals(expected, result)
    }

    @Test
    fun offsetsStringUpperCase() {
        var expected = "BCD"
        var result = enciphering.encipher("ABC", 1)
        assertEquals(expected, result)
    }

    @Test
    fun recursiveBaseCaseUpperCase() {
        var expected = "B"
        var result = enciphering.encipher("A", 1)
        assertEquals(expected, result)
    }

    @Test
    fun offsetsStringUpperCaseWrap() {
        var expected = "YZA"
        var result = enciphering.encipher("XYZ", 1)
        assertEquals(expected, result)
    }

    @Test
    fun recursiveBaseCaseUpperCaseWrap() {
        var expected = "B"
        var result = enciphering.encipher("Z", 2)
        assertEquals(expected, result)
    }

    @Test
    fun offsetsStringMixedUpperLower() {
        var expected = "bBcC"
        var result = enciphering.encipher("aAbB", 1)
        assertEquals(expected, result)
    }

    @Test
    fun offsetMixedUpperLowerWrap() {
        var expected = "zZaA"
        var result = enciphering.encipher("yYzZ", 1)
        assertEquals(expected, result)
    }

    @Test
    fun offsetMixedCasesandSymbolsWrap() {
        var expected = "Bzdrzq bhogdq? H oqdedq Bzdrzq rzkzc."
        var result = enciphering.encipher("Caesar cipher? I prefer Caesar salad.", 25)
        assertEquals(expected, result)
    }

}