import java.io.Serializable
import kotlin.math.roundToInt
import kotlin.math.truncate

class Kaffeemaschine {
    // Map available coffee types to their prices
    val availableCoffeeMap: Map<String, Int> = mapOf(
        "Kaffee" to 30,
        "Cappuccino" to 35,
        "Espresso" to 40,
        "Premium" to 120,
    )

    val acceptedCoinsMap: Map<String, Int> = mapOf(
        "5 Cent" to 5,
        "10 Cent" to 10,
        "20 Cent" to 20,
        "50 Cent" to 50,
        "1 Euro" to 100,
        "2 Euro" to 200,
    )

    val acceptedAdditivesArray: Array<String> = arrayOf(
        "Milch",
        "Zucker",
        "Nichts",
        "",
    )

    fun convertMoney(valInCents: Int): String {
        var euros: Int
        var cents: Int
        val valInCentsFr: Double = valInCents.toDouble()

        euros = truncate(valInCentsFr / 100.0).roundToInt()
        cents = (valInCentsFr % 100.0).roundToInt()

        val convertedVal: String =
            if (valInCentsFr > 100.0) {
                "$euros Euro und $cents Cent"
            } else if ((valInCentsFr % 100.0) == 0.0) {
                "$euros Euro"
            } else {
                "$cents Cent"
            }

        return convertedVal
    }

    // Checks if input is type number and valid
    fun inputChecker(type: String): Serializable {
        val input: String = readln()
        val inputVal: Any

        when (type) {
            "coffeeChoice" -> {
                try {
                    inputVal = input.toInt() - 1

                    if (availableCoffeeMap.entries.indices.contains(inputVal)) {
                        return inputVal + 1
                    } else {
                        throw NumberFormatException()
                    }

                } catch (e: NumberFormatException) {
                    println("Die Eingabe ist keine valide Zahl!")
                    println("Bitte erneut eingeben.")
                    return inputChecker("coffeeChoice")
                }
            }

            "validCoins" -> {
                try {
                    inputVal = input.toInt()

                    if (acceptedCoinsMap.containsValue(inputVal)) {
                        return inputVal
                    } else {
                        throw NumberFormatException()
                    }
                } catch (e: NumberFormatException) {
                    println("Die Eingabe ist kein valider Geldbetrag!")
                    println("Bitte erneut eingeben.")
                    return inputChecker("validCoins")
                }
            }

            "additiveChoice" -> {
                    val additivesTLC: String = acceptedAdditivesArray.contentToString().lowercase()
                    val inputTLC: String = input.lowercase()

                try {
                    if (additivesTLC.contains(inputTLC)) {
                        return input.replaceFirstChar { it.titlecase() }
                    } else {
                        throw Exception()
                    }
                } catch (e: Exception) {
                    println("Die Eingabe ist entspricht keiner der Optionen!")
                    println("Bitte erneut eingeben.")
                    return inputChecker("additiveChoice")
                }
            }

            else -> {
                println("Fehler bei der Auswahl eines Input Check Typs")
                return 0
            }
        }
    }

    fun chooseCoffee(): Number {
        println("Welchen Kaffee möchten Sie?")
        for (entry in availableCoffeeMap.entries) {
            val keyIndex = availableCoffeeMap.entries.indexOf(entry)

            val convertedPrice = convertMoney(entry.value)

            println("${keyIndex + 1}) ${entry.key} für $convertedPrice")
        }

        println("")
        println("Bitte geben Sie die gewünschte Zahl ein.")

        val coffeeChoiceNum: Number = inputChecker("coffeeChoice") as Number
        return coffeeChoiceNum
    }

    fun processPayment(chosenCoffee: Number) {
        val coffeeName: String = availableCoffeeMap.keys.toTypedArray()[chosenCoffee as Int - 1]
        val coffeePrice: Int
        val acceptedCoins: String = acceptedCoinsMap.keys.toString()

        // Potenzielle Verbesserung Nötig (Throw/Catch Exception)
        if (availableCoffeeMap[coffeeName] != null) {
            coffeePrice = availableCoffeeMap[coffeeName]!!
        } else {
            coffeePrice = 0
            println("Es gab einen Fehler beim Auslesen des Kaffeepreises!")
        }

        println("Bitte werfen Sie den gewünschten Betrag ein. (Bitte Eingabe in Cent angeben)")
        println("Dieser Automat nimmt folgende Münzen.")
        println(acceptedCoins)
        println("")

        var moneyInput: Int
        var moneyStored = 0
        var coffeePayed = false

        while (!coffeePayed) {
            moneyInput = inputChecker("validCoins") as Int
            moneyStored += moneyInput

            if (moneyStored == coffeePrice) {

                println("Vielen Dank. Ihr Kaffee wird nun zubereitet.")
                coffeePayed = true

            } else if (moneyStored > coffeePrice) {

                val returnMoney: String = convertMoney(moneyStored - coffeePrice)

                println("Vielen Dank. Ihr Kaffee wird nun zubereitet.")
                println("Sie erhalten außerdem $returnMoney zurück.")
                coffeePayed = true

            } else /* if (moneyStored < coffeePrice) */ {

                val missingMoney: String = convertMoney(coffeePrice - moneyStored)
                println("Es fehlt ein Betrag von $missingMoney.")
                println("Bitte werfen Sie weitere Münzen ein.")

            }
        }
    }

    fun addAdditives() {
        val additiveChoices: String = acceptedAdditivesArray.contentToString()
        println("Möchten Sie noch etwas zu Ihrem Kaffee dazu haben?")
        println("")
        println("Auswahlmöglichkeiten: $additiveChoices")

        val chosenAdditive = inputChecker("additiveChoice")
        println("Ihre Auswahl: $chosenAdditive")

        println("Genießen Sie Ihren Kaffee.")
    }
}