import java.io.File
import java.io.IOException
import java.io.InputStream

class Caesar {
    var dictionary : Set<String> = readDictionary()


    /**
     * Reads in a dictionary of the commonest 1000 words in the English language.
     * Dictionary taken from: {@link https://www.ef.co.uk/english-resources/english-vocabulary/top-1000-words/}
     * @return a mutuable set of {@link String}
     */
    fun readDictionary() : MutableSet<String> {
        val wordSet: MutableSet<String> = mutableSetOf()
        try {
            val inputStream: InputStream = File("src/dictionary.txt").inputStream()
            inputStream.bufferedReader().useLines { word -> word.forEach { wordSet.add(it) } }
        } catch (e: IOException) {
            println("File not read properly!")
            e.printStackTrace()
        }
        return wordSet
    }

    /**
     * Enciphers a word using a Caesar cipher (shifts characters along alphabet by a given number)
     * @param s {@link String}
     * @param offset {@link Int}
     * @return the input string shifted by the offset value {@link String}
     */
    fun encipher(s: String, offset: Int): String {
        var cipheredString = ""
        // Input validation
        if (offset < 0 || offset > 25) {
            throw IllegalArgumentException()
        }
        // Base case - return if last character in input string
        if (s.length == 1) {
            return shiftFunction(s[0], offset).toString()
        } else {
            // Builds string with changed chars
            cipheredString += shiftFunction(s[0], offset)
        }

        // Recursive string build with unchanged characters
        cipheredString += encipher(s.substring(1), offset)
        return cipheredString
    }

    /**
     * Shifts a character along alphabet by a given number
     * @param c {@link Char}
     * @param n {@link Int}
     * @return individual character that has been shifted by offset {@link Char}
     */
    fun shiftFunction(c: Char, n: Int): Char {
        var result : Char = c
        if (c.isLetter()) {
            val limit = when (c.isLowerCase()) {
                true -> 122
                false -> 90
            }
            result += n
            if (result.toInt() > limit) result -= 26
        }
        return result
    }

    // Decipher encoded string using frequency of common words and characters
    /**
     * Takes a ciphered {@link String} and returns the most likely
     * deciphered based on top 1000 words and character frequency
     * @param str {@link String}
     * @return deciphered {@link String}
     */
    fun decipher(str: String): String {
        val map : MutableMap<String, Int> = mutableMapOf()

        // Decipher using every possible key and then check results against common word dictionary
        for (i in listPossibleEncodings(str)) {
            var resultString = ""
            i.forEach { w -> resultString = "$resultString $w" }
            map[resultString.trim()] = checkAgainstDictionary(i)
        }

        // Return string with most words in dictionary. If no dictionary words found,
        // assume most frequent letter = 'e' and use to calculate key
        return if (map.maxBy { it.value }!!.value == 0) {
            val offset = 101 - findLikeliestE(str).toInt()
            when {
                offset == 0 -> str
                offset < 0 -> encipher(str, offset + 26)
                else -> encipher(str, offset)
            }
        } else {
            map.maxBy { it.value }!!.key
        }
    }

    /**
    * Takes a ciphered {@link String}, splits it into individual words,
     * and find all possible encodings based on a shift value of 0 - 25
    * @param str {@link String}
    * @return returns all possible encodings of each word in the ciphered {@link String} in a {@link MutableList}
     * of a {@link MutableList}
    */
    private fun listPossibleEncodings(str: String): MutableList<MutableList<String>> {
        val resultList: MutableList<MutableList<String>> = mutableListOf()
        (0..25).forEach { n ->
            val resultString : MutableList<String> = mutableListOf()
            str.split(" ").mapTo(resultString) { encipher(it, n) }
            resultList.add(resultString)
        }
        return resultList
    }

    /**
     * Returns score for {@link String} based on how many words are found in dictionary.txt
     * @param listOfWords {@link MutableList} of {@link String}
     * @return returns {@link Int} count of words found in dictionary.txt that match the input
     */
    fun checkAgainstDictionary(listOfWords: MutableList<String>) :Int {
        return listOfWords.count { dictionary.contains(it) }
    }

    /**
     * Identifies most common character in the encoded {@link String}
     * @param word {@link String} single enciphered word
     * @return {@link Char} most common character in word
     */
    fun findLikeliestE(word: String): Char {
        return word.groupingBy { it }.eachCount().maxBy { it.value }!!.key
    }
}