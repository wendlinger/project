package br.com.project.dto;

import br.com.project.enums.EnumFolder;
import br.com.project.enums.EnumOrigin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDTO {

	private Long id;
	private float totalAmount;
	private float spentAmount;
	private EnumFolder possibleDestinations;
	private EnumOrigin origin;

}
