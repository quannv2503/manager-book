package controller;

import model.Book;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.BookService;
import service.CategoryService;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/category/list");
        Iterable<Category> categories = categoryService.findAll();
        modelAndView.addObject("categorys", categories);
        return modelAndView;
    }

    @GetMapping(value = "/create-category")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;

    }

    @PostMapping(value = "/create-category")
    public ModelAndView addCategory(@ModelAttribute("category") Category category) {
        ModelAndView modelAndView = new ModelAndView("/category/create");
        categoryService.save(category);
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message", "created");
        return modelAndView;
    }
}

