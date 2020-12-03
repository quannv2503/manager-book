package service;

import model.Category;

public interface CategoryService {
    Iterable<Category> findAll();
    void save(Category category);
}
