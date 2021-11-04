package br.com.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import br.com.project.enums.EnumFolder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Data
@Table(name = "project")
public class Project {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private Long secretariatId;
	
	@NotNull
	private float cost;
	
	@NotNull
	private String title;
	
	@NotNull
	private String description;
	
	@NotNull
	private EnumFolder folder;
}
