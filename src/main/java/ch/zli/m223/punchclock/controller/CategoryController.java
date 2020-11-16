package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The Category endpoint.
 * GET: {URL}/categories - To see all categories
 * DELETE: {URL}/categories/{id} - To delete a category by the category-id
 * PUT: {URL}/categories/{id} - To update a category by the category-id
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

    @PutMapping(value = "/{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable Long id){
        category.setId(id);
        return categoryService.updateCategory(category);
    }
}
