import java.io.File
import java.io.IOException
import java.io.InputStream

class Caesar {
    var dictionary : Set<String> = readDictionary()

    // Reads dictionary.txt into Set to create list of commonest words
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

    // Shifts character according to value of offset
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

    // Encipher using every possible key value and return list of results
    private fun listPossibleEncodings(str: String): MutableList<MutableList<String>> {
        val resultList: MutableList<MutableList<String>> = mutableListOf()
        (0..25).forEach { n ->
            val resultString : MutableList<String> = mutableListOf()
            str.split(" ").mapTo(resultString) { encipher(it, n) }
            resultList.add(resultString)
        }
        return resultList
    }

    // Returns score for string based on how many words are found in dictionary.txt
    fun checkAgainstDictionary(listOfWords: MutableList<String>) :Int {
        return listOfWords.count { dictionary.contains(it) }
    }

    // Returns char value for most frequent letter
    fun findLikeliestE(word: String): Char {
        return word.groupingBy { it }.eachCount().maxBy { it.value }!!.key
    }
}