package app.service;

import app.domain.entities.Category;
import app.repository.CategoryRepository;
import app.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final static String CATEGORIES_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\BookshopSystem\\src\\main\\resources\\files\\categories.txt";
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        String[] categoryFileContent = this.fileUtil.getFileContent(CATEGORIES_FILE_PATH);
        for (String currentLine: categoryFileContent) {
            Category category = new Category();
            category.setName(currentLine);

            this.categoryRepository.saveAndFlush(category);
        }
    }
}
