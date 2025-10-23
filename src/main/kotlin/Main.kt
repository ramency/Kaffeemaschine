package org.ramency.kaffeemaschine

// Instantiate Kaffeemaschine object
val kaffeeMaschine: Kaffeemaschine = Kaffeemaschine()

/**
 * This is the main function. It repeatedly executes the coffee machine's
 * functions in a loop.
 *
 *
 * @see Kaffeemaschine
 * @see HelperFunctions
 * @author ramency
 * */
fun main() {
    while (true) {
        val chosenCoffee = kaffeeMaschine.chooseCoffee()
        kaffeeMaschine.processPayment(chosenCoffee)
        kaffeeMaschine.addAdditives()
    }
}