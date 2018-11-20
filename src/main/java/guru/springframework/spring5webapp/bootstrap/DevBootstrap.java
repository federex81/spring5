package guru.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private AuthorRepository authorRepository;
	private BookRepository bookRepository;

	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	private void initData(){

		Author author1 = new Author("Maurizio", "Tardozzi");
		Book book1 = new Book("Spark", "1111", "Spark author");
		author1.getBooks().add(book1);
		book1.getAuthors().add(author1);

		authorRepository.save(author1);
		bookRepository.save(book1);


		Author author2 = new Author("Vincenzo", "Apuzzo");
		Book book2 = new Book("Spring", "2222", "Spring author");
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
