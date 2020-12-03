package service;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CategoryRepository;

public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository repository;

    public Iterable<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Category category) {
        repository.save(category);
    }
}
