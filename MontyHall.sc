import scala.util.Random

val numberOfSimulations = 10000000.ensuring(_ >= 1)

val numberOfDoors = 3.ensuring(_ >= 2)

// Printing intermediary steps makes the simulation (MUCH) slower
val printSteps = false

def simulation(simulationNumber: Int): Boolean = {
  if (printSteps) println(s"===STARTING SIMULATION #$simulationNumber===")

  val prizePosition = Random.nextInt(numberOfDoors)
  val startingOptions =
    (0 until numberOfDoors)
      .map(e => (e == prizePosition, e))

  if (printSteps) println(s"StartingOptions = $startingOptions")

  val firstChoice = startingOptions(Random.nextInt(numberOfDoors))

  if (printSteps) println(s"FirstChoice = $firstChoice")

  val excludedOptions =
    startingOptions
      .filter(opt => opt != firstChoice && !opt._1)
      .take(numberOfDoors - 2)

  if (printSteps) println(s"ExcludedOptions = $excludedOptions")

  val finalOptions = startingOptions.diff(excludedOptions)

  if (printSteps) println(s"FinalOptions = $finalOptions")

  val switchingOption = finalOptions.filter(_ != firstChoice).head

  if (printSteps) println(s"SwitchingOption = $switchingOption")

  if (printSteps) println(s"Win switching = ${switchingOption._1}")

  switchingOption._1
}

val winSwitching = 1 to numberOfSimulations count simulation

f"Win by switching in ${winSwitching * 1.0 / numberOfSimulations * 100}%.1f%% of the simulations."
