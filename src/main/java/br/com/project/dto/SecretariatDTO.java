package br.com.project.dto;

import br.com.project.enums.EnumFolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecretariatDTO {

	private Long id;
	private String secretary;
	private EnumFolder folder;
	private int populationGrade;
	private boolean underInvestigation = false;
	
}
