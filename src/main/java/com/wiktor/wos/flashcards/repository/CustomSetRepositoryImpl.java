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
        if(request != null) {
            query = where(query, request);
            query = orderBy(query, request);
            query = limit(query, request);
        }
        return query.fetch();
    }

    private JPAQuery<Set> where(JPAQuery<Set> query, SharedSetsBrowserRequest request) {
        if(request.getLanguage() != null) {
            var languageSearch = request.getLanguage().getSearch();
            if (StringUtils.isNotBlank(languageSearch)) {
                query = query.where(set.language.like(languageSearch + "%"));
            }
        }

        if (request.getCategory() != null) {
            var categorySearch = request.getCategory().getSearch();
            if (StringUtils.isNotBlank(categorySearch)) {
                query = query.where(set.category.like(categorySearch + "%"));
            }
        }

        if (request.getCreateDate() != null) {
            var createDateSearchFrom = request.getCreateDate().getSearch();
            var createDateSearchTo = request.getCreateDate().getSearchTo();
            if (createDateSearchFrom != null) {
                query = query.where(set.createDate.after(createDateSearchFrom));
                if (createDateSearchTo != null) {
                    query = query.where(set.createDate.before(createDateSearchTo));
                }
            }
        }
        return query;
    }

    private JPAQuery<Set> orderBy(JPAQuery<Set> query, SharedSetsBrowserRequest request) {
        var language = request.getLanguage();
        if (language != null) {
            if (language.isSort()) {
                var orderSpecifier = language.isAsc() ? set.language.asc() : set.language.desc();
                query = query.orderBy(orderSpecifier);
            }
        }

        var category = request.getCategory();
        if (category != null) {
            if (category.isSort()) {
                var orderSpecifier = category.isAsc() ? set.category.asc() : set.category.desc();
                query = query.orderBy(orderSpecifier);
            }
        }

        var createDate = request.getCreateDate();
        if (createDate != null) {
            if (createDate.isSort()) {
                var orderSpecifier = createDate.isAsc() ? set.createDate.asc() : set.createDate.desc();
                query = query.orderBy(orderSpecifier);
            }
        }
        return query;
    }

    private JPAQuery<Set> limit(JPAQuery<Set> query, SharedSetsBrowserRequest request) {
        var offset = request.getLimit() * (request.getPage() - 1);
        return query.offset(offset).limit(request.getLimit());
    }
}
