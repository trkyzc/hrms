package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@PrimaryKeyJoinColumn(name="user_id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name="candidates")
public class Candidate extends User {
	
	@Column(name= "first_name")
	private String firstName;
	
	@Column(name= "last_name")
	private String lastName;
	
	@Column(name= "national_id")
	private String nationalId;
	
	@Column(name= "date_of_birth")
	private LocalDate dateOfBirth;
	
	@Column(name = "is_verified", columnDefinition = "boolean default false")
	private boolean isVerified = false;
	
	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<School> schools;
	
	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<ProgrammingSkill> programingSkills;
	
	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<Link> links;
	
	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<ForeignLanguage> languages;
	
	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<Experience> experiences;
	
	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<CoverLetter> coverLetters;	

	@JsonIgnore
	@OneToOne(mappedBy = "candidate", optional=false, fetch=FetchType.LAZY)
	private Image image;

}
