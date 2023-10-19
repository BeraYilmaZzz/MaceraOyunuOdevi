public class SafeHouse extends NormalLocation{
    public SafeHouse(Player player) {
        super(player, "GÜVENLİ EV"); // kullanıcıdan almamıza gerek olmadığı için ve lokasyon adı sabit olduğu için kendimiz girdik
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli evdesiniz !");
        System.out.println("Canınız yenilendi ! ");
        this.getPlayer().setHealth(this.getPlayer().getOrjHealth());
        System.out.println("----------------------------------------------------------------------------------");
        if((this.getPlayer().getInventory().isWood() && this.getPlayer().getInventory().isFood() &&  this.getPlayer().getInventory().isWater()) == true){
            System.out.println("-* TEBRİKLER ADADAN KAÇMAYI BAŞARDINIZ *-");
            return false;
        }
        return true;
    }
}
