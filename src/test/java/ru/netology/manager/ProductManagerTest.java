package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ProductManagerTest {

    ProductManager manager = new ProductManager();
    ru.netology.repository.ProductRepository ProductRepository;
    ProductManager manager1 = new ProductManager(ProductRepository);

    Product first = new Book(1, "First book", 100, "AuthorOne");
    Product second = new Book(2, "Second book", 1000, "AuthorTwo");
    Product third = new Smartphone(31, "First smartphone", 15000, "ManufacturerOne");
    Product fourth = new Smartphone(4, "Second smartphone", 15500, "ManufacturerTwo");

    @Test
    void add() {

        manager.add(first);

        manager.add(second);


        Product[] expected = new Product[]{second, first};
        Product[] actual = manager.getAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBy() {

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);

        Product[] expected = new Product[]{first, third};
        Product[] actual = manager.searchBy("First");

        assertArrayEquals(expected, actual);
    }

    @Test
    void getAll() {
        manager.add(first);
        manager.add(second);

        Product[] expected = new Product[]{second, first};
        Product[] actual = manager.getAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void matchesTrue() {
        manager.add(first);
        manager.add(second);

        manager.searchBy("First book");

        boolean expected = true;
        boolean actual = manager.matches(first, "First book");

        assertEquals(expected, actual);
    }

    @Test
    void matchesFalse() {
        manager.add(first);
        manager.add(second);

        manager.searchBy("Fifth book");

        boolean expected = false;
        boolean actual = manager.matches(first, "Fifth book");

        assertEquals(expected, actual);
    }

}