public class Enemy {
    private String name ;
    private int id;
    private int damage;
    private int health;
    private int orijinalHealth;
    private int drop;
    public Enemy(String name ,int id, int damage, int health, int drop) {
        this.name=name;
        this.id = id;
        this.damage = damage;
        this.health = health;
        this.orijinalHealth= health;
        this.drop=drop;
    }
    public int getOrijinalHealth() {
        return orijinalHealth;
    }

    public void setOrijinalHealth(int orijinalHealth) {
        this.orijinalHealth = orijinalHealth;
    }

    public int getDrop() {
        return drop;
    }

    public void setDrop(int drop) {
        this.drop = drop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        if(health < 0 ){
            health = 0 ;
        }
        this.health = health;
    }
}
