package it.aulab.progetto_blog;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.repositories.AuthorRepository;

//@SpringBootTest
@DataJpaTest 
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProgettoBlogApplicationTests {



	@Autowired
	AuthorRepository authorRepository;
	@BeforeEach
	void load() {
		Author a1 = new Author();
		a1.setName("Mario");
		a1.setSurname("Rossi");
		a1.setEmail("rossi@mario.it");
		authorRepository.save(a1);
	}

	@Test
	void contextLoads() {
	}
    
	@Test
	void findByName() {
		assertThat(authorRepository.findByName("Mario"))
		.extracting("name")
		.containsOnly("Mario");
	}

	@Test
void sameNameAuthor() {
    assertThat(authorRepository.authorsWithSameName())
        .extracting("name")
        .containsOnly("Mario");
}

@Test
void sameNameAuthorNonNative() {
    assertThat(authorRepository.authorsWithSameNameNonNative())
        .extracting("name")
        .containsOnly("Mario");
}
}
