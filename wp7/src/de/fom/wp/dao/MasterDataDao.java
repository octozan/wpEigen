package de.fom.wp.dao;

import java.util.*;

import de.fom.wp.persistence.*;

public interface MasterDataDao {
	
	public List<Company> getAllCompanies();
	public List<Company> searchCompany(String query);
	public List<Interest> getAllInterests();
	public void save(Company company);

}
