package microservices.book.multiplication.challenge;

public class Challenge {

	private int factorA;
	private int factorB;
	
	public Challenge() {
		this.factorA = 0;
		this.factorB = 0;
	}
	
	public Challenge(int factorA, int factorB) {
		this.factorA = factorA;
		this.factorB = factorB;
	}
	
	public int getFactorA() {
		return factorA;
	}
	public int getFactorB() {
		return factorB;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (obj == this) 
			return true;
		
		if (!(obj instanceof Challenge))
			return false;
		
		Challenge test = (Challenge) obj;
		return  this.factorA == test.getFactorA()
					&& this.factorB == test.getFactorB();
		
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.factorA;
		result = 31 * result + this.factorB;
		return result;
	}
}
