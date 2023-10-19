public class NormalLocation extends Location{
    public NormalLocation(Player player,String locName) {
        super(player, locName);
    }

    @Override
    public boolean onLocation() {
        return true; // lokasyonda mı kontrolü / normal lokasyonlarda hiçbir zaman ölünmeyeceği için true döndürüyoruz
    }
}
