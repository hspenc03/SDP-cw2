import kotlin.math.absoluteValue

class Caeser {

    data class Item(val letter: Char, val frequency: Int)

    fun encipher(s: String, n: Int): String {
        var cipheredString = ""

        //checks valid input for n
        if(n < 0 || n > 25) {
            throw IllegalArgumentException()
        }
        var charArray = s.toCharArray()

        // Base case - return if last character in input string
        if (s.length == 1) {
            return helperFunction(charArray[0], n).toString()
        } else {
            //builds string with changed chars
            cipheredString += helperFunction(charArray[0], n)
        }
        //recursive string build with unchanged characters
        cipheredString += encipher(s.substring(1), n)
        return cipheredString
    }

    fun helperFunction(c: Char, n: Int): Char {
        var limit = 0
        var result : Char
        if(c.isLetter()) {
            result = c + n
            limit = when (c.isLowerCase()) {
                true -> 122
                false -> 90
            }
            if (result.toInt() > limit) result -= 26

        } else {
            result = c
        }
        return result
    }

    fun decipher(s: String): String {
        val cleanString = cleanString(s)
        val commonestLetter = findLikeliestE(cleanString)
        val offset = 101 - commonestLetter!!.toInt()
        return when {
            offset == 0 -> s
            offset < 0 -> encipher(s, offset + 26)
            else -> encipher(s, offset)
        }

    }

    fun findOffset(s: String): Int {
//make 26 different enciphers and compare which have most e's etc
        var e = "e"
        var ascii = e.toInt()

        //var key =
        return 1
        //return(mostFrequent)
    }

    fun cleanString(phrase: String):String {
        var returnString = ""
        phrase.toLowerCase().toCharArray()
                .filter { it.isLetter() }
                .forEach { returnString += it }
        return returnString
    }

    // Returns char value for most frequent letter - add null check?
    fun findLikeliestE(word: String): Char? {
        val words = word.split("")
        val resultMap = word.groupingBy { it }.eachCount()
        return resultMap.maxBy { it.value }?.key
    }

}