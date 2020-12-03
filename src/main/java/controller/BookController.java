package controller;

import model.Book;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.BookService;
import service.CategoryService;

import javax.persistence.ManyToOne;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/books/list");
        Iterable<Book> books = bookService.fillAll();
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping(value = "/create-book")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("/books/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;

    }

    @PostMapping(value = "/create-book")
    public ModelAndView addBook(@ModelAttribute("book") Book book) {
        ModelAndView modelAndView = new ModelAndView("/books/create");
        bookService.save(book);
        modelAndView.addObject("book", new Book());
        modelAndView.addObject("message", "created");
        return modelAndView;
    }

    @GetMapping(value = "/edit-book/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/books/edit");
        Book book = bookService.findBook(id);
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @PostMapping(value = "/edit-book")
    public ModelAndView editBook(@ModelAttribute("book") Book book) {
        ModelAndView modelAndView = new ModelAndView("/books/edit");
        bookService.save(book);
        modelAndView.addObject("book", book);
        modelAndView.addObject("message", "book updated successfully");
        return modelAndView;
    }

    @GetMapping(value = "/delete-book/{id}")
    public ModelAndView showDelete(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/books/delete");
        Book book = bookService.findBook(id);
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @PostMapping("/delete-book")
    public String delete(@ModelAttribute("book") Book book) {
        bookService.delete(book.getId());
        return "redirect:/books/";
    }

    @GetMapping(value = "/search")
    public ModelAndView search(@RequestParam("search") String name) {
        ModelAndView modelAndView = new ModelAndView("/books/list");
        Iterable<Book> books = bookService.fillBookByName(name);
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categorys")
    public Iterable<Category> findAll() {
        return categoryService.findAll();
    }
}
