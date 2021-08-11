package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;



/**
 * <b>CurvePoint est la classe représentant les points de courbes pour
 * l'application Poseidon.</b>
 * 
 * <p>
 * Les instances de cette classe proviennent de la base de donnée indiquée dans
 * le application.properties.
 * </p>
 * 
 * <p>
 * L'ensemble des attributs de cette classe proviennent d'une demande client.
 * </p>
 * 
 * <p>
 * Les attributs considérés comme obligatoires à la validation des formulaires
 * par le client sont annotées @Min, @Max
 * </p>
 * 
 * @see CurvePointController
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Min(value = 1, message = "must not be null")
	@Max(value = 127, message = "cannot be above 127 for Id")
	@Column(name = "curveid")
	private int curveId;

	@Column(name = "asofdate")
	private Timestamp asOfDate;

	@Column(name = "term")
	private Double term;

	@Column(name = "value")
	private Double value;

	@Column(name = "creationdate")
	private Timestamp creationDate;

	public CurvePoint() {
		super();
	}

	public CurvePoint(int curveId, double term, double value) {
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurveId() {
		return curveId;
	}

	public void setCurveId(int curveId) {
		this.curveId = curveId;
	}

	public Timestamp getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(Timestamp asOfDate) {
		this.asOfDate = asOfDate;
	}

	public Double getTerm() {
		return term;
	}

	public void setTerm(Double term) {
		this.term = term;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

}
