public class ToolStore extends NormalLocation {
    public ToolStore(Player player) {
        super(player, "Mağaza"); // kullanıcıdan almamıza gerek olmadığı için ve lokasyon adı sabit olduğu için kendimiz girdik
    }

    @Override
    public boolean onLocation() {
        System.out.println(" < Mağazaya Hoşgeldiniz ! > ");
        boolean showMenu = true;
        while (showMenu){
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
            System.out.println("3 - Çıkış yap");
            System.out.print("Seçiminiz = ");
            System.out.println("----------------------------------------------------------------------------------");
            int selectCase = Location.input.nextInt();
            while (selectCase < 1 && selectCase > 3) {
                System.out.println("Lütfen geçerli bir seçim yapınız !!!");
                selectCase = input.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("SELAMETLE, Yine bekleriz !");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    private void buyArmor() {
        System.out.println("Ne istiyorsun ?");

        int selectArmorID = Location.input.nextInt();

        while (selectArmorID < 0 && selectArmorID > Armor.armors().length) {

            System.out.println("Lütfen geçerli bir seçim yapınız !!!");
            selectArmorID = input.nextInt();
        }
        if(selectArmorID != 0 ){
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);
            if (selectedArmor != null){
                if( selectedArmor.getPrice() > this.getPlayer().getGold()){
                    System.out.println("YETERLİ PARANIZ BULUNMAMAKTADIR !!!");
                }else{
                    System.out.println(selectedArmor.getName() + " Zırhını satın aldınız ! HAYRINI GÖR YİĞİDİM ");
                    int balance = this.getPlayer().getGold() - selectedArmor.getPrice();
                    this.getPlayer().setGold(balance);
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Güncel bakiyeniz = " + this.getPlayer().getGold());

                }
            }
        }
    }

    private void printWeapon() {
        System.out.println("----- SİLAHLAR ----- ");

        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + " - " + w.getName() + " < Para : " + w.getPrice() + " Hasar:" + w.getDamage());
        }
        System.out.println("0 - Çıkış");
    }

    private void buyWeapon() {
        System.out.println("Ne istiyorsun ?");

        int selectWeaponID = Location.input.nextInt();

        while (selectWeaponID < 0 && selectWeaponID > Weapon.weapons().length) {

            System.out.println("Lütfen geçerli bir seçim yapınız !!!");
            selectWeaponID = input.nextInt();
        }
        if(selectWeaponID != 0 ){
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);

            if (selectedWeapon != null) {

                if (selectedWeapon.getPrice() > this.getPlayer().getGold()) {
                    System.out.println("YETERLİ PARANIZ BULUNMAMAKTADIR !!!");

                } else {

                    // Satın almanın gerçekleştiği alan
                    System.out.println(selectedWeapon.getName() + " silahını satın aldınız ! HAYRINI GÖR YİĞİDİM");
                    int balance = this.getPlayer().getGold() - selectedWeapon.getPrice();
                    this.getPlayer().setGold(balance);
                    System.out.println("Güncel bakiyeniz = " + this.getPlayer().getGold());
                    System.out.println("Önceki silahınız = " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Yeni silahınız = " + this.getPlayer().getInventory().getWeapon().getName());

                }
            }
        }
    }
    private void printArmor() {
        System.out.println("----- ZIRHLAR ----- ");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + " - " + a.getName() + " < Para : " + a.getPrice() + " Zırh :" + a.getBlock());
        }
        System.out.println("0 - Çıkış");
    }
}
