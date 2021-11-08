package se.blacklemon.repository;

import se.blacklemon.model.Classification.Classification;
import se.blacklemon.model.library.Book;

import java.util.List;

public interface BookRepository {
    void saveBook(Book book);
    Book getBook(String bookId);
    List<Book> getAllBookList();
    List<Book> getBookListFromCategory(Classification Classification);
}
