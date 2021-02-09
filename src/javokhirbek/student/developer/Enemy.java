package javokhirbek.student.developer;

public class Enemy extends GameMap{

    private String name;
    private int hp;
    private static int damage_level;
    private static int max_attack_damage;
    private String[] enemy_types = {"Ogre", "Troll", "Skeleton", "Wolf"};
    private int rand_index;


    public Enemy(String name, int hp, int damage_level) {
        this.name = name;
        this.hp = hp;
        this.damage_level = damage_level;
    }

    public Enemy() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage_level() {
        return damage_level;
    }

    public void setDamage_level(int damage_level) {
        this.damage_level = damage_level;
    }

    public int getMax_attack_damage() {
        return max_attack_damage;
    }

    public void setMax_attack_damage(int max_attack_damage) {
        this.max_attack_damage = max_attack_damage;
    }

    public String[] getEnemy_types() {
        return enemy_types;
    }

    public void setEnemy_types(String[] enemy_types) {
        this.enemy_types = enemy_types;
    }

    public int enemy_attack() {
        return (int) (Math.random() * max_attack_damage);
    }

    public void restoreEnemyHP() {
        this.setHp((int) (Math.random()*50));
    }

    public Enemy generateRandomEnemy() {
        rand_index = (int) (Math.random() * enemy_types.length);
        switch (enemy_types[rand_index]) {
            case "Skeleton":
                this.max_attack_damage = 10;
                hp = (int) (Math.random() * (20 - 10 + 1) + 10); // min skeleton hp = 20 and min hp = 10
                this.damage_level = (int) (Math.random() * max_attack_damage); // min damage = 0 and max damage = 10
                break;
            case "Wolf":
                this.max_attack_damage = 8;
                this.hp = (int) (Math.random() * (25 - 15 + 1) + 15);
                this.damage_level = (int) (Math.random() * max_attack_damage); // wolf's max damage: 8
                break;
            case "Troll":
                this.max_attack_damage = 25;
                this.hp = (int) (Math.random() * (40 - 25 + 1) + 25); // min Troll hp = 40 and min hp = 25
                this.damage_level = (int) (Math.random() * max_attack_damage); // troll's max damage = 25;
                break;
            default:
                this.max_attack_damage = 40;
                this.hp = (int) (Math.random() * (100 - 50 + 1) + 50); // min Ogre hp = 50
                this.damage_level = (int) (Math.random() * max_attack_damage); // Ogre's min damage level = 40
                break;
        }
        return new Enemy(enemy_types[rand_index], hp, damage_level);
    }

    public void showEnemyInfo() {
        System.out.println("Type:\t" + this.name +
                "\nHP:\t" + this.hp +
                "\nMax Attack Damage:\t" +  this.max_attack_damage);
    }

    @Override
    public String toString() {
        return name;
    }
}
