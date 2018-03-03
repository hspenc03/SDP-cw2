import java.io.File
import java.io.IOException
import java.io.InputStream

class Caesar {

    var dictionary : Set<String> = readDictionary()

    fun encipher(s: String, offset: Int): String {
        var cipheredString = ""

        // Checks valid input for offset
        if(offset < 0 || offset > 25) {
            throw IllegalArgumentException()
        }

        val firstChar = s[0]

        // Base case - return if last character in input string
        if (s.length == 1) {
            return helperFunction(firstChar, offset).toString()
        } else {
            // Builds string with changed chars
            cipheredString += helperFunction(firstChar, offset)
        }
        // Recursive string build with unchanged characters
        cipheredString += encipher(s.substring(1), offset)
        return cipheredString
    }

    fun helperFunction(c: Char, n: Int): Char {
        val limit: Int
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

    private fun getListOfPossibleEncodings(s: String): MutableList<MutableList<String>> {
        val words = s.split(" ")
        val resultList: MutableList<MutableList<String>> = mutableListOf()

        // Run through encipher using each possible offset and return list of results
        for (n in 0..25) {
            val resultString : MutableList<String> = mutableListOf()
            words.mapTo(resultString) { encipher(it, n) }
            resultList.add(resultString)
        }
        return resultList
    }

    // Returns score for string based on how many words are in our dictionary
    fun checkAgainstDictionary(listOfWords: MutableList<String>) :Int {
        return listOfWords.count { dictionary.contains(it) }
    }


    fun decipher(s: String): String {
        val map : MutableMap<String, Int> = mutableMapOf()

        for (i in getListOfPossibleEncodings(s)) {
            var resultString = ""
            i.forEach { w -> resultString = "$resultString $w" }
            map[resultString.trim()] = checkAgainstDictionary(i)
        }

        // Return string with most words in dictionary. Or, if no dictionary words found,
        // assume most frequent letter = 'e' and calculate key accordingly
        return if (map.maxBy { it.value }!!.value == 0) {
            val offset = 101 - findLikeliestE(s)!!.toInt()
            when {
                offset == 0 -> s
                offset < 0 -> encipher(s, offset + 26)
                else -> encipher(s, offset)
            }
        } else {
            map.maxBy { it.value }!!.key
        }
    }

    fun cleanString(phrase: String):String {
        var returnString = ""
        phrase.toLowerCase()
                .toCharArray()
                .filter { it.isLetter() }
                .forEach { returnString += it }
        return returnString
    }

    // Returns char value for most frequent letter - add null check?
    fun findLikeliestE(word: String): Char? {
        val resultMap = word.groupingBy { it }.eachCount()
        return resultMap.maxBy { it.value }?.key
    }

    // Reads dictionary.txt into set for membership testing
    fun readDictionary() : MutableSet<String> {
        lateinit var wordSet: MutableSet<String>
        try {
            val inputStream: InputStream = File("src/dictionary.txt").inputStream()
            wordSet = mutableSetOf()
            inputStream.bufferedReader().useLines { word -> word.forEach { wordSet.add(it) } }
        } catch (e: IOException) {
            e.printStackTrace()
            println("File not read properly!")
        }
        return wordSet
    }

}