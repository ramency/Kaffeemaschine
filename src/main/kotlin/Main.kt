package org.ramency.kaffeemaschine

val kaffeeMaschine: Kaffeemaschine = Kaffeemaschine()

fun main() {
    while (true) {
        val chosenCoffee = kaffeeMaschine.chooseCoffee()
        kaffeeMaschine.processPayment(chosenCoffee)
        kaffeeMaschine.addAdditives()
    }
}