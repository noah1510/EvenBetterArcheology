# Changelog

## 2.0.0-alpha.8

* Move worldgen tags to the new data generation API (This should allow for easier adding of mod biomes)
* Use more tags instead of specifying biomes directly. The following mods should generate Even Better Archeology structures now:
  * terralith
  * wider wild
  * terrestria
  * Traverse
  * William Wythers' Overhauled Overworld
  * Regions Unexplored
* Create an Identifier class that allows tags
* Create a loot distribution class to generate loot tables
* Generate the Archeoloy Tabe loot table in code
  * add all levels of soaring winds to loot table
  * add artifacts form the artifacts mod as possible loot
* Use mc-publish github action to publish releases to modrinth

## 2.0.0-alpha.7

* Another rework of the Datageneration API (Should be mostly stable now)
* Generating all blockstate and item models in code
* Generating all recipes in code
* Rewritten implementation of the fossils (they now share as much code as possible)
* Rewritten vases (loot vase and regular vase are the same class now just with a bool to differentiate them)
* Renamed rotton_x to rotton_wood_x (eg. rotton_planks -> rotton_wood_planks). This should be the last breaking change.
* Adding some documentation using doxygen
* Creating all tags using the data generation API
* Transition loot tables to use the new data generation API
* Updated nbt data of the structures (now they actually have the mod blocks and the chests have loot)
* Migrate the structure processors to the new data generation API

## 2.0.0-alpha.6

* More work on the data generation API
* Generation of blockstates, block models and block item is now split
* Created interface to generate data in block classes
* Generating models for items
* Generating more block models
* Allow automatically adding items to creative menu

## 2.0.0-alpha.5

* Adding custom annotations to generate data of blocks
* Using Arrp to generate even more data
* moving some textures in other folders

## 2.0.0-alpha.4

* Using arrp to generate data dynamically
* Fixing broken build due to implementation of the ItemRarity Tag

## 2.0.0-alpha.3

* Using owo-lib to register items and blocks
* Using owo-lib for creative menu
* Using data generators to register brushes

## 2.0.0-alpha.2

* Support for Numismatic-Overhaul
* Added helper class for villager trades
* reworked villager trades and costs
* renaming stuff that was missed before

## 2.0.0-alpha.1

* Support for Trinkets API
* Using OwO lib for config
* support for higher levels of soaring winds
* rename form BetterArcheology to EvenBetterArcheology