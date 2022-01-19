package com.example.market.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

//    private final CategoryRepository categoryRepository;
//
//    public Category categoryRoot() {
//        Map<Long, List<Category>> categoryGroup = new HashMap<>();
//        for (Category c : categoryRepository.findAll()) {
//            Category categories = new Category(c.getCategoryId(), c.getCategoryName(), c.getParentId());
//            categoryGroup.computeIfAbsent(categories.getParentId(), k -> new ArrayList<>()).add(categories);
//        }
//
//        Category rootCategory = new Category(00l, "START", null);
//        addSubCategories(rootCategory, categoryGroup);
//
//        return rootCategory;
//    }
//
//    private void addSubCategories(Category parent, Map<Long, List<Category>> groupingByParentId) {
//        List<Category> subCategories = groupingByParentId.get(parent.getCategoryId());
//
//        if(subCategories == null) {
//            return;
//        }
//
//
//        for (Category s : subCategories) {
//            addSubCategories(s, groupingByParentId);
//        }
//    }

}
