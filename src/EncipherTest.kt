import org.junit.*
import kotlin.test.assertEquals

class EncipherTest {
    lateinit var enciphering: Caesar
    @Before
    fun initial() {
       enciphering = Caesar()
    }

    @Test
    fun testShift() {
        val result = enciphering.shiftFunction('a', 1)
        assertEquals('b', result)
    }

    @Test
    fun testShiftWrapAround() {
        val result = enciphering.shiftFunction('z', 1)
        assertEquals('a', result)

    }

    @Test
    fun testShiftUpper() {
        val result = enciphering.shiftFunction('A', 1)
        assertEquals('B', result)
    }

    @Test
    fun testShiftWrapAroundUpper() {
        val result = enciphering.shiftFunction('Z', 1)
        assertEquals('A', result)

    }

    @Test (expected = IllegalArgumentException::class)
    fun throwsExceptionforIllegalArgumentOffsetLimitHigh() {
        enciphering.encipher("abc", 26)
    }


    @Test (expected = IllegalArgumentException::class)
    fun throwsExceptionforIllegalArgumentOffsetLimitLow() {
        enciphering.encipher("abc", -3)
    }

    @Test
    fun numberIgnore() {
        val result = enciphering.shiftFunction('1', 21)
        assertEquals('1', result)
    }

    @Test
    fun punctuationIgnore() {
        val result = enciphering.shiftFunction('!', 21)
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
    fun offsetMixedCasesAndSymbolsWrap() {
        val expected = "Bzdrzq bhogdq? H oqdedq Bzdrzq rzkzc."
        val result = enciphering.encipher("Caesar cipher? I prefer Caesar salad.", 25)
        assertEquals(expected, result)
    }

}