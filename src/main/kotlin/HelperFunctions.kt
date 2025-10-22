package org.ramency.kaffeemaschine

import kotlin.math.roundToInt
import kotlin.math.truncate

object HelperFunctions {
    val availableCoffeeMap = kaffeeMaschine.availableCoffeeMap
    val acceptedCoinsMap = kaffeeMaschine.acceptedCoinsMap
    val acceptedAdditivesArray = kaffeeMaschine.acceptedAdditivesArray

    // -----------------------------------------------------

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

    // This function gets an input and checks if it's the correct type and
    // format, according to the input type parameter
    fun inputChecker(type: String): Any {
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

    // "Check"-Functions for inputChecker
    fun checkCoffeeChoice(input: String): Int {
        var inputVal: Any = 0

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
            return inputVal as Int + 1
        }
    }

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
            inputChecker("validCoins")
            return 0
        }
    }

    fun checkAdditiveChoice(input: String): String {
        val inputTLC: String = input.lowercase()
        var output: String = ""

        /*
        TODO: Fix detection of element in acceptedAdditivesArray. Should only
              be detected if whole word matches (case INsensitive)
        */
        try {
            loop@ while (acceptedAdditivesArray.iterator().hasNext()) {
                if (acceptedAdditivesArray.iterator().next().lowercase() == inputTLC) {
                    output = input.replaceFirstChar { it.titlecase() }
                } else {
                    continue@loop
                }
            }

            if (output == "") { throw Exception() }
            return output
        } catch (e: Exception) {
            println("Die Eingabe ist entspricht keiner der Optionen!")
            println("Bitte erneut eingeben.")
            inputChecker("additiveChoice")
            return ""
        }
    }

    // -----------------------------------------------------

    fun getCoffeePriceFromName(input: String): Int {

        // Exception unhandled - if wrong input is given, program might crash
        require(availableCoffeeMap.containsKey(input))
        val coffeePrice: Int = availableCoffeeMap[input]!!
        return coffeePrice

    }

    fun collectCoins(coffeePrice: Int) {
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
}