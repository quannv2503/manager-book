package service;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import repository.BookRepository;

public interface BookService {


    Iterable<Book> fillAll();


    void delete(Long id);

    Iterable<Book> fillBookByName(String name);

    void save(Book book);
    Book findBook(Long id);
}
