package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.adapters.UserCheckService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.verification.VerificationService;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class AuthManager implements AuthService {
	
	 private UserCheckService userCheckService;
	 private UserService userService;
	 private CandidateService candidateService;
	 private EmployerService employerService;
	 private VerificationService verificationService;
	 private VerificationCodeService verificationCodeService;
	 
	 @Autowired
	 public AuthManager(UserCheckService userCheckService, UserService userService ,CandidateService candidateService, EmployerService employerService,
             VerificationService verificationService, VerificationCodeService verificationCodeService) {
		super();
		this.userCheckService = userCheckService;
		this.userService = userService;
		this.candidateService = candidateService;
		this.employerService = employerService;
		this.verificationService = verificationService;
		this.verificationCodeService=verificationCodeService;
	}
	
	@Override
	public Result registerEmployer(Employer employer, String confirmPassword) {
		if (!eMailRequired(employer.getEmail())) {

			return new ErrorResult("Invalid email address.");
		}
		
		if (!checkIfEmailExists(employer.getEmail())) {

			return new ErrorResult(employer.getEmail() + " already exists.");
		}
		
		if (!checkIfEqualPasswordAndConfirmPassword(employer.getPassword(), confirmPassword)) {

			return new ErrorResult("Passwords do not match.");
		}
		
		return new SuccessResult("Registration has been successfully completed");
	}

	@Override
	public Result registerCandidate(Candidate candidate, String confirmPassword) {
		if (!checkIfExistsTcNo(candidate.getNationalId())) {

			return new ErrorResult(candidate.getNationalId() + " already exists.");
		}
		
		if (!checkIfEmailExists(candidate.getEmail())) {

			return new ErrorResult(candidate.getEmail() + " already exists.");
		}
		
		if (checkIfRealPerson(Long.parseLong(candidate.getNationalId()), candidate.getFirstName(),
				candidate.getLastName(), candidate.getDateOfBirth()) == false) {
			return new ErrorResult("TCKN could not be verified.");
		}
		
		return new SuccessResult("Registration has been successfully completed");
	}
	
	public static boolean eMailRequired(String eMail) {
		
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		 
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(eMail);
		
		return matcher.matches();
	}
	
	private boolean checkIfEmailExists(String email) {

		if (this.userService.getUserByEmail(email).getData() == null) {

			return true;
		}

		return false;
	}
	
	private boolean checkIfEqualPasswordAndConfirmPassword(String password, String confirmPassword) {

		if (!password.equals(confirmPassword)) {
			return false;
		}

		return true;
	}
	
	private boolean checkIfExistsTcNo(String nationalId) {

		if (this.candidateService.getCandidateByNationalId(nationalId).getData() == null) {
			return true;
		}
		return false;
	}
	
	private boolean checkIfRealPerson(long nationalId, String firstName, String lastName, LocalDate dateOfBirth) {

		if (this.userCheckService.checkIfRealPerson(nationalId, firstName, lastName, dateOfBirth)) {
			return true;
		}
		return false;
	}
	
	

}
