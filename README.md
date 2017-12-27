Walking Dead
============

Another Zombie / Human simulation written in Java.

![Screenshot of the result](https://raw.githubusercontent.com/madeindjs/WalkingDead/master/screenshot.gif)

Scenario
--------

`Human`s (in blue) & `Zombie`s (in red) lives together.
A **loop** (100 ms) consist of theses steps:

* `Human` moves. He make only one of theses actions
  * **escape** from zombie if zombies are in `AFFRAID_AREA`
  * **rejoin** nearest human if another human is in `VISION`
  * **have sex** with another human if two human are on the same point & together are major :)
  * **walk** to the center if no action behind can be executed
* **Create** new humans if some `Baby` exists
* `Zombie` moves. He make only one of theses actions
  * **rejoin** nearest human if another human is in `VISION`
  * **fight** with another human another human exists on the same point
  * **walk** to the center if no action behind can be executed
* add X year to each humans
* **Kill** all died people if somenone have not enought life or `age` is over to `maxAge`. Of course, if an human died, is converted to `Zombie`

If you think the game is too slow, you can click on the screen to add zombie!

Contribute
----------

I'm open minded to any suggesstion / modification. Just [fork this repository](https://github.com/madeindjs/WalkingDead#fork-destination-box).