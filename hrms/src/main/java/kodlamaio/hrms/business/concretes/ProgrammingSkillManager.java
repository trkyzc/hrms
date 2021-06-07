package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ProgrammingSkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ProgrammingSkillDao;
import kodlamaio.hrms.entities.concretes.ProgrammingSkill;

@Service
public class ProgrammingSkillManager implements ProgrammingSkillService {
	
	private ProgrammingSkillDao programmingSkillDao;
	
	
	@Autowired
	public ProgrammingSkillManager(ProgrammingSkillDao programmingSkillDao) {
		super();
		this.programmingSkillDao = programmingSkillDao;
	}

	@Override
	public Result add(ProgrammingSkill programmingSkill) {
		this.programmingSkillDao.save(programmingSkill);
		return new SuccessResult("Programming skill has been added.");
	}

	@Override
	public Result update(ProgrammingSkill programmingSkill) {
		this.programmingSkillDao.save(programmingSkill);
		return new SuccessResult("Programming skill has been updated.");
	}

	@Override
	public Result delete(int id) {
		this.programmingSkillDao.deleteById(id);
		return new SuccessResult("Programming skill has been deleted.");
	}

	@Override
	public DataResult<ProgrammingSkill> getById(int id) {
		return new SuccessDataResult<ProgrammingSkill>(this.programmingSkillDao.getById(id));
	}

	@Override
	public DataResult<List<ProgrammingSkill>> getAll() {
		return new SuccessDataResult<List<ProgrammingSkill>>(this.programmingSkillDao.findAll());
	}

	@Override
	public DataResult<List<ProgrammingSkill>> getAllByCandidateId(int id) {
		return new SuccessDataResult<List<ProgrammingSkill>>(this.programmingSkillDao.getAllByCandidate_id(id));
	}

}
