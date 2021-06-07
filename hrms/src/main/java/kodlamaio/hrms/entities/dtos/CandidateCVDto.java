package kodlamaio.hrms.entities.dtos;

import java.util.List;

import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Experience;
import kodlamaio.hrms.entities.concretes.ForeignLanguage;
import kodlamaio.hrms.entities.concretes.Image;
import kodlamaio.hrms.entities.concretes.Link;
import kodlamaio.hrms.entities.concretes.ProgrammingSkill;
import kodlamaio.hrms.entities.concretes.School;

public class CandidateCVDto {
	
	public Candidate candidate;
	public List<School> schools;
	public List<ProgrammingSkill> programingSkills;
	public List<Link> links;
	public List<ForeignLanguage> languages;
	public List<Experience> experiences;
	public Image image;
	//public List<CoverLetter> coverLetters;

}
