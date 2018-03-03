import org.junit.*
import kotlin.test.assertEquals

class EncipherTest {
    lateinit var enciphering: Caesar
    @Before
    fun initial() {
       enciphering = Caesar()
    }

    @Test
    fun testHelper() {
        val result = enciphering.helperFunction('a', 1)
        assertEquals('b', result)

    }
    @Test
    fun testHelperWrapAround() {
        val result = enciphering.helperFunction('z', 1)
        assertEquals('a', result)

    }

    @Test
    fun testHelperUpper() {
        val result = enciphering.helperFunction('A', 1)
        assertEquals('B', result)
    }

    @Test
    fun testHelperWrapAroundUpper() {
        val result = enciphering.helperFunction('Z', 1)
        assertEquals('A', result)

    }

    @Test (expected = IllegalArgumentException::class)
    fun throwsExceptionforIllegalArgumentOffsetLimit() {
        enciphering.encipher("abc", 26)
    }

    @Test
    fun numberIgnore() {
        val result = enciphering.helperFunction('1', 21)
        assertEquals('1', result)
    }

    @Test
    fun punctuationIgnore() {
        val result = enciphering.helperFunction('!', 21)
        assertEquals('!', result)
    }

    @Test
    fun mixedStringIgnore() {
        val result = enciphering.encipher("1 ab", 1)
        val expected = "1 bc"
        assertEquals(expected, result)
    }

    @Test
    fun offSetZero() {
        val result = enciphering.encipher("abc", 0)
        val expected = "abc"
        assertEquals(expected, result)
    }

    @Test
    fun offsetsString() {
        val expected = "bcd"
        val result = enciphering.encipher("abc", 1)
        assertEquals(expected, result)
    }

    @Test
    fun recursiveBaseCase() {
        val expected = "b"
        val result = enciphering.encipher("a", 1)
        assertEquals(expected, result)
    }

    @Test
    fun offsetsStringUpperCase() {
        val expected = "BCD"
        val result = enciphering.encipher("ABC", 1)
        assertEquals(expected, result)
    }

    @Test
    fun recursiveBaseCaseUpperCase() {
        val expected = "B"
        val result = enciphering.encipher("A", 1)
        assertEquals(expected, result)
    }

    @Test
    fun offsetsStringUpperCaseWrap() {
        val expected = "YZA"
        val result = enciphering.encipher("XYZ", 1)
        assertEquals(expected, result)
    }

    @Test
    fun recursiveBaseCaseUpperCaseWrap() {
        val expected = "B"
        val result = enciphering.encipher("Z", 2)
        assertEquals(expected, result)
    }

    @Test
    fun offsetsStringMixedUpperLower() {
        val expected = "bBcC"
        val result = enciphering.encipher("aAbB", 1)
        assertEquals(expected, result)
    }

    @Test
    fun offsetMixedUpperLowerWrap() {
        val expected = "zZaA"
        val result = enciphering.encipher("yYzZ", 1)
        assertEquals(expected, result)
    }

    @Test
    fun offsetMixedCasesandSymbolsWrap() {
        val expected = "Bzdrzq bhogdq? H oqdedq Bzdrzq rzkzc."
        val result = enciphering.encipher("Caesar cipher? I prefer Caesar salad.", 25)
        assertEquals(expected, result)
    }

}