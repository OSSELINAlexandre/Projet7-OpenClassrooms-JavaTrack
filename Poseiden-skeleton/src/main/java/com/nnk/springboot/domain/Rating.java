package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "rating")
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int Id;

	
	@Column(name = "moodysrating")
	private String moodysRating;

	@Column(name = "sandprating")
	private String sandpRating;

	@Column(name = "fitchrating")
	private String fitchRating;

	@Min(value= 1 , message ="must not be null")
	@Max(value=127, message ="cannot be above 127 for orderNumber")
	@Column(name = "ordernumber")
	private int orderNumber;

	public Rating() {
		super();
	}

	public Rating(String moodysRating, String sandpRating, String fitchRating, int orderNumber) {
			
		this.moodysRating = moodysRating;
		this.sandpRating = sandpRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}



	public String getMoodysRating() {
		return moodysRating;
	}

	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}


	public String getSandpRating() {
		return sandpRating;
	}

	public void setSandpRating(String sandpRating) {
		this.sandpRating = sandpRating;
	}

	public String getFitchRating() {
		return fitchRating;
	}

	public void setFitchRating(String fitchRating) {
		this.fitchRating = fitchRating;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

}
