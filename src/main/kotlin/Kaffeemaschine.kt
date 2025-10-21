import kotlin.math.roundToInt
import kotlin.math.truncate

class Kaffeemaschine {
    var moneyInput: String = ""
    var moneyStored: String = ""

    // Map available coffee types to their prices
    val availableCoffeeMap: Map<String, Int> = mapOf(
        "Kaffee" to 30,
        "Cappuccino" to 35,
        "Espresso" to 40,
        "Premium" to 120,
    )

    val acceptedCoinsMap: Map<String, Int> = mapOf(
        "5 cent" to 5,
        "10 cent" to 10,
        "20 cent" to 20,
        "50 cent" to 50,
        "1 euro" to 100,
        "2 euro" to 200,
    )

    // Checks if input is type number and valid
    fun inputChecker(): Number {
        val input: String = readln()

        try {
            val inputVal = input.toInt() - 1

            if (availableCoffeeMap.entries.indices.contains(inputVal)) {
                return inputVal + 1
            } else {
                throw NumberFormatException()
            }

        } catch (e: NumberFormatException) {
            println("Die Eingabe ist keine valide Zahl!")
            println("Bitte erneut eingeben.")
            return inputChecker()
        }
    }

    fun chooseCoffee(): Number {
        println("Welchen Kaffee möchten Sie?")
        for (entry in availableCoffeeMap.entries) {
            val keyIndex = availableCoffeeMap.entries.indexOf(entry)

            var euros: Int
            var cents: Int
            val priceInCents: Double = entry.value.toDouble()

            euros = truncate(priceInCents / 100.0).roundToInt()
            cents = (priceInCents % 100.0).roundToInt()

            val convertedPrice: String =
                if (priceInCents > 100.0) {
                    "$euros Euro und $cents Cent"
                } else {
                    "$cents Cent"
                }

            println("${keyIndex + 1}) ${entry.key} für $convertedPrice")
        }

        println("")
        println("Bitte geben Sie die gewünschte Zahl ein.")

        return inputChecker()
    }
}