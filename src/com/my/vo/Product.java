package com.my.vo;

/*
 * 추후에 자바 빈으로 쓰일 수 있으므로 자바빈의 자격 요건에 맞춰서 작성
 * 자바빈 요건
 * public 클래스
 * public 생성자()
 * 프로퍼티용 멤버 변수는 public 이면 안된다.
 * 프로퍼티용 public setter()/getter() 메소드 필요
 * 
 * 
 */

public class Product {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prod_no == null) ? 0 : prod_no.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (prod_no == null) {
			if (other.prod_no != null)
				return false;
		} else if (!prod_no.equals(other.prod_no))
			return false;
		return true;
	}

	private String prod_no;
	private String prod_name;
	private int prod_price;
	private String prod_mfd;
	
	public Product() {
		super();
	}

	public Product(String prod_no, String prod_name, int prod_price, String prod_mfd) {
		super();
		this.prod_no = prod_no;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
		this.prod_mfd = prod_mfd;
	}

	@Override
	public String toString() {
		return "Product [prod_no=" + prod_no + ", prod_name=" + prod_name + ", prod_price=" + prod_price + ", prod_mfd="
				+ prod_mfd + "]";
	}
	
	public String getProd_no() {
		return prod_no;
	}

	public void setProd_no(String prod_no) {
		this.prod_no = prod_no;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getProd_price() {
		return prod_price;
	}

	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}

	public String getProd_mfd() {
		return prod_mfd;
	}

	public void setProd_mfd(String prod_mfd) {
		this.prod_mfd = prod_mfd;
	}
}
