import java.util.Scanner;

public class Player {
    private int damage ; //Player dan nesne üretirken değerleri oyunun içerisinden alacağımız için constructera bunu eklemiyoruz
    private int health ; //Player dan nesne üretirken değerleri oyunun içerisinden alacağımız için constructera bunu eklemiyoruz
    private int gold ; //Player dan nesne üretirken değerleri oyunun içerisinden alacağımız için constructera bunu eklemiyoruz
    private String characterName; //Player dan nesne üretirken değerleri oyunun içerisinden alacağımız için constructera bunu eklemiyoruz
    private String name;
    private Inventory inventory;
    private int orjHealth;
    private boolean isRiver;
    private boolean isCave;
    private Scanner input = new Scanner(System.in);
    public Player(String name){
        this.name=name;
        this.inventory= new Inventory();
    }
    public void selectCharacter(){
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};

        System.out.println("----------------------------------------------------------------------------------");
        for(GameChar gameChar : charList) {
            System.out.println("ID:"+ gameChar.getId() +
                    "\t Karakter :" + gameChar.getName() +
                    "\t\t hasar:" + gameChar.getDamage() +
                    "\t\t Sağlık:" + gameChar.getHealth() +
                    "\t\t Para:" + gameChar.getGold());
        }
        System.out.println("----------------------------------------------------------------------------------");
        System.out.print("Karakter seçiniz = ");
        int selectChar = input.nextInt();
        switch (selectChar){
            case 1 :
                initPlayer(new Samurai());
                break;
            case 2 :
                initPlayer(new Archer());
                break ;
            case 3 :
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Karakterinizi "+ this.getCharacterName() + " seçtiniz !! \nKARAKTERİNİZİN ÖZELLİKLERİ = \nHASAR:"+this.getDamage()+"\nSAĞLIK:"+this.getHealth()+"\nPARA:"+this.getGold());
    }
    public void initPlayer(GameChar gamechar){
        this.setDamage(gamechar.getDamage());
        this.setHealth(gamechar.getHealth());
        this.setOrjHealth(gamechar.getHealth());
        this.setGold(gamechar.getGold());
        this.setCharacterName(gamechar.getName());
    }
    public void printInfo(){
        System.out.println("---------------------------------------");
        System.out.println("Silahınız =  " + this.getInventory().getWeapon().getName()+
                " || Zırhınız =  " + this.getInventory().getArmor().getName()+
                " || Zırh gücünüz =  " + this.getInventory().getArmor().getBlock()+
                ", || Hasarınız: "+ this.getTotalDamage()+
                ", || Sağlığınız: "+ this.getHealth()+
                ", || Paranız: "+ this.getGold());
        System.out.println("---------------------------------------");

    }
    public int getTotalDamage (){
        return damage + this.getInventory().getWeapon().getDamage();
    }
    public int getDamage(){
        return damage ;
    }
    public void setDamage(int damage){
        this.damage= damage;
    }
    public int getHealth(){
        return this.health;
    }
    public void setHealth(int health){
        this.health= health;
    }
    public int getGold(){
        return this.gold;
    }
    public void setGold(int gold){
        this.gold= gold;
    }
    public String getCharacterName(){
        return characterName;
    }
    public void setCharacterName(String name){
        this.characterName=name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public int getOrjHealth() {
        return orjHealth;
    }
    public void setOrjHealth(int orjHealth) {
        this.orjHealth = orjHealth;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean getIsRiver() {
        return isRiver;
    }
    public boolean setIsRiver(boolean isRiver) {
        this.isRiver = isRiver;
        return isRiver;
    }
}
