//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val kaffeemaschine = Kaffeemaschine()

    val chosenCoffee = kaffeemaschine.chooseCoffee()
    kaffeemaschine.processPayment(chosenCoffee)
    kaffeemaschine.addAdditives()
}