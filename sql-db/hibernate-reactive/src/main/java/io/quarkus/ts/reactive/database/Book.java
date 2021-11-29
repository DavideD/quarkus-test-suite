package io.quarkus.ts.reactive.database;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Entity
@Table(name = "books")
@NamedQuery(name = "find_by_title_prefix", query = "from Book where title like :prefix")
public class Book extends PanacheEntityBase {
    private static final int MAX_TITLE_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @NotNull
    @Size(max = MAX_TITLE_LENGTH)
    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author", nullable = false)
    private Author author;

    @Convert(converter = ISBNConverter.class)
    private long isbn;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public static Multi<Book> all() {
        return streamAll();
    }

    public static Uni<Book> byId(Integer id) {
        return findById(id);
    }

    public static Uni<Book> create(Author author, String name) {
        Book book = new Book(name);
        book.setAuthor(author);
        author.getBooks().add(book);
        return book.persistAndFlush();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public long getISBN() {
        return isbn;
    }

    public void setISBN(long isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Book book = (Book) o;
        return isbn == book.isbn && title.equals( book.title );
    }

    @Override
    public int hashCode() {
        return Objects.hash( title, isbn );
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder( "Book{" );
        sb.append( "id=" ).append( id );
        sb.append( ", title='" ).append( title ).append( '\'' );
        sb.append( ", author=" ).append( author );
        sb.append( ", isbn=" ).append( isbn );
        sb.append( '}' );
        return sb.toString();
    }
}
