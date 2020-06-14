package com.springproject.library.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springproject.library.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
