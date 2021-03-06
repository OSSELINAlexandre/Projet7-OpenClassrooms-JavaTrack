package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * <b>RuleName est la classe représentant les règles à appliquer pour les actifs
 * pour l'application Poseidon.</b>
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
 * par le client sont annotées @NotBlank
 * </p>
 * 
 * @see RuleNameController
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Entity
@Table(name = "rulename")
public class RuleName {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotBlank(message = "Name is mandatory")
	@Column(name = "name")
	private String name;

	@NotBlank(message = "Description is mandatory")
	@Column(name = "description")
	private String description;

	@NotBlank(message = "Json is mandatory")
	@Column(name = "json")
	private String json;

	@NotBlank(message = "Template is mandatory")
	@Column(name = "template")
	private String template;

	@NotBlank(message = "SQL is mandatory")
	@Column(name = "sqlstr")
	private String sqlStr;

	@NotBlank(message = "SQLPart is mandatory")
	@Column(name = "sqlpart")
	private String sqlPart;

	public RuleName() {
		super();
	}

	public RuleName(String RuleName, String description, String json, String template, String SQL, String SQLpart) {

		this.name = RuleName;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = SQL;
		this.sqlPart = SQLpart;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public String getSqlPart() {
		return sqlPart;
	}

	public void setSqlPart(String sqlPart) {
		this.sqlPart = sqlPart;
	}

}
