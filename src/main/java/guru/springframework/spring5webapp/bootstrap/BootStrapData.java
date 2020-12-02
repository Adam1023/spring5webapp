package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Author & Book
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1312213");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Jognson");
        Book noEJB = new Book("J2EE Development without EJB", "35626526");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());

        // Publisher
        Publisher publisher1 = new Publisher("Adam", "Anderlecht", "Brussels", "1070");

        publisherRepository.save(publisher1);

        System.out.println(publisher1.toString());

        ddd.setPublisher(publisher1);
        publisher1.getBooks().add(ddd);
        noEJB.setPublisher(publisher1);
        publisher1.getBooks().add(noEJB);

        publisherRepository.save(publisher1);


        System.out.println("Publisher nb of books: " + publisher1.getBooks().size());
    }
}
