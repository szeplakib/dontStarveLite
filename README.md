# dontStarveLite
dontStarveLite is a sample game, and hobby project, originaly a home project for School.
# About
Don't Starve is the property of Klei Entertainment, this little game is NOT made by Klei, It's only a hobby project. Also all sprites are made by me.
I hope I have respected the Player Creation Guidlines:

https://support.klei.com/hc/en-us/articles/360029880791-Player-Creation-Guidelines

This software is free and open-source, I don't use it for commercial purposes.

# Dependencies
The whole project is written in Java, the only library used is JavaFx, which was part of Java 8. Since I wanted to learn a project management tool, I left Java 8 in favor of Java 11, and managed it's openJFX dependency with Maven.

# The game

### Goal
The goal of the game is to survive as long as you can, by don't let your needs dropping to zero.

### Actions
The game is turned based, every action will trigger a turn in the game, namely:
* Moving
* Waiting
* Collecting
* Crafting
* Cooking
* Placing a campfire

### Controls
* Move: WASD
* Collect/Wait: E (If there is no active structure on the tile E is waiting, spending an Action Point)
* Place campfire: SHIFT
* Eat edible items: Left click in the inventory
* Use items/craft: Right click in the inventory (Tool usage is automaticle done with the E button on tiles with the corresponding structure)

When an Action Point is consumed, the need bar changes. The character will get hungry in the daytime, and it will even lose health, and sanity in the darkness of night.

### Tools
The player will need tools in order to cut down trees, or to smash rock into pieces. When an ingredient is in the inventory, all of it's recipes are available by right clicking on it's button in the inventory. If all ingredient is available in a recipe, the player may craft it. After a while your tools might wear out. If that happens you have to replace them with a new one.

### Nights, Campfires and Sanity
Nights are especialy dangerious, one might place a campfire before each night to save the most sanity. At the light of the campfire sanity only slowly depleting in the night.

Sanity can be restored by picking flowers, also flowers are edible.

Campfires are active for a day, so you have to build a new campfire every day.

### Cooking
Cooked food may be most beneficial since it may restore more hunger, also the red berries take HP away when eaten raw. The player may cook the food at a placed campfire.

Health can be restored by some types of food.

### Blockades
The player cannot move on tilse with water, or Mountain on it.

### Biomes
The map is random generated with a few biomes, which sizes and shapes are also random.
* Water - blockade
* Mountain - blocade
* Plains - Every kind of resource is available
* Dry grass - Only carrots, and dry grass pacthes spawn here but in more often
* Rocky plains - Only rocks, and flint but plenty
* Marshland - Trees, twigs, and a small amount of flint can be found here

## Good Luck, fellow explorer!
