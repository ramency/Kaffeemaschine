package org.ramency.kaffeemaschine

/**
 * This class contains all the functions of our coffee machine.
 *
 * @constructor Creates a coffee machine object.
 * @property availableCoffeeMap Contains all coffee types and their prices.
 * @property acceptedCoinsMap Contains all coin types this machine takes.
 * @property acceptedAdditivesArray Contains all possible additives for the coffee.
 */
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

    // ----------------------------------------------------------------------------------

    fun chooseCoffee(): Int {
        println("Welchen Kaffee möchten Sie?")
        for (entry in availableCoffeeMap.entries) {
            val keyIndex = availableCoffeeMap.entries.indexOf(entry)

            val convertedPrice = HelperFunctions.convertMoney(entry.value)

            println("${keyIndex + 1}) ${entry.key} für $convertedPrice")
        }

        println("")
        println("Bitte geben Sie die gewünschte Zahl ein.")

        val coffeeChoiceInt: Int = HelperFunctions.inputChecker("coffeeChoice") as Int
        return coffeeChoiceInt
    }

    fun processPayment(chosenCoffee: Number) {
        val coffeeName: String = availableCoffeeMap.keys.toTypedArray()[chosenCoffee as Int - 1]
        val coffeePrice: Int = HelperFunctions.getCoffeePriceFromName(coffeeName)
        val convertedCoffeePrice: String = HelperFunctions.convertMoney(coffeePrice)
        val acceptedCoins: String = acceptedCoinsMap.keys.toString()

        println("Bitte werfen Sie den gewünschten Betrag ein. (Bitte Eingabe in Cent angeben)")
        println("Gewünschter Betrag: $convertedCoffeePrice für 1x $coffeeName")
        println("")
        println("Dieser Automat nimmt folgende Münzen.")
        println(acceptedCoins)

        HelperFunctions.collectCoins(coffeePrice)
    }

    fun addAdditives() {
        val additiveChoices: String = acceptedAdditivesArray.contentToString()
        println("Möchten Sie noch etwas zu Ihrem Kaffee dazu haben?")
        println("")
        println("Auswahlmöglichkeiten: $additiveChoices")

        val chosenAdditive = HelperFunctions.inputChecker("additiveChoice")
        println("Ihre Auswahl: $chosenAdditive")
        println("Ihr Kaffee wird nun zubereitet. Bitte warten...")
        println("")

        println("Genießen Sie Ihren Kaffee.")
    }
}