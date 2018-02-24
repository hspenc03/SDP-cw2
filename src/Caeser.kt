class Caeser {
    fun encipher(s: String, n: Int): String {
        var cipheredString = ""

        //checks valid input for n
        if(n < 0 || n > 25) {
            throw IllegalArgumentException()
        }
        var charArray = s.toCharArray()

        // Base case - return if last character in input string
        if (s.length == 1) {
            return helperFunction(s.toCharArray()[0], n).toString()
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
        return encipher(s, 25)
    }

    fun findOffset(s: String): Int {
//make 26 different enciphers and compare which have most e's etc
        var e = "e"
        var ascii = e.toInt()

        //var key =
        return 1
        //return(mostFrequent)
    }

    fun findTopThreeFrequencies(s: String) {
        var clean = cleanString(s)
        var frequencies = groupingFrequency(clean)
        lateinit var mostFrequent: MutableList<Pair<Char, Int>>
        for (f in frequencies) {
            mostFrequent.add(Pair(f.key, f.value))
        }
        //mostFrequent.sortBy {  }
    }

    fun cleanString(phrase: String):String {
        var returnString = ""
        phrase.toLowerCase().toCharArray()
                .filter { it.isLetter() }
                .forEach { returnString += it }
        return returnString
    }

    fun groupingFrequency(word: String) : Map<Char, Int> {
        val words = word.split("")
        return word.groupingBy {it}.eachCount()
        //rank rather than frequency
    }


}