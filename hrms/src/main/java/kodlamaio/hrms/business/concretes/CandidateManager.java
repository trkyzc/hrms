package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.ExperienceService;
import kodlamaio.hrms.business.abstracts.ForeignLanguageService;
import kodlamaio.hrms.business.abstracts.ImageService;
import kodlamaio.hrms.business.abstracts.LinkService;
import kodlamaio.hrms.business.abstracts.ProgrammingSkillService;
import kodlamaio.hrms.business.abstracts.SchoolService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.dtos.CandidateCVDto;

@Service
public class CandidateManager implements CandidateService {
	
	private CandidateDao candidateDao;
	private ExperienceService experienceService;
	private ForeignLanguageService foreignLanguageService;
	private ImageService imageService;
	private LinkService linkService;
	private ProgrammingSkillService programmingSkillService;
	private SchoolService schoolService;
	
	public CandidateManager(CandidateDao candidateDao, ExperienceService experienceService,
			ForeignLanguageService foreignLanguageService, ImageService imageService, LinkService linkService,
			ProgrammingSkillService programmingSkillService, SchoolService schoolService) {
		super();
		this.candidateDao = candidateDao;
		this.experienceService = experienceService;
		this.foreignLanguageService = foreignLanguageService;
		this.imageService = imageService;
		this.linkService = linkService;
		this.programmingSkillService = programmingSkillService;
		this.schoolService = schoolService;
	}

	@Autowired
	public CandidateManager(CandidateDao candidateDao) {
		super();
		this.candidateDao = candidateDao;
	}

	@Override
	public Result add(Candidate candidate) {
		
		this.candidateDao.save(candidate);
		return new SuccessResult("Candidate has been added.");
		
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		
		return new SuccessDataResult<List<Candidate>> (this.candidateDao.findAll(),"Candidates has been listed.");
	}

	@Override
	public Result update(Candidate candidate) {
		this.candidateDao.save(candidate);
		return new SuccessResult("Candidate has been updated.");
	}

	@Override
	public Result delete(int id) {
		this.candidateDao.deleteById(id);
		return new SuccessResult("Candidate has been deleted.");
	}

	@Override
	public DataResult<Candidate> getCandidateByNationalId(String nationalId) {
		return new SuccessDataResult<Candidate>(this.candidateDao.findCandidateByNationalId(nationalId));
	}

	@Override
	public DataResult<Candidate> getById(int id) {
		return new SuccessDataResult<Candidate>(this.candidateDao.getById(id));
	}

	@Override
	public DataResult<CandidateCVDto> getCandidateCVById(int id) {
		Candidate candidate = this.candidateDao.getById(id);
		CandidateCVDto cv = new CandidateCVDto();
		cv.experiences = candidate.getExperiences();
		cv.languages = candidate.getLanguages();
		cv.image = candidate.getImage();
		cv.links = candidate.getLinks();
		cv.programingSkills = candidate.getProgramingSkills();
		cv.schools = candidate.getSchools();
		return new SuccessDataResult<CandidateCVDto>(cv);
	}

}
