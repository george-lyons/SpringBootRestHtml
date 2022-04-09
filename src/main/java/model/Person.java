package model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter
@Setter
public class Person {
	String id;
	String firstName;
	String lastName;
	int    age;
}
