import interfaces.Buyer;
import interfaces.RatingChanger;
import interfaces.ShopList;
import interfaces.WishList;

import java.util.*;

public class Shop implements ShopList, WishList, RatingChanger, Buyer {                                   //Interface segregation principle
    protected List<Good> shopList;
    protected Map<Good, Integer> wishList;

    public Shop(List<Good> shopList) {
        this.shopList = shopList;
        wishList = new HashMap<Good, Integer>();
    }

    public String shopListPrint() { //DRY
        if (shopList.size() == 0) {
            return "В магазине нет товаров";
        } else {
            System.out.println("Товары в магазине:");
            System.out.println(String.format("%1$-12s %2$-8s %3$-5s", "Название", "Цена", "Рейтинг"));
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < shopList.size(); i++) {                                                 //Magic numbers
                builder.append(shopList.get(i).goodToString());
                if (i != shopList.size() - 1) {
                    builder.append("\n");
                }
            }
            return builder.toString();
        }
    }

    public void buySmthng() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Вы хотите что-то из этого купить (Да/Нет?)");
            String answer1 = scanner.nextLine();
            if (answer1.equals("Нет")) {
                if (wishList.isEmpty()) {
                    System.out.println("Вы ничего не купили");
                } else {
                    System.out.println(printWishList());
                }
                break;
            } else if (answer1.equals("Да")) {
                System.out.println("Выберите товар из списка (в формате 'Наименование Количество (через пробел)')");
                String answer2 = scanner.nextLine();
                String[] arr = answer2.split(" ");
                if (shopListCheck(arr[0])) {
                    if (wishList.containsKey(shopListGet(arr[0]))) {
                        int k = wishList.get(shopListGet(arr[0])) + Integer.parseInt(arr[1]);
                        wishList.put(shopListGet(arr[0]), k);
                    } else {
                        wishList.put(shopListGet(arr[0]), Integer.parseInt(arr[1]));
                    }
                } else {
                    System.out.println("Нет такого товара");
                }
            } else
                System.out.println("Не понял, повторите");
        }
    }

    public boolean shopListCheck(String s) {
        boolean existance = false;
        for (int i = 0; i < shopList.size(); i++) {                                                     //Magic numbers
            if (s.equals(shopList.get(i).name)) {
                existance = true;
                break;
            }
        }
        return existance;
    }

    public Good shopListGet(String s) {
        Good good = null;
        for (int i = 0; i < shopList.size(); i++) {                                                     //Magic numbers
            if (s.equals(shopList.get(i).name)) {
                good = shopList.get(i);
                break;
            }
        }
        return good;
    }

    public String printWishList() {
        double total = 0;
        String k;
        StringBuilder builder = new StringBuilder("Ваши товары:\n");
        Iterator<Map.Entry<Good, Integer>> iterator = wishList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Good, Integer> entry = iterator.next();
            String name = entry.getKey().name;
            int amount = entry.getValue();
            total = total + entry.getKey().price * amount;
            builder.append(String.format("%1$-12s %2$-4d %3$-6.1f", name, amount, entry.getKey().price * amount));
            builder.append("\n");
        }
        k = builder.toString();
        k = k + "Итоговая сумма: " + total + " рублей";
        return k;
    }

    public void changeRating() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Хотите оставить отзыв о купленных товарах (Да/Нет)?");
        String answer1 = sc.nextLine();
        if ("Да".equals(answer1)) {
            Iterator<Map.Entry<Good, Integer>> iterator = wishList.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Good, Integer> entry = iterator.next();
                String name = entry.getKey().name;
                System.out.println("Что вы думаете о продукте '" + name + "'? Введите число от 1 до 5");
                try {
                    while (true) {
                        String answer2 = sc.nextLine();
                        double k = Double.parseDouble(answer2);
                        if (k < 1 || k > 5) {
                            System.out.println("Введите число от 1 до 5");
                        } else {
                            entry.getKey().setRating(k);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Спасибо за отзыв");
        } else {
            System.out.println("Спасибо за покупки");
        }
    }
}





