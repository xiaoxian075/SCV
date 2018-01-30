package com.scv.vadmin.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
  * @ClassName: User 
  * @Description: TODO
  * @author chenjx
  * @date 2017年12月20日 上午9:44:13
*/

@Entity
@Table(name = "user")
public class User {
	
	public final static String ID = "id";
	public final static String NAME = "name";
	public final static String HEIGHT = "height";
	public final static String SEX = "sex";
	public final static String BIRTHDAY = "birthday";
	public final static String SENDTIME = "sendtime";
	public final static String PRICE = "price";
	public final static String FLOATPRICE = "floatprice";
	public final static String DOUBLEPRICE = "doubleprice";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, length = 10)
	private int id;

	@Column(name = "name", nullable = true, length = 30)
	private String name;

	@Column(name = "height", nullable = true, length = 10)
	private int height;

	@Column(name = "sex", nullable = true, length = 2)
	private char sex;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Temporal(TemporalType.TIMESTAMP)
	private Date sendtime; // 日期类型，格式：yyyy-MM-dd HH:mm:ss


	@Column(name = "price", nullable = true, length = 10)
	private BigDecimal price;


	@Column(name = "floatprice", nullable = true, length = 10)
	private float floatprice;


	@Column(name = "doubleprice", nullable = true, length = 10)
	private double doubleprice;


	public User() {
	
	}
	
//	public User(int id) {
//		this.id = id;
//	}

	public User(String name, int height, char sex, Date birthday, Date sendtime, BigDecimal price, float floatprice,
			double doubleprice) {
		super();
		this.id = 0;
		this.name = name;
		this.height = height;
		this.sex = sex;
		this.birthday = birthday;
		this.sendtime = sendtime;
		this.price = price;
		this.floatprice = floatprice;
		this.doubleprice = doubleprice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public float getFloatprice() {
		return floatprice;
	}

	public void setFloatprice(float floatprice) {
		this.floatprice = floatprice;
	}

	public double getDoubleprice() {
		return doubleprice;
	}

	public void setDoubleprice(double doubleprice) {
		this.doubleprice = doubleprice;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", height=" + height + ", sex=" + sex + ", birthday=" + birthday
				+ ", sendtime=" + sendtime + ", price=" + price + ", floatprice=" + floatprice + ", doubleprice="
				+ doubleprice + "]";
	}
	
	
}
