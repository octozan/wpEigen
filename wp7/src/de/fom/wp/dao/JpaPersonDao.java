package de.fom.wp.dao;

import java.util.List;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.fom.wp.controller.DaoException;
import de.fom.wp.persistence.Person;

@Transactional
@Alternative
public class JpaPersonDao implements PersonDao {

	@Inject
	private EntityManager manager;
	
	@Override
	public Person read(Integer id) throws DaoException {

		TypedQuery<Person> query = manager.createQuery("select p from Person p WHERE p.id = :id", Person.class);
		return query.setParameter("id", id).getSingleResult();
	}

	@Override
	public Person register(String name, String email, String passwort) throws DaoException{
		
		

		Person p = new Person();
		p.setEmail("jpainsert");
		p.setFirstname("jpaFirst");
		manager.persist(p);


		
		return p;
//		try (Connection c = ds.getConnection()) {
//			PreparedStatement pst = null;
//			if (person.getId() == null) {
//				final Random r = new SecureRandom();
//				byte[] salt = new byte[32];
//				byte[] pass = new byte[8];
//				r.nextBytes(salt);
//				r.nextBytes(pass);
//				String encodedSalt = Base64.getEncoder().encodeToString(salt);
//				String password = Base64.getEncoder().encodeToString(pass);
//				pst = c.prepareStatement(
//						"INSERT INTO person (firstname, lastname, email, birthday, gender, height, company_fid, comment, newsletter"
//								+ ", passphrase, passphrase_md5, passphrase_sha2, passphrase_sha2_salted, salt) VALUES (?,?,?,?,?,?,?,?,?,?, md5(?), sha2(?, 512), sha2(?, 512), ?)", Statement.RETURN_GENERATED_KEYS);
//				pst.setString(10, password);
//				pst.setString(11, password);
//				pst.setString(12, password);
//				pst.setString(13, password + encodedSalt);
//				pst.setString(14, encodedSalt);
//			}
//			pst.setString(1, person.getFirstname());
//			pst.setString(2, person.getLastname());
//			pst.setString(3, person.getEmail());
//			pst.setObject(4, person.getBirthday());
//			pst.setString(5, person.getGender().name());
//			if (person.getHeight() != null) {
//				pst.setDouble(6, person.getHeight());
//			} else {
//				pst.setNull(6, Types.INTEGER);
//			}
//			if (person.getCompanyid() != null) {
//				pst.setInt(7, person.getCompanyid());
//			} else {
//				pst.setNull(7, Types.INTEGER);
//			}
//			pst.setString(8, person.getComment());
//			pst.setInt(9, person.isNewsletter() ? 1 : 0);
//			pst.executeUpdate();
//			if(person.getId()==null){
//				// automatisch generierten Prim�rschl�ssel von DB auslesen
//				ResultSet rs = pst.getGeneratedKeys();
//				rs.next();
//				person.setId(rs.getInt(1));
//			}
//			
//			PreparedStatement pd = c.prepareStatement("delete from person_interest where person_fid = ?");
//			pd.setInt(1, person.getId());
//			pd.executeUpdate();
//			
//			PreparedStatement pi = c.prepareStatement("insert into person_interest "
//					+ "(interest_fid, person_fid) values (?,?)");
//			pi.setInt(2, person.getId());
//			for (Integer i : person.getInterests()) {
//				pi.setInt(1, i);
//				pi.executeUpdate();
//			}
//			
//			
//			
//		} catch (SQLException e) {
//			throw new DaoException(e.getMessage(), e);
//		}
	}

	@Override
	public void save(Person p) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> list() throws DaoException {
		return manager.createQuery("select p from Person p left join fetch p.company", Person.class).getResultList(); //Person geht auf die Klasse Person kein sql mehr
	}

	@Override
	public Person login(String email, String password) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePassword(String email, String oldpassword, String newpassword) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int checkEmail(String value, Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}


	
}
