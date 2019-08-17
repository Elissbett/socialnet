package com.ebolotina.socialnet;

import com.ebolotina.socialnet.controller.MessageController;
import com.ebolotina.socialnet.model.Community;
import com.ebolotina.socialnet.model.Message;
import com.ebolotina.socialnet.model.Post;
import com.ebolotina.socialnet.model.User;
import com.ebolotina.socialnet.repository.CommunityRepository;
import com.ebolotina.socialnet.repository.MessageRepository;
import com.ebolotina.socialnet.repository.PostRepository;
import com.ebolotina.socialnet.repository.UserRepository;
import com.ebolotina.socialnet.service.MessageService;
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
		SpringApplication.run( SocialnetApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase(
	        UserRepository repository,
            MessageRepository messageRepository,
            PostRepository postRepository,
            CommunityRepository communityRepository,
            MessageService messageService) {
		return args -> {

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			User julio = user().withFirstName("Julio").withSecondName("Antonio")
					.withDateOfBirth(dateFormat.parse("01/03/1993"))
					.withCreatedDate(new Date(System.currentTimeMillis()))
					.withLogin("julio")
					.withPassword("julio")
					.build();
			User jorge = user().withFirstName("Jorge").withSecondName("Sanches")
					.withDateOfBirth(dateFormat.parse("25/05/1998"))
					.withCreatedDate(new Date(System.currentTimeMillis()))
					.withLogin("jorge")
					.withPassword("jorge")
					.build();
			User carmelita = user().withFirstName("Carmelita").withSecondName("Rosario")
					.withDateOfBirth(dateFormat.parse("01/03/1980"))
					.withCreatedDate(new Date(System.currentTimeMillis()))
					.withLogin("carmelita")
					.withPassword("carmelita")
					.build();
            repository.save(julio);
            repository.save(carmelita);
            repository.save(jorge);

            julio.setFriends(new HashSet<User>() {{add(carmelita);}});
			//carmelita.addToFriends(julio);
			repository.save(julio);
			repository.save(carmelita);

			messageService.createMessage(julio, carmelita, "Hey there");

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
