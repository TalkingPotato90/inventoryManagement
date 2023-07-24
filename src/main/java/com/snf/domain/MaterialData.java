package com.snf.domain;

public class MaterialData {
    
	private int id;
	private String largeCat;
    private String midCat;
    private String smallCat;

    public MaterialData(int id, String largeCat, String midCat, String smallCat) {
        this.id = id;
    	this.largeCat = largeCat;
        this.midCat = midCat;
        this.smallCat = smallCat;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLargeCat() {
		return largeCat;
	}

	public void setLargeCat(String largeCat) {
		this.largeCat = largeCat;
	}

	public String getMidCat() {
		return midCat;
	}

	public void setMidCat(String midCat) {
		this.midCat = midCat;
	}

	public String getSmallCat() {
		return smallCat;
	}

	public void setSmallCat(String smallCat) {
		this.smallCat = smallCat;
	}
}
