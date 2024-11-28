package customerDetails;

public class customerDetailsBean {

	String Mobile,CName,Address,City,DOStart,SNewspapers,SPrice;

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getCName() {
		return CName;
	}

	public void setCName(String cName) {
		CName = cName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getDOStart() {
		return DOStart;
	}

	public void setDOStart(String dOStart) {
		DOStart = dOStart;
	}

	public String getSNewspapers() {
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
	}

	public customerDetailsBean(String mobile, String cName, String address, String city, String dOStart,
			String sNewspapers, String sPrice) {
		super();
		Mobile = mobile;
		CName = cName;
		Address = address;
		City = city;
		DOStart = dOStart;
		SNewspapers = sNewspapers;
		SPrice = sPrice;
	}

	public customerDetailsBean(){
		
	}
}
