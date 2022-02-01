public class Good {
    protected String name;
    protected double price;
    protected double rating;

    public Good (String name, double price, double rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }

    public void setPrice (int price) {
        this.price = price;
    }

    public double getPrice () {
        return price;
    }

    public void setRating (double rating) {
        this.rating = rating;
    }

    public double getRating () {
        return rating;
    }

    public String goodToString() {
        return String.format("%1$-12s %2$-8.1f %3$-5.1f", name, price, rating );
    }
}
