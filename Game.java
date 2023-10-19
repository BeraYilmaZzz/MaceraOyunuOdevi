import java.util.Scanner;
public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Macera Oyununa Hoşgeldiniz ");
        System.out.print("Lütfen bir isim giriniz = ");
        String playerName = input.nextLine();
        Player newPlayer = new Player(playerName);
        System.out.println("Sayın " + newPlayer.getName() + " Tristan da Cunha adası'na Hoşgeldiniz !! \n'Dünyanın kara parçalarına en uzak adası'");
        System.out.println(newPlayer.getName() + " - Heeeeey !! Kimse yok muuuu !! ??? ");
        System.out.println(" ''Adada gezinmek için lütfen bir karakter seçiniz.'' ");
        newPlayer.selectCharacter();

        Location location = null;
        while (true) {
            newPlayer.printInfo();
            System.out.println();
            System.out.println("******* Bölgeler *******");
            System.out.println();
            System.out.println("1 - Güvenli ev --> Düşman yok, evinizdesiniz.");
            System.out.println("2 - Market --> Karakteriniz için eşya satın alabilirsiniz.");
            System.out.println("3 - Mağara --> Mağara'ya girer.");
            System.out.println("4 - Orman --> Orman'a girer.");
            System.out.println("5 - Nehir --> Nehir'e girer.");
            System.out.println("6 - Maden --> Maden'e girer. ");
            System.out.println("0 - Çıkış --> Oyunu sonlandırır");
            System.out.println();
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz =");
            int selectLoc = input.nextInt();
            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(newPlayer);
                    break;
                case 2:
                    location = new ToolStore(newPlayer);
                    break;
                case 3:
                    location = new Cave(newPlayer);
                    break;
                case 4:
                    location = new Forest(newPlayer);
                    break;
                case 5:
                    location = new River(newPlayer);
                    break;
                case 6:
                    location = new Coal(newPlayer);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz !!!");
            }
            if (location == null) {
                System.out.println(newPlayer.getName() + " Uykuya daldı ");
                break;
            }
            if (!location.onLocation()) {
                System.out.println("-Oyun Sona Erdi-");
                break;
            }
        }
    }
}

