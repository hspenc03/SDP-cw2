class practice {
    fun encipher(s: String, n: Int): String {
        var cipheredString = ""

        if(n < 0 || n > 25) {
            throw IllegalArgumentException()
        }
        var charArray = s.toCharArray()

        // Base case - return if last character in input string
        if (s.length == 1) {
            var offsetChar = helperFunction(s.toCharArray()[0], n).toString()
            return offsetChar
        } else {
            var offsetChar = helperFunction(s.toCharArray()[0], n).toString()
            cipheredString += helperFunction(charArray[0], n)

        }

        cipheredString += encipher(s.substring(1), n)
        return cipheredString
    }

    fun helperFunction(c: Char, n: Int): Char {
        if(!c.isLetter()) {
            throw IllegalArgumentException()
        }

        var limit = 0
        limit = when(c.isLowerCase()) {
            true -> 122
            false -> 90
        }
        var result = c + n
        if (result.toInt() > limit) {
            result -= 26
        }
        return result
    }

    fun main(args: Array<String>) {
        var a = 'a'
        println(a + 2)

    }

}