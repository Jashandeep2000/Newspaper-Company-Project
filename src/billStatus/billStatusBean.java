package billStatus;

public class billStatusBean {

	String Mobile,DOStart,SNewspapers,SPrice,DOEnd;
	float Bill;
	int Status;
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getDOStart() {
		return DOStart;
	}
	public void setDOStart(String dOStart) {
		DOStart = dOStart;
	}
	public String getDOEnd() {
		return DOEnd;
	}
	public void setDOEnd(String dOEnd) {
		DOEnd = dOEnd;
	}
	public float getBill() {
		return Bill;
	}
	public void setBill(float bill) {
		Bill = bill;
	}
	public billStatusBean(String mobile, String dOStart,String dOEnd, float bill) {
		super();
		Mobile = mobile;
		DOStart = dOStart;
		DOEnd = dOEnd;
		Bill = bill;
		
	}
	
	public billStatusBean()
	{
		
	}
	
}
