import java.util.ArrayList;
import java.util.Random;

public class BattleLoc extends Location{
    private Enemy enemy;
    private String award;
    private int maxEnemy;
    private boolean isfirst;

    public BattleLoc(Player player, String locName, Enemy enemy, String award, int maxEnemy) {
        super(player, locName);
        this.enemy=enemy;
        this.award=award;
        this.maxEnemy= maxEnemy;
        this.isfirst=randomStart()==0;
    }

    private int randomStart() {
        Random rnd = new Random();
        return rnd.nextInt(2);
    }

    @Override
    public boolean onLocation() {
        if(this.getLocName().equals("Mağara")) {
            if (this.getPlayer().getInventory().isFood() == true) {
                System.out.println("Ödülü zaten kazandınız");
                return true;
            }
        }
        if(this.getLocName().equals("Orman")) {
            if (this.getPlayer().getInventory().isWood() == true) {
                System.out.println("Ödülü zaten kazandınız");
                return true;
            }
        }
        if(this.getLocName().equals("Nehir")) {
            if (this.getPlayer().getInventory().isWater() == true) {
                System.out.println("Ödülü zaten kazandınız");
                return true;
            }
        }
        int enemyNum = this.randomEnemyNumber();
        System.out.println("Şuan buradasınız :" + this.getLocName());
        System.out.println("DİKKATLİ OL Burada : " + enemyNum + " tane " + this.getEnemy().getName() + " yaşıyor ! ");
        System.out.println("Savaşmak için = s \nKaçmak için = k");
        String select = input.nextLine().toUpperCase();

        if(select.equals("S") && combat(enemyNum)){
            System.out.println(this.getLocName()+ " tüm düşmanları yendiniz !");
            if(this.award.equals("Food")) {
                this.getPlayer().getInventory().setFood(true);
            }
            if(this.award.equals("Wood")) {
                this.getPlayer().getInventory().setWood(true);
            }
            if(this.award.equals("Water")) {
                this.getPlayer().getInventory().setWater(true);
            }
            if(BattleLoc.this.getEnemy().getName().equals("Yılan")){
                randomItem();
            }
            System.out.println("*********");
            return true;
        }
        if(this.getPlayer().getHealth() <= 0 ){
            System.out.println("-ÖLDÜNÜZ-");
            return false;
        }
        return true;
    }
    public boolean combat(int enemyNumber) {
        boolean playerTurn = isfirst; // Oyuncunun ilk mi düşmanın mı sırası olduğunu belirlemek için bir değişken eklenir.
        for (int i = 1; i <= enemyNumber; i++) {
            this.getEnemy().setHealth(this.getEnemy().getOrijinalHealth());
            playerStats();
            enemyStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getEnemy().getHealth() > 0) {
                if (playerTurn) {
                    System.out.println("Vur == v \nKaç = k");
                    String selectChoice = input.nextLine().toUpperCase();
                    if (selectChoice.equals("V")) {
                        System.out.println("Vuruş yaptınız !");
                        enemy.setHealth(this.getEnemy().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    } else if (selectChoice.equals("K")) {
                        return false;
                    }
                    playerTurn = false; // Oyuncu hamlesini yaptıktan sonra sırayı düşmana geçirin.
                } else {
                    if (this.getEnemy().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Canavar size vuruş yaptı !");
                        int enemyDamage = this.getEnemy().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (enemyDamage < 0) {
                            enemyDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - enemyDamage);
                        afterHit();
                    }
                    playerTurn = true; // Düşman hamlesini yaptıktan sonra sırayı oyuncuya geçirin.
                }
            }
            if (this.getEnemy().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı yendiniz ! ");
                System.out.println("-----------------------------------");
                System.out.println(this.getEnemy().getDrop() + " kadar para kazandınız ! ");
                System.out.println("-----------------------------------");
                this.getPlayer().setGold(this.getPlayer().getGold() + this.getEnemy().getDrop());
                System.out.println("Güncel paranız : " + this.getPlayer().getGold());
                System.out.println("Kazanımlarınız = " + this.award);
                System.out.println("-----------------------------------");
            } else {
                return false;
            }
        }
        return true;
    }
    public void randomItem(){
        double random = Math.random() * 100;
        double secondRandom = Math.random() * 100;
        if (random < 15) {
            if (secondRandom < 20) {
                getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
                System.out.println("Tüfek Kazandınız !");
            } else if (secondRandom < 50) {
                getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));
                System.out.println("Kılıç Kazandınız !");
            } else {
                getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
                System.out.println("Tabanca Kazandınız !");
            }
        } else if (random < 30) {
            if (secondRandom < 20) {
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
                System.out.println("Ağır Zırh Kazandınız !");
            } else if (secondRandom < 50) {
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));
                System.out.println("Orta Zırh Kazandınız !");
            } else {
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
                System.out.println("Hafif Zırh Kazandınız !");
            }
        } else if (random < 55) {
            if (secondRandom < 20) {
                getPlayer().setGold(getPlayer().getGold() + 10);
            } else if (secondRandom < 50) {
                getPlayer().setGold(getPlayer().getGold() + 5);
            } else {
                getPlayer().setGold(getPlayer().getGold() + 1);
            }
        } else {
            System.out.println("Hiç item kazanamadınız !");
        }
    }
    private void afterHit() {
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println(this.getEnemy().getName() + " Canı " + this.getEnemy().getHealth());
        System.out.println("---------------------");
    }
    private void enemyStats(int i ) {
        System.out.println(i +  ". " + this.getEnemy().getName() + " Değerleri:");
        System.out.println("-----------------------------------");
        System.out.println("Sağlık: " + this.getEnemy().getHealth());
        System.out.println("Hasar: " + this.getEnemy().getDamage());
        System.out.println("Drop: " + this.getEnemy().getDrop());
        System.out.println();
    }

    private void playerStats() {
        System.out.println("Oyuncu değerleri :");
        System.out.println("-----------------------------------");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.print("Silah: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println(" - Hasarı: " + this.getPlayer().getTotalDamage());
        System.out.print("Zırh: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println(" - gücü: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para:"+ this.getPlayer().getGold());
        System.out.println();
    }
    public int randomEnemyNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxEnemy())+1;
    }

    public int getMaxEnemy() {
        return maxEnemy;
    }

    public void setMaxEnemy(int maxEnemy) {
        this.maxEnemy = maxEnemy;
    }
    public Enemy getEnemy() {
        return enemy;
    }
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
