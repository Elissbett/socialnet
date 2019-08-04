package com.ebolotina.socialnet;

import com.ebolotina.socialnet.model.Community;
import com.ebolotina.socialnet.model.Message;
import com.ebolotina.socialnet.model.Post;
import com.ebolotina.socialnet.model.User;
import com.ebolotina.socialnet.repository.CommunityRepository;
import com.ebolotina.socialnet.repository.MessageRepository;
import com.ebolotina.socialnet.repository.PostRepository;
import com.ebolotina.socialnet.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import static com.ebolotina.socialnet.model.Community.community;
import static com.ebolotina.socialnet.model.Message.message;
import static com.ebolotina.socialnet.model.Post.post;
import static com.ebolotina.socialnet.model.User.user;

@SpringBootApplication
public class SocialnetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialnetApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase(
	        UserRepository repository,
            MessageRepository messageRepository,
            PostRepository postRepository,
            CommunityRepository communityRepository) {
		return args -> {

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			User julio = user().withFirstName("Julio").withSecondName("Antonio")
					.withDateOfBirth(dateFormat.parse("01/03/1993"))
					.withCreatedDate(new Date(System.currentTimeMillis()))
					.build();
			User jorge = user().withFirstName("Jorge").withSecondName("Sanches")
					.withDateOfBirth(dateFormat.parse("25/05/1998"))
					.withCreatedDate(new Date(System.currentTimeMillis()))
					.build();
			User carmelita = user().withFirstName("Carmelita").withSecondName("Rosario")
					.withDateOfBirth(dateFormat.parse("01/03/1980"))
					.withCreatedDate(new Date(System.currentTimeMillis()))
					.build();
            repository.save(julio);
            repository.save(carmelita);
            repository.save(jorge);

            julio.setFriends(new HashSet<User>() {{add(carmelita);}});
			//carmelita.addToFriends(julio);
			repository.save(julio);
			repository.save(carmelita);

			Message message = message().withText("Hey there").withOwner(julio).withParty(carmelita)
                    .withCreatedDate(new Date(System.currentTimeMillis())).withOutgoing(true).build();
            Message message1 = message().withText("Hey there").withOwner(carmelita).withParty(jorge)
                    .withCreatedDate(new Date(System.currentTimeMillis())).withOutgoing(false).build();
            messageRepository.save(message);
            messageRepository.save(message1);

            Post post = post().withAuthor(carmelita).withText("Hi everybody!")
                    .withCreatedDate(new Date(System.currentTimeMillis())).build();

            postRepository.save(post);

            Community community = community().withName("Big group").withDescription("The first one")
                    .withMembers(new HashSet<User>() {{add(carmelita); add(jorge);}})
                    .withAdmins(new HashSet<User>() {{add(carmelita);}})
                    .withCreatedDate(new Date(System.currentTimeMillis()))
                    .withFeed(new HashSet<Post>() {{add(post);}}).build();

            communityRepository.save(community);
		};
	}

}
