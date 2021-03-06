package jp.co.who.spring_tutorial.api.jpa.bookmark.repository;

import jp.co.who.spring_tutorial.api.jpa.bookmark.domain.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {

    List<BookCategory> findAll();
}
