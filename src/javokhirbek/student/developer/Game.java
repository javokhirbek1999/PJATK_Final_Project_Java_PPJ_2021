package javokhirbek.student.developer;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private Scanner scanner = new Scanner(System.in);
    GameMap[][] gameMap = new GameMap[10][10];
    private String name;
    private Enemy enemy = new Enemy();
    private Player player = new Player();
    private Enemy[] enemies = new Enemy[40];
    private int x, y, initial_pos_x, initial_pos_y, total_enemies;
    private boolean enemy_appeared = false;
    private boolean place_appeared = false;
    private String[] place_names = {"Mountain", "River", "Greenery Field", "Cave", "Castle", "Shop"};
    private Place[] places = new Place[20];
    private Place place = new Place();

    public void setName() {
        System.out.print("Enter your name: ");
        name = scanner.next();
        player.setName(name);
    }

    public void checkEnemy() {
        for (int i = 0; i < enemies.length; i++) {
            if (gameMap[x][y].toString().equals(enemies[i].toString())) {
                enemy = enemies[i];
                enemy_appeared = true;
            } else {
                checkPlaces();
            }
        }
    }

    public void checkPlaces() {
        for (int i = 0; i<places.length; i++) {
            if (gameMap[x][y].toString().equals(places[i].toString())) {
                place = places[i];
                place_appeared = true;
            }
        }
    }

    public void enemyEncounterOption() {
        System.out.println(enemy.getName() + " appeared\n");
        System.out.println("1.\tBattle" +
                "\n2.\tShow Player Info" +
                "\n3.\tShow Enemy Info\n");
        int in = scanner.nextInt();
        switch (in) {
            case 1:
                battle();
                break;
            case 2:
                player.showPlayerInfo();
                enemyEncounterOption();
                break;
            case 3:
                enemy.showEnemyInfo();
                enemyEncounterOption();
                break;
            default:
                System.out.println("Please choose available options only");
                enemyEncounterOption();
                break;

        }
    }

    public void encounterPlaces() {
        System.out.println(" # You are in " + place.getName() + " # ");
        switch (place.getName()) {
            case "Mountain":
                System.out.println("\tDo you want to recover your health ?\n" +
                        "\t1. Yes\n" +
                        "\t2. No, I want to continue on my journey");
                int in = scanner.nextInt();
                if (in == 1) {
                    if (player.getHp() < 100) {
                        player.restoreHP();
                        System.out.println("Your health has been fully recovered\n" +
                                "Your HP: " + player.getHp());
                        startGame();
                    } else {
                        System.out.println("You do not need a recovery");
                        startGame();
                    }
                } else {
                    System.out.println(" # You are in " + place.getName() + " # ");
                    startGame();
                }
                break;
            case "Castle":
            case "Greenery Field":
            case "Cave":
                startGame(); // nothing to do there, keep going
                break;
            case "River":
                buildBride();
                break;
            case "Shop":
                merchantGreet();
                shopper();
                break;
            default:
                break;
        }
    }

    private void buildBride() {
        if (player.getResources() > 50) {
            gameMap[x][y] = new Place("Bridge");//build bride over the river if player has enough resources
            player.setResources(player.getResources()-50);// 50 resources are required to build a bridge
            startGame();
        } else {
            System.out.println("You do not have enough resources to build a bride so you cannot cross the river");
            startGame();
        }
    }

    private void shopper() {
        System.out.println("\t1. White Steel Sword (+5 Attack Damage) (500 Golds)\n" +
                "\t2. Silver Sword (+10 Attack Damage) (600 Golds)\n" +
                "\t3. Dragon Sword (+25 Attack Damage) (1,500 Golds)\n" +
                "\t4. Ripper's Sword (+50 Attack Damage) (2,000 Golds)\n" +
                "\t5. 30 HP Potion (300 Golds)\n" +
                "\t6. 50 HP Potion (500 Golds)\n" +
                "\t7. 100 HP Potion (800 Golds)\n" +
                "\t8. Resources (Wood) (100 Golds/100 Wood)" +
                "\t9. Thank you, I don't wanna buy anything\n");
        int in = scanner.nextInt();
        switch (in) {
            case 1:
                System.out.println("(You) Ok, I am buying that White Steel Sword");
                System.out.println("(Merchant) Do you have enough money to buy it, because it's gonna cost you 500 Golds ?");
                System.out.println("1. Yeah of course, I will buy it\n" +
                        "2. Nah, I don't have enough money");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        if (player.getMoney() >= 500) {
                            player.setMoney(player.getMoney()-500);
                            player.setPotentialAttackDamageDeal(player.getPotentialAttackDamageDeal() + 5);
                            System.out.println("Wow, I have never seen such an awesome sword before");
                            System.out.println("Your attack damage is upgraded (+ 5 Attack Damage)");
                        } else {
                            System.out.println("(Merchant, hey, you do not have enough money to buy this Sword, Hero)");
                            shopper();
                        }
                        break;
                    default:
                        System.out.println("\t(Merchant) Ok, maybe you will buy it next time");
                        shopper();
                        break;
                }
                break;
            case 2:
                System.out.println("(You) Ok, I am buying that Silver Sword");
                System.out.println("(Merchant) Do you have enough money to buy it, because it's gonna cost you 600 Golds ?");
                System.out.println("1. Yeah of course, I will buy it\n" +
                        "2. Nah, I don't have enough money");
                int choice2 = scanner.nextInt();
                switch (choice2) {
                    case 1:
                        if (player.getMoney() >= 600) {
                            player.setMoney(player.getMoney() - 600);
                            player.setPotentialAttackDamageDeal(player.getPotentialAttackDamageDeal() + 10);
                            System.out.println("Wow, I have never seen such an awesome sword before");
                            System.out.println("Your attack damage is upgraded (+ 10 Attack Damage)");
                        } else {
                            System.out.println("(Merchant, hey, you do not have enough money to buy this Sword, Hero)");
                            shopper();
                        }
                        break;
                    default:
                        System.out.println("\t(Merchant) Ok, maybe you will buy it next time");
                        shopper();
                        break;
                }
                break;
            case 3:
                System.out.println("(You) Ok, I am buying that Dragon Sword");
                System.out.println("(Merchant) Is that expensive for you, do you have enough money to buy it, because it's gonna cost you 1,500 Golds ?");
                System.out.println("1. Yeah of course, I will buy it\n" +
                        "2. Nah, I don't have enough money");
                int choice3 = scanner.nextInt();
                switch (choice3) {
                    case 1:
                        if (player.getMoney() >= 1500) {
                            player.setMoney(player.getMoney() - 1500);
                            player.setPotentialAttackDamageDeal(player.getPotentialAttackDamageDeal() + 25);
                            System.out.println("Wow, I have never seen such an awesome sword before");
                            System.out.println("Your attack damage is upgraded (+ 25 Attack Damage)");
                        } else {
                            System.out.println("(Merchant, hey, you do not have enough money to buy this Sword, Hero)");
                            shopper();
                        }
                        break;
                    default:
                        System.out.println("\t(Merchant) Ok, maybe you will buy it next time");
                        shopper();
                        break;
                }
                break;
            case 4:
                System.out.println("(You) Ok, I am buying that Ripper's Sword");
                System.out.println("(Merchant) Is that expensive for you, do you have enough money to buy it, because it's gonna cost you 2,000 Golds ?");
                System.out.println("1. Yeah of course, I will buy it\n" +
                        "2. Nah, I don't have enough money");
                int choice4 = scanner.nextInt();
                switch (choice4) {
                    case 1:
                        if (player.getMoney() >= 2000) {
                            player.setMoney(player.getMoney() - 2000);
                            player.setPotentialAttackDamageDeal(player.getPotentialAttackDamageDeal() + 50);
                            System.out.println("Wow, I have never seen such an awesome sword before");
                            System.out.println("Your attack damage is upgraded (+ 50 Attack Damage)");
                        } else {
                            System.out.println("(Merchant, hey, you do not have enough money to buy this Sword, Hero)");
                            shopper();
                        }
                        break;
                    default:
                        System.out.println("\t(Merchant) Ok, maybe you will buy it next time");
                        shopper();
                        break;
                }
                break;
            case 5:
                System.out.println("(You) Ok, I am buying that 30 HP Health Potion");
                System.out.println("(Merchant) Ok, it costs you 300 Golds ?");
                System.out.println("1. Ok, I will buy it\n" +
                        "2. Nah, I don't have enough money");
                int choice6 = scanner.nextInt();
                switch (choice6) {
                    case 1:
                        if (player.getMoney() >= 300) {
                            player.setMoney(player.getMoney() - 300);
                            player.setXp(player.getHp()+30);
                            System.out.println("I think I am feeling better now");
                            System.out.println("You got 30 HP");
                        } else {
                            System.out.println("(Merchant, I am sorry, I cannot sell you this HP potion because you do not have enough money, Hero)");
                            shopper();
                        }
                        break;
                    default:
                        System.out.println("\t(Merchant) Ok, take care, Hero");
                        shopper();
                        break;
                }
                break;
            case 6:
                System.out.println("(You) Ok, I am buying that 50 HP Health Potion");
                System.out.println("(Merchant) Ok, it costs you 500 Golds ?");
                System.out.println("1. Ok, I will buy it\n" +
                        "2. Nah, I don't have enough money");
                int choice7 = scanner.nextInt();
                switch (choice7) {
                    case 1:
                        if (player.getMoney() >= 500) {
                            player.setMoney(player.getMoney() - 500);
                            player.setXp(player.getHp()+50);
                            System.out.println("I think I am feeling better now");
                            System.out.println("You got 50 HP");
                        } else {
                            System.out.println("(Merchant, I am sorry, I cannot sell you this HP potion because you do not have enough money, Hero)");
                            shopper();
                        }
                        break;
                    default:
                        System.out.println("\t(Merchant) Ok, take care, Hero");
                        shopper();
                        break;
                }
                break;
            case 7:
                System.out.println("(You) Ok, I am buying that 30 HP Health Potion");
                System.out.println("(Merchant) Ok, it costs you 300 Golds ?");
                System.out.println("1. Ok, I will buy it\n" +
                        "2. Nah, I don't have enough money");
                int choice8 = scanner.nextInt();
                switch (choice8) {
                    case 1:
                        if (player.getMoney() >= 800) {
                            player.setMoney(player.getMoney() - 800);
                            player.restoreHP();
                            System.out.println("I think I am feeling better now");
                            System.out.println("Your HP has fully been restored");
                        } else {
                            System.out.println("(Merchant, I am sorry, I cannot sell you this HP potion because you do not have enough money, Hero)");
                            shopper();
                        }
                        break;
                    default:
                        System.out.println("\t(Merchant) Ok, take care, Hero");
                        shopper();
                        break;
                }
                break;
            case 8:
                System.out.println("(You) I need some resources");
                System.out.println("(Merchant) Ok, you have to pay 100 Golds per 100 Resources");
                System.out.println("\n1.\tOk, I will pay it");
                System.out.println("\n2.\tNo, I don't need it right now");
                System.out.print("> ");
                int input = scanner.nextInt();
                switch (input) {
                    case 1:
                        System.out.println("(Merchant) Awesome, how many resources do you need ?");
                        System.out.print("Enter amount: ");
                        int t = scanner.nextInt();
                        int total_price = 100 * t;
                        int number_of_resources_bought = 100*t;
                        if (player.getMoney() < total_price) {
                            System.out.println("(Merchant) You cannot afford it right now, Hero !");
                            shopper();
                        } else {
                            player.setMoney(player.getMoney()-total_price);
                            player.setResources(player.getResources() + number_of_resources_bought);
                            System.out.println("You have " + number_of_resources_bought + " amount of Resources");
                            shopper();
                        }
                        break;
                    case 2:
                        System.out.println("(Merchant) Ok, can buy it whenever you want, it will never goes out of stock, ha-ha-ha");
                        System.out.println("(You) Ok");
                        shopper();
                        break;
                    default:
                        shopper();
                }
            case 9:
                System.out.println("(Merchant)Ok, take care and I wish you luck on your journey");
                startGame();
        }

    }

    private void merchantGreet() {
        //Shopper greeting and welcoming the player
        System.out.println("Welcome Hero !!!\n" +
                "What do you want to buy ?\n");
    }

    private void battle() {
        int current_player_hp = player.getHp();
        int current_enemy_hp = enemy.getHp();
        boolean battle_is_over = false;
        while (true) {
                current_enemy_hp -= player.attack();
                current_player_hp -= enemy.enemy_attack();
                enemy.setHp(current_enemy_hp);
                player.setHp(current_player_hp);
                System.out.println("\nYou have attacked " + enemy.getName() + " for " + player.attack() + " HP");
                System.out.println("\nYou have taken " + enemy.enemy_attack() + " HP damage from " + enemy.getName());
                if (current_enemy_hp <= 0 && current_player_hp <= 0) {
                    gameOver();
                }
            if (current_enemy_hp <= 0) {
                total_enemies--; // decrease total number of enemies
                enemy.restoreEnemyHP();
                System.out.println(" # You killed " + enemy.getName() + " # \n");
                enemy_appeared = false;
                player.setMoney(player.getMoney() + 100); // player earns 50 golds for killing an enemy
                player.setXp(player.getXp()+80); // increase player's Experience
                player.setPoints(player.getPoints() + 150); // player earns points for killing an enemy
                player.setKill_count(player.increment_Kill_Count()); // increment kill count after player kills an enemy
                enemy.restoreEnemyHP();
                break;
            }
            if (current_player_hp <= 0) {
                System.out.println(" # You lost # \n");
                battle_is_over = true;
                gameOver();
            }
            if (battle_is_over) {
                break;
            } else {
                System.out.println();
                enemyEncounterOption();
            }
        }
        System.out.println();
        startGame();
        }

    private void gameOver() {
        System.out.println("\nGame Over !" +
                "\n1.\tRestart" +
                "\n2.\tExit Game");
        int in = scanner.nextInt();
        if (in == 1) {
            player.restoreHP();
            player.setResources(0);
            player.setMoney(player.initial_money);
            player.setKill_count(0);
            startGame();
        } else if (in == 2) {
            exitGame();
        }
    }

    private void exitGame() {
        System.out.println("Game Over!\n" +
                "Player Stats: \n");
        System.out.println("Name: " + player.getName() +
                "\nPoints: " + player.getPoints() +
                "\nXP: " + player.getXp() +
                "\nMoney: " + player.getMoney() +
                "\nEnemies Killed: " + player.getKill_count());
        System.out.println("\nThank you for playing!\n" +
                "Hope you enjoyed it!\n");
        System.out.println("================================\n" +
                "Developed By Javokhirbek Khaydaraliev\n" +
                "================================\n");
    }

    private void checkVictory() {
        if (total_enemies == 0) {
            System.out.println("\tVICTORY\n" +
                    "You killed all enemies\n" +
                    "\tYou Won !!!\n");
            gameOver();
        }
    }

    public void startGame() {
        checkVictory();
        System.out.println("Where do you want to move ?");
        System.out.println("Available Options: " +
                "\n1.\tNorth" +
                "\n2.\tNorth-West" +
                "\n3.\tSouth" +
                "\n4.\tSouth-East" +
                "\n5.\tWest" +
                "\n6.\tEast" +
                "\n7.\tPlayer Info");
        int input = scanner.nextInt();
        if (input == 1) {
            moveNorth();
        } else if (input == 2) {
            moveNorthWest();
        } else if (input == 3) {
            moveSouth();
        } else if (input == 4) {
            moveSouthEast();
        }else if (input == 5) {
            moveWest();
        } else if (input == 6) {
            moveEast();
        } else if (input == 7) {
            player.showPlayerInfo();
            startGame();
        }
        else {
            System.out.println("Wrong Direction");
            startGame();
        }
    }

    private void moveNorth() {
        try {
            initial_pos_x = x;
            initial_pos_y = y;
            x--;
            checkEnemy();
            gameMap[initial_pos_x][initial_pos_y] = new Place(place_names[2]);
            gameMap[x][y] = player;
            if (enemy_appeared) {
                enemyEncounterOption();
                startGame();
            } else {
                checkPlaces();
                if (place_appeared) {
                    encounterPlaces();
                } else {
                    startGame();
                }
                startGame();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You can't move further NORTH");
            x++;
            gameMap[x][y] = player;
            startGame();
        }
    }

    private void moveNorthWest() {
        try {
            initial_pos_x = x;
            initial_pos_y = y;
            x--;
            y--;
            checkEnemy();
            gameMap[initial_pos_x][initial_pos_y] = new Place(place_names[2]);
            gameMap[x][y] = player;
            if (enemy_appeared) {
                enemyEncounterOption();
                startGame();
            } else {
                checkPlaces();
                if (place_appeared) {
                    encounterPlaces();
                } else {
                    startGame();
                }
                startGame();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You can't move further NORTH-WEST");
            x++;
            y++;
            gameMap[x][y] = player;
            startGame();
        }
    }

    private void moveSouth() {
        try {
            initial_pos_x = x;
            initial_pos_y = y;
            x++;
            checkEnemy();
            gameMap[initial_pos_x][initial_pos_y] = new Place(place_names[2]);
            gameMap[x][y] = player;
            if (enemy_appeared) {
                enemyEncounterOption();
                startGame();
            } else {
                checkPlaces();
                if (place_appeared) {
                    encounterPlaces();
                } else {
                    startGame();
                }
                startGame();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You can't move further SOUTH");
            x--;
            gameMap[x][y] = player;
            startGame();
        }
    }

    private void moveSouthEast() {
        try {
            initial_pos_x = x;
            initial_pos_y = y;
            x++;
            y++;
            checkEnemy();
            gameMap[initial_pos_x][initial_pos_y] = new Place(place_names[2]);
            gameMap[x][y] = player;
            if (enemy_appeared) {
                enemyEncounterOption();
                startGame();
            } else {
                checkPlaces();
                if (place_appeared) {
                    encounterPlaces();
                } else {
                    startGame();
                }
                startGame();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You can't move further NORTH");
            x--;
            y--;
            gameMap[x][y] = player;
            startGame();
        }
    }

    private void moveWest() {
        try {
            initial_pos_x = x;
            initial_pos_y = y;
            y--;
            checkEnemy();
            gameMap[initial_pos_x][initial_pos_y] = new Place(place_names[2]);
            gameMap[x][y] = player;
            if (enemy_appeared) {
                enemyEncounterOption();
                startGame();
            } else {
                checkPlaces();
                if (place_appeared) {
                    encounterPlaces();
                } else {
                    startGame();
                }
                startGame();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You cannot move further West");
            y++;
            gameMap[x][y] = player;
            startGame();
        }
    }

    private void moveEast() {
        try {
            initial_pos_x = x;
            initial_pos_y = y;
            y++;
            checkEnemy();
            gameMap[initial_pos_x][initial_pos_y] = new Place(place_names[2]);
            gameMap[x][y] = player;
            if (enemy_appeared) {
                enemyEncounterOption();
                startGame();
            } else {
                checkPlaces();
                if (place_appeared) {
                    encounterPlaces();
                } else {
                    startGame();
                }
                startGame();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You cannot move further East");
            y--;
            gameMap[x][y] = player;
            startGame();
        }
    }

    public void placePlayer() {
        x = (int) (Math.random()* gameMap.length);
        y = (int) (Math.random()* gameMap.length);
        gameMap[x][y] = player;
    }

    public void populateEnemyArray() {
        //populate enemies randomly
        for (int i = 0; i<enemies.length; i++) {
            enemies[i] = enemy.generateRandomEnemy();
        }
    }

    public void placeEnemy() {
        populateEnemyArray();
        for (int j = 0; j < gameMap.length; j++) {
            for (int k = 0; k < gameMap[j].length/2; k++) {
                x = (int) (Math.random() * gameMap.length);
                y = (int) (Math.random() * gameMap[0].length);
                if (gameMap[x][y] == null) {
                    gameMap[x][y] = enemy.generateRandomEnemy();
                    total_enemies++;
                }
            }
        }
    }

    public void populatePlaces() {
        for (int i = 0; i<places.length; i++) {
            places[i] = new Place(place_names[(int)(Math.random()*place_names.length)]);
        }
    }

    public void placePlaces() {
        populatePlaces();
        int t = 3; // number of merchants
        for (int j = 0; j< gameMap.length; j++) {
            for (int k = 0; k<gameMap[j].length; k++) {
                x = (int) (Math.random() * gameMap.length);
                y = (int) (Math.random() * gameMap[0].length);
                if (gameMap[x][y] == null) {
                    while (t != 0) {
                        gameMap[x][y] = new Place(place_names[place_names.length-1]); // Add three shops into the map
                        t--;
                    }
                    gameMap[x][y] = new Place(place_names[(int) (Math.random()*place_names.length)]);
                }
                if (gameMap[j][k] == null) gameMap[j][k] = new Place(place_names[2]);
            }
        }
    }
}

