package com.works.querydsl;

import com.querydsl.core.types.Predicate;

import java.util.Optional;


public interface QuerydslPredicateExecutor<T> {

    Optional<T> queryFindById(Predicate predicate);

    Iterable<T> queryFindAll(Predicate predicate);

    long queryCount(Predicate predicate);

    boolean queryExists(Predicate predicate);

}