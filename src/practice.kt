class practice {
    fun encipher(s: String, n: Int): String {
        if(n < 0 || n > 25 || n !is Int || s !is String) {
            throw IllegalArgumentException()
        } //regex to check string

        var modifiedS = s;
        if (s == "") {
            //move by 1
            //return
        }

        return (encipher(modifiedS, n))
    }

    fun helperFunction(c: Char, n: Int): Char {
        var limit = 0
        when(c.isLowerCase()) {
            true -> limit = 122
            false -> limit = 90
        }
        var result = c + n
        if (result.toInt() > limit) {
            result = result - 26
        }
        return result
    }

    fun main(args: Array<String>) {
        var a = 'a'
        println(a + 2)

    }

}