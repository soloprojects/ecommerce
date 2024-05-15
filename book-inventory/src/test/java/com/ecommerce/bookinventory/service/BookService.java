package com.ecommerce.bookinventory.service;

import com.ecommerce.bookinventory.BookElasticsearchContainer;
import com.ecommerce.bookinventory.dto.BookDto;
import com.ecommerce.bookinventory.exception.BusinessException;
import com.ecommerce.bookinventory.model.BookModel;
import com.ecommerce.bookinventory.model.Genre;
import com.ecommerce.bookinventory.repository.BookRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.google.common.collect.Iterables;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookServiceTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ElasticsearchTemplate template;

    @Container
    private static final ElasticsearchContainer elasticsearchContainer = new BookElasticsearchContainer();

    @BeforeAll
    static void setUp() {
        elasticsearchContainer.start();
    }

    @BeforeEach
    void testIsContainerRunning() {
        assertTrue(elasticsearchContainer.isRunning());
        recreateIndex();
    }

    @Test
    void testFindBookById() throws BusinessException{
        BookModel book = bookService.save(createBook("12 rules for life", "Jordan Peterson", 2018, "978-0345816023", "Poetry"));
        BookModel result = bookService.findById(book.getId());
        assertNotNull(result);
        assertEquals("12 rules for life", result.getTitle());
        assertEquals("Jordan Peterson", result.getAuthor());
        assertEquals(2018, result.getYearOfPublication());
        assertEquals("978-0345816023", result.getIsbn_code());
        assertEquals("Poetry", result.getGenre());
    }

    @Test
    void testGetAllBooks() {
        bookService.save(createBook("12 rules for life", "Jordan Peterson", 2018, "978-0345816023", "Horror"));
        bookService.save(createBook("The Cathedral and the Bazaar", "Eric Raymond", 1999, "9780596106386", "Poetry"));
        Iterable<BookModel> books = bookService.findAll();

        assertNotNull(books);
        assertEquals(2, Iterables.size(books));
    }

    @Test
    void testSearch() throws IOException {
        bookService.save(createBook("12 rules for life", "Jordan Peterson", 2018, "978-0345816023", "Satire"));
        bookService.save(createBook("Rules or not rules?", "Jordan Miller", 2010, "978128000000", "Satire"));
        bookService.save(createBook("Poor economy", "Jordan Miller", 2006, "9781280789000", "Satire"));
        bookService.save(createBook("The Cathedral and the Bazaar", "Eric Raymond", 1999, "9780596106386", "Satire"));

        List<BookModel> books = bookService.search("rules");

        assertNotNull(books);
        assertEquals(2, books.size());
    }

    @Test
    void testCreateBook() throws BusinessException {
        BookModel createdBook = bookService.save(createBook("12 rules for life", "Jordan Peterson", 2024, "978-0345816023", "Poetry"));
        assertNotNull(createdBook);
        assertNotNull(createdBook.getId());
        assertEquals("12 rules for life", createdBook.getTitle());
        assertEquals("Jordan Peterson", createdBook.getAuthor());
        assertEquals(2024, createdBook.getYearOfPublication());
        assertEquals("978-0345816023", createdBook.getIsbn_code());
        assertEquals("Poetry", createdBook.getGenre());
    }

    @Test
    void testFindBookByIdThrowsExceptionIfCannotFindBook() {
        BookDto createBook = createBook("12 rules for life", "Jordan Peterson", 2000, "978-0345816023", "HORROR");

        assertThrows(BusinessException.class, () -> {
            bookService.findById("1A2B3C");
        });
    }

    private BookDto createBook(String title, String authorName, int publicationYear, String isbn, String genre) {
        BookDto book = new BookDto();
        book.setTitle(title);
        book.setAuthor(authorName);
        book.setYearOfPublication(publicationYear);
        book.setIsbn_code(isbn);
        book.setGenre(Genre.validateString(genre));
        return book;
    }

    private void recreateIndex() {
        if (template.indexOps(BookModel.class).exists()) {
            template.indexOps(BookModel.class).delete();
            template.indexOps(BookModel.class).create();
        }
    }

    @AfterAll
    static void destroy() {
        elasticsearchContainer.stop();
    }
}
