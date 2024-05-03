package io.quarkus.ts.hibernate.reactive.database;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Entity
@Table(name = "books")
@NamedQuery(name = "find_by_title_prefix", query = "from Book where title like :prefix")
public class Book extends PanacheEntityBase {
    private static final int MAX_TITLE_LENGTH = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @NotNull
    @Size(max = MAX_TITLE_LENGTH)
    private String title;

    @NotNull
    private Integer author;

    @Convert(converter = ISBNConverter.class)
    private long isbn;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public static Multi<Book> all() {
        return Book.<Book> listAll().toMulti().flatMap(l -> Multi.createFrom().items(l.stream()));
    }

    public static Uni<Book> byId(Integer id) {
        return findById(id);
    }

    public static Uni<Book> create(Integer author, String name) {
        Book book = new Book(name);
        book.setAuthor(author);
        return book.persistAndFlush();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull @Size(max = MAX_TITLE_LENGTH) String getTitle() {
        return title;
    }

    public void setTitle(@NotNull @Size(max = MAX_TITLE_LENGTH) String title) {
        this.title = title;
    }

    public @NotNull Integer getAuthor() {
        return author;
    }

    public void setAuthor(@NotNull Integer author) {
        this.author = author;
    }

    public long getISBN() {
        return isbn;
    }

    public void setISBN(long isbn) {
        this.isbn = isbn;
    }
}
