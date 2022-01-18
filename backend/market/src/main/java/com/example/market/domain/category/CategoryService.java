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

    private final CategoryRepository categoryRepository;

    public Categories categoryRoot() {
        Map<Long, List<Categories>> categoryGroup = new HashMap<>();
        for (Categories c : categoryRepository.findAll()) {
            Categories categories = new Categories(c.getCategoryId(), c.getCategoryName(), c.getParentId());
            categoryGroup.computeIfAbsent(categories.getParentId(), k -> new ArrayList<>()).add(categories);
        }

        Categories rootCategory = new Categories(0l, "ROOT", null);
        addSubCategories(rootCategory, categoryGroup);

        return rootCategory;
    }

    private void addSubCategories(Categories parent, Map<Long, List<Categories>> groupingByParentId) {
        List<Categories> subCategories = groupingByParentId.get(parent.getCategoryId());

        if(subCategories == null) {
            return;
        }

        parent.setSubCategories(subCategories);

        for (Categories s : subCategories) {
            addSubCategories(s, groupingByParentId);
        }
    }
}
