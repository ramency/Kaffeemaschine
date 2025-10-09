package org.ramency

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    var coffeePrice: Int = 0
    var inputMoney: Int = 0
    var moneyInMachine:Int = 0
    var missingMoney:Int = 0
    var extraMoney:Int = 0

    println("Guten Tag! Welchen Kaffee möchten Sie haben?")
    println("1. Kaffee (30 Cent)")
    println("2. Cappuccino (35 Cent)")
    println("3. Espresso (40 Cent")
    println("Geben Sie bitte die zugehörige Nummer ein (1, 2 oder 3).")

    val coffeeChoice = readln()

    println("Bitte geben Sie den angegebenen Betrag in Cent ein.")

    when(coffeeChoice) {
        "1" -> {
            println("30 Cent")
            coffeePrice = 30
        }

        "2" -> {
            println("35 Cent")
            coffeePrice = 35
        }

        "3" -> {
            println("40 Cent")
            coffeePrice = 40
        }

        else -> println("Fehlerhafte Eingabe!")
    }

    inputMoney = readln().toInt()
    
    moneyInMachine += inputMoney
    
    if(moneyInMachine >= coffeePrice) {
        println("Vielen Dank!")

        if(moneyInMachine > coffeePrice) {
            extraMoney = moneyInMachine - coffeePrice
            println("Sie erhalten $extraMoney Cent zurück.")
        }
        additives()
    } else {
        missingMoney = coffeePrice - moneyInMachine
        println("Es fehlen noch $missingMoney Cent.", )
    }
}

fun additives() {
    TODO()
}