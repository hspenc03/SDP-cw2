import org.junit.*
import kotlin.test.assertEquals

class EncipherTest {
    lateinit var enciphering: practice
    @Before
    fun intiial() {
       enciphering = practice()
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



}