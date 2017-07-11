package de.fom.wp.persistence;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@OneToMany(mappedBy="company") //mappedby geht auf das Attribut company in Person
	private Set<Person> arbeiter;

	public Company() {

	}

	public Company(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Company(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
