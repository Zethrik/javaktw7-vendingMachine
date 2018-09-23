package pl.sdacademy.vending.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Tray implements Serializable {

    public static final long serialVersionUID = 1L;

    private String symbol;
    private Long price;
    private Queue<Product> products;

    private Tray(Builder builder) {
        this.symbol = builder.symbol;
        this.price = builder.price;
        this.products = builder.products;
    }

    public static Builder builder(String symbol) {
        return new Builder(symbol);
    }

    public String getSymbol() {
        return symbol;
    }

    public long getPrice() {
        return price;
    }

    public Optional<String> firstProductName() {
        Product firstProduct = products.peek();

        return Optional.ofNullable(firstProduct)
                .map(Product::getName);
    }

    public Optional<Product> getFirstProduct() {
        return Optional.ofNullable(products.poll());
    }

    public boolean addProduct(Product product) {
        if (products.size() < 10) {
            products.add(product);
            return true;
        } else {
            return false;
        }
    }

    public static class Builder {
        private String symbol;
        private Long price;
        private Queue<Product> products;

        private Builder(String symbol) {
            if (symbol == null) {
                throw new IllegalArgumentException("Tray symbol cannot be null");
            }
            this.symbol = symbol;
            this.products = new LinkedList();
        }

        public Builder price(Long price) {
            this.price = price;
            return this;
        }

        public Builder products(Product product) {
            this.products.add(product);
            return this;
        }

        public Tray build() {
            if (price == null) {
                price = 990L;
            }
            return new Tray(this);
        }
    }

}
