package service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


import org.springframework.stereotype.Service;

import model.Person;

@Service
public class PersonService {
	ArrayList<Person> persons = new ArrayList<Person>();
	public PersonService() {
		Person p = new Person();
		p.setId("1");
		p.setAge(21);
		p.setFirstName("Steve");
		p.setLastName("Smith");
		persons.add(p);

		p = new Person();
		p.setId("2");
		p.setAge(25);
		p.setFirstName("Tom");
		p.setLastName("Brown");
		persons.add(p);
		ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
		scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				byte[] array = new byte[7]; // length is bounded by 7
				new Random().nextBytes(array);
				String generatedString = new String(array, Charset.forName("UTF-8"));
				final Person finalP1 = new Person();

				int age = ThreadLocalRandom.current().nextInt(0,100);
				finalP1.setAge(age);
				finalP1.setFirstName(generatedString);
				finalP1.setLastName(generatedString);
				finalP1.setId(generatedString);

				addPerson(finalP1);

			}
		}, 0, 10, TimeUnit.SECONDS);
	}

	public void addPerson(Person p) {
		System.out.println("adding person " + p.getFirstName());
		persons.add(p);
	}

	public Person getPerson(String id) {
		for(Person person:persons) {
			if(person.getId().equalsIgnoreCase(id)) return person;
		}
	    return null;
	}
	public ArrayList<Person> getAll() {
		return persons;
	}
}

