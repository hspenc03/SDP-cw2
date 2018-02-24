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
        return 1
    }

    fun cleanString(phrase: String):String {
        var returnString = ""
        phrase.toLowerCase().toCharArray()
                .filter { it.isLetter() }
                .forEach { returnString += it }
        return returnString
    }


}