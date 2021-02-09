package javokhirbek.student.developer;

public class Player extends GameMap {
    private String name;
    private int hp;
    private int xp;
    private int points;
    private int resources;
    private double money;
    private int potentialAttackDamageDeal = 30; // initial potential attack damage level [increase this as player gains more XP]
    private int maxHP = 100;
    private int kill_count;
    public int initial_money = 150;

    public Player() {
        this.name = name;
        this.hp = 100;
        this.xp = 0;
        this.points = 0;
        this.resources = 0;
        this.money = initial_money;
    }

    public int getPotentialAttackDamageDeal() {
        return potentialAttackDamageDeal;
    }

    public void setPotentialAttackDamageDeal(int potentialAttackDamageDeal) {
        this.potentialAttackDamageDeal = potentialAttackDamageDeal;
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

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getKill_count() {
        return kill_count;
    }

    public void setKill_count(int kill_count) {
        this.kill_count = kill_count;
    }

    public int increment_Kill_Count() {
        this.kill_count++;
        return kill_count;
    }

    public void restoreHP() {
        this.hp = maxHP;
    }

    public int attack() {
        return (int) (Math.random()*potentialAttackDamageDeal);
    }

    public void showPlayerInfo() {
        System.out.println("Name:\t" + name +
                "\nHP:\t" + hp +
                "\nXP:\t" + xp +
                "\nPoints:\t" + points +
                "\nResources:\t" + resources +
                "\nMoney:\t" + money +
                "\nTotal Kills:\t" + kill_count +"\n");
    }



    @Override
    public String toString() {
        return name;
    }
}
