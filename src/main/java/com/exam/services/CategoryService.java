package com.exam.services;

import com.exam.models.exam.Category;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface CategoryService {

    //add a category
    public Category addCategory(Category category);

    //update already existing category
    public Category updateCategory(Category category);

    //fetch all categories
    public Set<Category> getCategories();

    //fetch specific category
    public Category getCategory(Long categoryId);

    //delete the category
    public void deleCategory(Long categoryId);

}
