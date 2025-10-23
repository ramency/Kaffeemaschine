package org.ramency.kaffeemaschine

import kotlin.math.roundToInt
import kotlin.math.truncate

/**
 * This object contains all helper functions needed for the coffee machine.
 *
 * It contains the following functions:
 *
 * @property convertMoney
 * @property inputChecker
 * @property checkCoffeeChoice
 * @property checkValidCoins
 * @property checkAdditiveChoice
 * @property getCoffeePriceFromName
 * @property collectCoins
 * @author ramency
 */
object HelperFunctions {
    val availableCoffeeMap = kaffeeMaschine.availableCoffeeMap
    val acceptedCoinsMap = kaffeeMaschine.acceptedCoinsMap
    val acceptedAdditivesArray = kaffeeMaschine.acceptedAdditivesArray

    // -----------------------------------------------------

    /**
     *  This function does conversion from an integer to a readable/displayable string format.
     *
     *  @param valInCents Takes the value in Cents as an integer.
     *  @return Returns the value as a readable string, converted to Euro and Cents.
     */
    fun convertMoney(valInCents: Int): String {
        var euros: Int
        var cents: Int
        val valInCentsFr: Double = valInCents.toDouble()

        euros = truncate(valInCentsFr / 100.0).roundToInt()
        cents = (valInCentsFr % 100.0).roundToInt()

        val convertedVal: String = if (valInCentsFr > 100.0) {
            "$euros Euro und $cents Cent"
        } else if ((valInCentsFr % 100.0) == 0.0) {
            "$euros Euro"
        } else {
            "$cents Cent"
        }

        return convertedVal
    }

    // -----------------------------------------------------

    /**
     *  This function gets an input and checks if it's the correct type and
     *  format, according to the input type parameter.
     *
     *  @param type Takes a string as a parameter that specifies which operation should be
     *  executed -> [checkCoffeeChoice], [checkValidCoins] or [checkAdditiveChoice]
     *  @return Returns the given Value as an integer if it is considered valid. If not,
     *  a new input will be requested.
     */
    fun inputChecker(type: String): Int {
        val input: String = readln()

        when (type) {
            "coffeeChoice" -> return checkCoffeeChoice(input)
            "validCoins" -> return checkValidCoins(input)
            "additiveChoice" -> return checkAdditiveChoice(input)

            else -> {
                println("Fehler bei der Auswahl eines Input Check Typs")
                return 0
            }
        }
    }

    /**
     * This Function is called by [inputChecker] to determine and check the users' coffee choice.
     *
     * @param input Takes a string as an input, tries to extract integer from it.
     * @return Returns said integer as index for [availableCoffeeMap].
     * @throws NumberFormatException Should the value be either not an integer or an invalid number,
     * this exception will be thrown and a new input will be requested.
     */
    fun checkCoffeeChoice(input: String): Int {
        var inputVal: Any

        try {
            inputVal = input.toInt() - 1

            when {
                inputVal < 0 -> throw NumberFormatException()
                inputVal in availableCoffeeMap.entries.indices -> return inputVal + 1
                else -> throw NumberFormatException()
            }

        } catch (e: NumberFormatException) {
            println("Die Eingabe ist keine valide Zahl!")
            println("Bitte erneut eingeben.")
            return inputChecker("coffeeChoice")
        }
    }

    /**
     * This function is called by [inputChecker] to check if the inputted coin values are valid.
     *
     * @param input Takes a string as an input, tries to extract a coin value as an integer.
     * @return Returns said coin value as integer.
     * @throws NumberFormatException Should the value be either not an integer or an invalid number,
     * this exception will be thrown and a new input will be requested.
     */
    fun checkValidCoins(input: String): Int {
        val inputVal: Any

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

    /**
     * This function is called by [inputChecker] to check if valid extras were chosen.
     *
     * @param input Takes a string as an input, tries to convert it into integer.
     * @return Returns said integer. (index for [acceptedAdditivesArray])
     * @throws NumberFormatException Should the value be either not an integer or an invalid number,
     * this exception will be thrown and a new input will be requested.
     */
    fun checkAdditiveChoice(input: String): Int {
        var inputVal: Any

        try {
            inputVal = input.toInt() - 1

            when {
                inputVal < 0 -> throw NumberFormatException()
                inputVal in 0..<acceptedAdditivesArray.size -> return inputVal + 1
                else -> throw NumberFormatException()
            }

        } catch (e: NumberFormatException) {
            println("Die Eingabe ist keine valide Zahl!")
            println("Bitte erneut eingeben.")
            return inputChecker("additiveChoice")
        }
    }

    // -----------------------------------------------------

    /**
     * This function takes name as key for [availableCoffeeMap] and extracts value (price) from it.
     *
     * @param input Name of the requested coffee.
     * @return Returns price in cents as integer.
     * @throws IllegalArgumentException Should the value be either not a string or null,
     * this exception will be thrown and the ordering process will reset to the beginning.
     */
    fun getCoffeePriceFromName(input: String): Int {

        try {
            require(availableCoffeeMap.containsKey(input))
            val coffeePrice: Int = availableCoffeeMap[input]!!
            return coffeePrice
        } catch (e: IllegalArgumentException) {
            println("Value wasn't found in availableCoffeeMap!")
            println("Resetting to beginning!")
            return kaffeeMaschine.chooseCoffee()
        }

    }

    /**
     *  This function checks how much money was inserted to the price of the coffee.
     *
     *  @param coffeePrice Price that is requested to be paid.
     */
    fun collectCoins(coffeePrice: Int) {
        var moneyInput: Int
        var moneyStored = 0
        var coffeePayed = false

        while (!coffeePayed) {
            moneyInput = inputChecker("validCoins")
            moneyStored += moneyInput

            if (moneyStored == coffeePrice) {

                coffeePayed = true

            } else if (moneyStored > coffeePrice) {

                val returnMoney: String = convertMoney(moneyStored - coffeePrice)

                println("Sie erhalten $returnMoney zurück.")
                coffeePayed = true

            } else /* if (moneyStored < coffeePrice) */ {

                val missingMoney: String = convertMoney(coffeePrice - moneyStored)
                println("Es fehlt ein Betrag von $missingMoney.")
                println("Bitte werfen Sie weitere Münzen ein.")

            }
        }
    }
}