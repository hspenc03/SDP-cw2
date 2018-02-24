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



}