package com.my.vo;

public class Post {
	private String zipcode;
	private String sido;
	private String sigungu;
	private String eupmyun;
	private String doro;
	private String buildingno;
	private String building1;
	private String building2;
	private String building;
	private String dong;
	
	public Post() {	}
	public Post(String zipcode, String sido, String sigungu, String eupmyun, String doro, String buildingno) {
		this.zipcode = zipcode;
		this.sido = sido;
		this.sigungu = sigungu;
		this.eupmyun = eupmyun;
		this.doro = doro;
		this.buildingno = buildingno;
	}
	public String getZipcode() {
		return zipcode;
	}	
	public Post(String zipcode, String sido, String sigungu, String eupmyun, String doro, String buildingno,
			String building1, String building2, String building, String dong) {
		super();
		this.zipcode = zipcode;
		this.sido = sido;
		this.sigungu = sigungu;
		this.eupmyun = eupmyun;
		this.doro = doro;
		this.buildingno = buildingno;
		this.building1 = building1;
		this.building2 = building2;
		this.building = building;
		this.dong = dong;
	}
	
	@Override
	/*public String toString() {
		return "Post [zipcode=" + zipcode + ", sido=" + sido + ", sigungu=" + sigungu + ", doro=" + doro
				+ ", buildingno=" + buildingno + ", dong=" + dong + "]";
	}*/
	public String toString() {
		String msg = "";
		msg = zipcode;
		msg += ":";
		msg += sido+" "+sigungu+" " + doro+ " "+building1+building2;
		msg += ":";
		msg +=  "("+dong+building+")";
		msg += ":";
		msg += buildingno;
		return msg;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getSigungu() {
		return sigungu;
	}
	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}
	public String getEupmyun() {
		return eupmyun;
	}
	public void setEupmyun(String eupmyun) {
		this.eupmyun = eupmyun;
	}
	public String getDoro() {
		return doro;
	}
	public void setDoro(String doro) {
		this.doro = doro;
	}
	public String getBuildingno() {
		return buildingno;
	}
	public void setBuildingno(String buildingno) {
		this.buildingno = buildingno;
	}
	public String getBuilding1() {
		return building1;
	}
	public void setBuilding1(String building1) {
		this.building1 = building1;
	}
	public String getBuilding2() {
		return building2;
	}
	public void setBuilding2(String building2) {
		this.building2 = building2;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
}
