package ru.netology.repository;

import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;

public class ProductRepository {

    private Product[] items = new Product[0];

    public void save(Product item) {
        int lenght = items.length + 1;
        Product[] tmp = new Product[lenght];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public Product[] findAll() {
        return items;
    }

    public Product findById(int id) {
        for (Product item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public Product findByPrice(int price) {
        for (Product item : items) {
            if (item.getPrice() == price) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) {

        if (findById(id) == null) {
            throw new NotFoundException(id);

        }
        int lenght = items.length - 1;
        Product[] tmp = new Product[lenght];
        int index = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }


}
