import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Good> shopList = new ArrayList<>();
        shopList.add(new Good("Колбаса", 150.0, 0.0));
        shopList.add(new Good("Сыр", 120.0, 0.0));
        shopList.add(new Good("Хлеб", 70.0, 0.0));

        Shop shop = new Shop(shopList);
        System.out.println(shop.shopListPrint());

        shop.buySmthng();
        shop.changeRating();
        System.out.println();
        System.out.println(shop.shopListPrint());



    }


}
