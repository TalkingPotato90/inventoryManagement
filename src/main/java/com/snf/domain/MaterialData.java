package com.snf.domain;

public class MaterialData {
    
	private int num;
	private int id;
	private String largeCat;
    private String midCat;
    private String smallCat;
    private String ioTyp;
    private String outName;
    private String ioDate;
	private String qty;
	private String ioQty;

	public MaterialData() {}

    public MaterialData(int num, int id, String largeCat, String midCat, String smallCat) {
    	this.num = num;
        this.id = id;
    	this.largeCat = largeCat;
        this.midCat = midCat;
        this.smallCat = smallCat;
    }

    public MaterialData(int num, String largeCat, String midCat, String smallCat) {
    	this.num = num;
    	this.largeCat = largeCat;
        this.midCat = midCat;
        this.smallCat = smallCat;
    }
    
    public MaterialData(int num, String largeCat, String midCat, String smallCat, String ioTyp, String ioDate, String ioQty) {
    	this.num = num;
    	this.largeCat = largeCat;
        this.midCat = midCat;
        this.smallCat = smallCat;
        this.ioTyp = ioTyp;
        this.ioDate = ioDate;
    	this.ioQty = ioQty;
    }

	public MaterialData(int num2, String lc, String mc, String sc, String iotyp2, String iodate2, String ioqty2,
			String outName2) {
    	this.num = num2;
    	this.largeCat = lc;
        this.midCat = mc;
        this.smallCat = sc;
        this.ioTyp = iotyp2;
        this.ioDate = iodate2;
    	this.ioQty = ioqty2;
    	this.outName = outName2;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public String getIoTyp() {
		return ioTyp;
	}

	public void setIoTyp(String ioTyp) {
		this.ioTyp = ioTyp;
	}

	public String getOutName() {
		return outName;
	}

	public void setOutName(String outName) {
		this.outName = outName;
	}

	public String getIoDate() {
		return ioDate;
	}

	public void setIoDate(String ioDate) {
		this.ioDate = ioDate;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getIoQty() {
		return ioQty;
	}

	public void setIoQty(String ioQty) {
		this.ioQty = ioQty;
	}

    
}
