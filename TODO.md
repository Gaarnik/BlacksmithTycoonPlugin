# TODO List

## Project

- [X] Kotlin
- [X] Setup git repo
- [X] implement Vault API to get economy
- [X] Add blacksmith player service to handle connect, disconnect, data loading
- [X] Add system to save data for player (SQLite)

## Features

- [X] NPC "Blacksmith master"
    - [X] Spawn with command
    - [X] Disable AI
    - [X] Disable damage
    - [X] Mobs can't target NPC (EntityTargetEvent)

- [ ] Blacksmith Master menu
    - [X] "Remove" button to remove NPC
    - [X] Display player money
        - [X] Add the icon in the menu
        - [X] Link vault api to the item
    - [ ] Add Machines Shop button
        - [X] Shop Menu
        - [ ] Implement buy (check space in inventory & decrease money)
        - [ ] Orders Sign - Free
        - [ ] Skills Bookshelf
        - [ ] Blacksmith Crafting Table
        - [ ] Blacksmith Anvil
        - [ ] Blacksmith Enchanting Table
    - [ ] Add Materials Shop button
        - [ ] Shop Menu
        - [ ] provide materials to craft weapons

- [ ] Orders Sign 
    - [X] Based on Sign
    - [X] Show list of available orders
    - [ ] Fix sign orientation
    - [ ] Generate random list based on player blacksmith level
    - [ ] Giving order items give money to the player
    - [ ] ~~Order can contains multiple items~~

- [ ] Skills Bookshelf
    - [ ] Based on Bookshelf
    - [ ] Show player Blacksmith level (total of all skills) 
    - [ ] Show player Skills available
    - [ ] Upgrade and Unlock new skills with money & requirements
    
- [ ] Blacksmith Crafting Table
    - [ ] Based on Crafting table
    - [ ] Used to craft wooden & stone based weapons

- [ ] Blacksmith Anvil
    - [ ] Based on Forge
    - [ ] Used to craft metal based weapons

- [ ] Blacksmith Enchanting Table
    - [ ] Used to enchant weapons with experience / level
    - [ ] Enchant don't give anything, it's just a requirement for high level orders

## Ideas

- Check orders & Skills from a custom book (https://www.youtube.com/watch?v=j49ko74QkCU)
- Add a new Machine to craft basic Materials from resources
- Hire NPCs to collect resources 
- Add Axes
- Implement built-in Economy to avoid economy plugin dependency (use config option to enable/disable feature) 