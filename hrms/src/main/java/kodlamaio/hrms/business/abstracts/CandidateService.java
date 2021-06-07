package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.dtos.CandidateCVDto;

public interface CandidateService {
	
	Result add(Candidate candidate);
	Result update(Candidate candidate);
	Result delete(int id);
	DataResult<List<Candidate>> getAll();
	DataResult<Candidate> getCandidateByNationalId(String nationalId);
	DataResult<Candidate> getById(int id);
	DataResult<CandidateCVDto> getCandidateCVById(int id);
}
