package se.blacklemon.model.library.book;

import se.blacklemon.model.library.Classification.Classification;

import static se.blacklemon.utils.UniqueIdGenerator.createUniqueId;

public class BookBuilder<SELF extends BookBuilder<SELF>> {

    protected Book book = new Book();

    public SELF withUniqueId() {
        book.setId(createUniqueId());
        return self();
    }

    public SELF withIsbn(String isbn) {
        book.setIsbn(isbn);
        return self();
    }

    public SELF withTitle(String title) {
        book.setTitle(title);
        return self();
    }

    public SELF withAuthor(String author) {
        book.setAuthor(author);
        return self();
    }

    public SELF withPages(int pages) {
        book.setPages(pages);
        return self();
    }

    public SELF withClassification(Classification classification) {
        book.setClassification(classification);
        return self();
    }

    public Book build() {
        return book;
    }

    protected SELF self() {
        return (SELF) this;
    }
}
