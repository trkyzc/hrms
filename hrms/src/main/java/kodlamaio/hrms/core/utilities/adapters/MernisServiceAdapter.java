package kodlamaio.hrms.core.utilities.adapters;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.MernisService.FakeMernisService;

@Service
public class MernisServiceAdapter implements UserCheckService {

	@Override
	public boolean checkIfRealPerson(long nationalityId, String firstName, String lastName, LocalDate dateOfBirth) {
		
		FakeMernisService client= new FakeMernisService();
		boolean result = client.TCKimlikDogrula(nationalityId, firstName, lastName, dateOfBirth);
		
		return result;
		
	}

}
