package microservices.book.multiplication.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String alias;

	public User() {
		this.id = 0L;
		this.alias = "";
	}

	public User(Long id, String alias) {
		this.id = id;
		this.alias = alias;
	}
	
	public User(final String userAlias) {
		this(null, userAlias);
	}

	public Long getId() {
		return id;
	}

	public String getAlias() {
		return alias;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (obj == this) 
			return true;
		
		if (!(obj instanceof User))
			return false;
		
		User test = (User) obj;
		return (this.id == null? test.getId() == null : this.id.equals(test.getId()))
					&& ( this.alias == null ? test.getAlias() == null : this.alias.equals(test.getAlias()) );
					
		
	}

	@Override
	public int hashCode() {
		int result = 17;
		if (this.id != null)
			result = 31 * result + this.id.hashCode();
		if (this.alias != null)
			result = 31 * result + this.alias.hashCode();
		return result;
	}
}
