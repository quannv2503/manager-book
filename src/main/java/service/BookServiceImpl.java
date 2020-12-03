package service;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import repository.BookRepository;

public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public Iterable<Book> fillAll() {
        return bookRepository.findAll();
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book findBook(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public Iterable<Book> fillBookByName(String name) {
        return bookRepository.findBooksByNameContaining(name);
    }

}
