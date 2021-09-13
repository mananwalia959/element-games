package com.shop.elementgames;

import java.math.BigDecimal;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.elementgames.models.Product;
import com.shop.elementgames.models.User;
import com.shop.elementgames.repository.ProductRepository;
import com.shop.elementgames.repository.UserRepository;

@Component
public class DataSeeder {
	
	@Autowired private ProductRepository productRepository;
	@Autowired private UserRepository userRepository;
	
	@PostConstruct
	void seed() {
		Product product1 = new Product();
		product1.setId(UUID.fromString("e9582513-0d04-42ed-9faf-622a04b83d54"));
		product1.setName("God of War");
		product1.setDescription("In God of War, players control Kratos, a Spartan warrior who is sent by the Greek gods to kill Ares, the god of war. As the story progresses, Kratos is revealed to be Ares' former servant, who had been tricked into killing his own family and is haunted by terrible nightmares." );
		product1.setPrice(new BigDecimal("18.99"));
		product1.setImage("https://static.wikia.nocookie.net/godofwar/images/a/a7/God_of_War_4_cover.jpg/revision/latest?cb=20170614023116");
		
		productRepository.save(product1);
		
		Product product2 = new Product();
		product2.setId(UUID.fromString("32bcfa9d-5857-4d5b-b3fb-c1b2a728e3a0"));
		product2.setName("Read Dead Redemption");
		product2.setDescription("America, 1899. The end of the wild west era has begun as lawmen hunt down the last remaining outlaw gangs. Those who will not surrender or succumb are killed. After a robbery goes badly wrong in the western town of Blackwater, Arthur Morgan and the Van der Linde gang are forced to flee. With federal agents and the best bounty hunters in the nation massing on their heels, the gang must rob, steal and fight their way across the rugged heartland of America in order to survive. As deepening internal divisions threaten to tear the gang apart, Arthur must make a choice between his own ideals and loyalty to the gang who raised him. From the creators of Grand Theft Auto V and Red Dead Redemption, Red Dead Redemption 2 is an epic tale of life in America at the dawn of the modern age");
		product2.setPrice(new BigDecimal("18.99"));
		product2.setImage("https://pbs.twimg.com/media/EF4k1KNXkAEg-4o.jpg");
		
		productRepository.save(product2);
		
		
		User user = new User();
		
		user.setProfileId(UUID.fromString("3ea53969-8dec-4dd8-9c85-da0531d38e89"));
		user.setEmail("abc@gmail.com");
		user.setName("abc");
		//translates to 12345
		user.setEncodedPassword("$2a$10$KQk./ciekRp/Srf/1RjUvOTT7OBD3zklVfZmM4EqV7V2ymGSMq6AK");
		user.setAdminAccount(false);
		
		userRepository.save(user);
		
		

		
	}
}
