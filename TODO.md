# TODO List

## Project

- [X] Kotlin
- [X] Setup git repo
- [ ] implement Vault API to get economy
- [ ] Add system to save data for player (MongoDB / MySQL)

## Features

- [X] NPC "Blacksmith master"
    - [X] Spawn with command
    - [X] Disable AI
    - [X] Disable damage
    - [X] Mobs can't target NPC (EntityTargetEvent)

- [ ] Blacksmith Master menu
    - [X] "Remove" button to remove NPC
    - [ ] Display player money
    - [ ] Add Machines Shop button
        - [ ] Orders Sign - Free
        - [ ] Skills Bookshelf
        - [ ] Blacksmith Crafting Table
        - [ ] Blacksmith Anvil
        - [ ] Blacksmith Enchanting Table
    - [ ] Add Materials Shop button
        - [ ] provide materials to craft weapons

- [ ] Orders Sign 
    - [ ] Based on Sign
    - [ ] Show list of orders based on player blacksmith level
    - [ ] Giving order items give money to the player
    - [ ] Order can contains multiple items

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