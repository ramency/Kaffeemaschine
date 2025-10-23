package org.ramency.kaffeemaschine

/**
 * This class contains all main functions of the coffee machine.
 *
 * For subfunctions see [HelperFunctions].
 * @constructor Creates a coffee machine instance.
 * @property availableCoffeeMap Contains all coffee types and their prices.
 * @property acceptedCoinsMap Contains all coin types this machine takes.
 * @property acceptedAdditivesArray Contains all possible additives for the coffee.
 * @author ramency
 * @see HelperFunctions
 */
class Kaffeemaschine {
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
        "Milch und Zucker",
        "Nichts",
    )

    // ----------------------------------------------------------------------------------

    /**
     * This Function asks user for coffee + input of their choice.
     * @return Integer of chosen coffee (Index for availableCoffeeMap)
     * @see HelperFunctions.checkCoffeeChoice
     */
    fun chooseCoffee(): Int {
        println("Welchen Kaffee möchten Sie?")
        println("")
        for (entry in availableCoffeeMap.entries) {
            val keyIndex = availableCoffeeMap.entries.indexOf(entry)

            val convertedPrice = HelperFunctions.convertMoney(entry.value)

            println("${keyIndex + 1}) ${entry.key} für $convertedPrice")
        }

        println("")
        println("Bitte geben Sie die gewünschte Zahl ein.")

        val coffeeChoiceInt: Int = HelperFunctions.inputChecker("coffeeChoice")
        return coffeeChoiceInt
    }

    /**
     * This function handles the payment process, asks for coins, checks them
     * and waits until payment is completed.
     *
     * @param chosenCoffee Takes the index number + 1 as an input for [availableCoffeeMap]
     * @see HelperFunctions.collectCoins
     * @see HelperFunctions.checkValidCoins
     */
    fun processPayment(chosenCoffee: Int) {
        val coffeeName: String = availableCoffeeMap.keys.toTypedArray()[chosenCoffee - 1]
        val coffeePrice: Int = HelperFunctions.getCoffeePriceFromName(coffeeName)
        val convertedCoffeePrice: String = HelperFunctions.convertMoney(coffeePrice)
        val acceptedCoins: String = acceptedCoinsMap.keys.toString()

        println("Bitte werfen Sie den angegebenen Betrag ein. (Bitte Eingabe in Cent angeben)")
        println("Gewünschter Betrag: $convertedCoffeePrice für 1x $coffeeName")
        println("")
        println("Dieser Automat nimmt folgende Münzen.")
        println(acceptedCoins)

        HelperFunctions.collectCoins(coffeePrice)
    }

    /**
     * This function asks the user if they want any extras to their coffee.
     *
     * @see HelperFunctions.checkAdditiveChoice
     */
    fun addAdditives() {
        println("Möchten Sie noch etwas zu Ihrem Kaffee dazu haben?")
        println("Folgende Optionen stehen Ihnen zur Verfügung:")
        println("")
        for (element in acceptedAdditivesArray) {
            val elementIndex = acceptedAdditivesArray.indexOf(element)

            println("${elementIndex + 1}) $element")
        }

        val chosenAdditive: Int = HelperFunctions.inputChecker("additiveChoice")
        println("Ihre Auswahl: ${acceptedAdditivesArray[chosenAdditive - 1]}")
        println("")
        println("Vielen Dank!")
        println("Ihr Kaffee wird nun zubereitet. Bitte warten...")

        println("Besuchen Sie uns gerne bald wieder!")
        println("")
    }
}