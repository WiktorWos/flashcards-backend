package com.wiktor.wos.flashcards.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wiktor.wos.flashcards.dto.browser.SharedSetsBrowserRequest;
import com.wiktor.wos.flashcards.entity.QSet;
import com.wiktor.wos.flashcards.entity.Set;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class CustomSetRepositoryImpl implements CustomSetRepository {
    @PersistenceContext
    private EntityManager em;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    void createQueryFactory() {
        queryFactory = new JPAQueryFactory(em);
    }

    private final QSet set = QSet.set;
    @Override
    public List<Set> browse(SharedSetsBrowserRequest request) {
        var query = queryFactory.selectFrom(set);
        if(request.getLanguage() != null) {
            var languageSearch = request.getLanguage().getSearch();
            if (StringUtils.isNotBlank(languageSearch)) {
                query.where(set.language.like(languageSearch + "%"));
            }
        }

        if (request.getCategory() != null) {
            var categorySearch = request.getCategory().getSearch();
            if (StringUtils.isNotBlank(categorySearch)) {
                query.where(set.category.like(categorySearch));
            }
        }

        if (request.getCreateDate() != null) {
            var createDateSearchFrom = request.getCreateDate().getSearch();
            var createDateSearchTo = request.getCreateDate().getSearchTo();
            if (createDateSearchFrom != null) {
                query.where(set.createDate.after(createDateSearchFrom));
                if (createDateSearchTo != null) {
                    query.where(set.createDate.before(createDateSearchTo));
                }
            }
        }
//        if(request != null) {
//            where(query, request);
//            orderBy(query, request);
//            limit(query, request);
//        }
        return query.fetch();
    }

    private void where(JPAQuery<Set> query, SharedSetsBrowserRequest request) {
        if(request.getLanguage() != null) {
            var languageSearch = request.getLanguage().getSearch();
            if (StringUtils.isNotBlank(languageSearch)) {
                query.where(set.language.eq(languageSearch));
            }
        }

        if (request.getCategory() != null) {
            var categorySearch = request.getCategory().getSearch();
            if (StringUtils.isNotBlank(categorySearch)) {
                query.where(set.category.like(categorySearch));
            }
        }

        if (request.getCreateDate() != null) {
            var createDateSearchFrom = request.getCreateDate().getSearch();
            var createDateSearchTo = request.getCreateDate().getSearchTo();
            if (createDateSearchFrom != null) {
                query.where(set.createDate.after(createDateSearchFrom));
                if (createDateSearchTo != null) {
                    query.where(set.createDate.before(createDateSearchTo));
                }
            }
        }
    }

    private void orderBy(JPAQuery<Set> query, SharedSetsBrowserRequest request) {
        var language = request.getLanguage();
        if (language != null) {
            if (language.isSort()) {
                var orderSpecifier = language.isAsc() ? set.language.asc() : set.language.desc();
                query.orderBy(orderSpecifier);
            }
        }

        var category = request.getCategory();
        if (category != null) {
            if (category.isSort()) {
                var orderSpecifier = category.isAsc() ? set.category.asc() : set.category.desc();
                query.orderBy(orderSpecifier);
            }
        }

        var createDate = request.getCreateDate();
        if (createDate != null) {
            if (createDate.isSort()) {
                var orderSpecifier = createDate.isAsc() ? set.createDate.asc() : set.createDate.desc();
                query.orderBy(orderSpecifier);
            }
        }
    }

    private void limit(JPAQuery<Set> query, SharedSetsBrowserRequest request) {
        var offset = request.getLimit() * request.getPage();
        query.offset(offset).limit(request.getLimit());
    }
}
