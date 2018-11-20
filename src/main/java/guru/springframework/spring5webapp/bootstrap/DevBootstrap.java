package guru.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;

	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	private void initData(){
		
		Publisher pub1 = new Publisher();
		pub1.setName("Fede");
		publisherRepository.save(pub1);

		
		Author author1 = new Author("John", "Smith");
		Book book1 = new Book("Spark", "1111", pub1);
		author1.getBooks().add(book1);
		book1.getAuthors().add(author1);
		authorRepository.save(author1);
		bookRepository.save(book1);


		Author author2 = new Author("Jona", "Smitha");
		Book book2 = new Book("Spring", "2222", pub1);
		author2.getBooks().add(book2);
		book2.getAuthors().add(author2);
		authorRepository.save(author2);
		bookRepository.save(book2);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
	}

}
