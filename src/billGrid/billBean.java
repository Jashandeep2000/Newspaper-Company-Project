package billGrid;

public class billBean {

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
	/*public String getSNewspapers() {
		return SNewspapers;
	}
	public void setSNewspapers(String sNewspapers) {
		SNewspapers = sNewspapers;
	}
	public String getSPrice() {
		return SPrice;
	}
	public void setSPrice(String sPrice) {
		SPrice = sPrice;
	}*/
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
	/*public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}*/
	public billBean(String mobile, String dOStart,String dOEnd, float bill) {
		super();
		Mobile = mobile;
		DOStart = dOStart;
		DOEnd = dOEnd;
		Bill = bill;
		
	}
	
	public billBean()
	{
		
	}
	
}
