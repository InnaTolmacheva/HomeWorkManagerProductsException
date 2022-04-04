package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository repo = new ProductRepository();

    private Book book1 = new Book(1, "First book", 100, "AutorOne");
    private Book book2 = new Book(2, "Second book", 1000, "FutorTwo");
    private Smartphone smartphone1 = new Smartphone(31, "First smartphone", 15000, "ManufacturerOne");
    private Smartphone smartphone2 = new Smartphone(4, "Second smartphone", 15500, "ManufacturerTwo");

    @Test
    public void save() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);

        Product[] expected = new Product[]{book1, book2, smartphone1, smartphone2};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void findAll() {
        repo.save(book1);
        repo.save(book2);

        repo.save(smartphone1);

        Product[] expected = new Product[]{book1, book2, smartphone1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void findById() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);

        repo.findById(2);

        Product expected = book2;
        Product actual = repo.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    void findByIdWhenNoId() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);

        repo.findById(68);

        Product actual = repo.findById(68);
        assertEquals(null, actual);
    }

    @Test
    void findByPrice() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);

        repo.findByPrice(1000);

        Product expected = book2;
        Product actual = repo.findByPrice(1000);
        assertEquals(expected, actual);
    }

    @Test
    void findByPriceWhenNoPrice() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);

        repo.findByPrice(10);

        Product actual = repo.findByPrice(10);
        assertEquals(null, actual);
    }

    @Test
    void removeById() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);


        repo.removeById(31);

        Product[] expected = new Product[]{book1, book2, smartphone2};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByIdWhenNoId() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);

        assertThrows(NotFoundException.class, () -> {
            repo.removeById(32);
        });

        Product[] expected = new Product[]{book1, book2, smartphone1, smartphone2};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }
}