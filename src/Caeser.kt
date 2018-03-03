import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.*
import kotlin.math.absoluteValue

class Caeser {

    data class Item(val letter: Char, val frequency: Int)
    lateinit var dictionary : Set<String>;

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

    fun getListOfPossibleEncodings(s: String): MutableList<MutableList<String>> {
        val words = s.split(" ")
        var resultList: MutableList<MutableList<String>> = mutableListOf()

        for (n in 0..25) {
            var resultString : MutableList<String> = mutableListOf()
            for (w in words) {
                val cleanString = cleanString(w)
                resultString.add(encipher(w, n))
            }
            resultList.add(resultString)
        }
        return resultList
    }


    fun checkAgainstDictionary(listOfWords: MutableList<String>) :Int {
        var score = 0
        for(w in listOfWords) {
            if(dictionary.contains(w)) {
                score++
            }
        }

        return score
    }

    fun decipher(s: String): String {
        dictionary = readDictionary()
        var listOfOptions = getListOfPossibleEncodings(s)
        var map : MutableMap<String, Int> = mutableMapOf()
        var bestWord: String

        for (i in listOfOptions) {
            var resultString= ""
            for(w in i) {
                resultString = resultString + w + " "
            }
            resultString = resultString.trim()
            map.put(resultString, checkAgainstDictionary(i));
        }

        // If highest value = 0 (single words, no common words in sentence...) use 'e' char frequency
        bestWord = if (map.maxBy { it.value }!!.value == 0) {
            val commonestLetter = findLikeliestE(s)
            val offset = 101 - commonestLetter!!.toInt()
            when {
                offset == 0 -> s
                offset < 0 -> encipher(s, offset + 26)
                else -> encipher(s, offset)
            }
        } else {
            map.maxBy { it.value }!!.key
        }
        return bestWord
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
        val resultMap = word.groupingBy { it }.eachCount()
        return resultMap.maxBy { it.value }?.key
    }

    // Reads dictionary.txt into set for membership testing
    fun readDictionary() : MutableSet<String> {
        lateinit var wordSet: MutableSet<String>
        try {
            val inputStream: InputStream = File("src/dictionary.txt").inputStream()
            wordSet = mutableSetOf<String>()
            inputStream.bufferedReader().useLines { word -> word.forEach { wordSet.add(it) }}
        } catch (e: IOException) {
            e.printStackTrace()
            println("File not read properly!")
        }
        return wordSet
    }

}