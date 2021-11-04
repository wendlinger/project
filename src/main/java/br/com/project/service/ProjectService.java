package br.com.project.service;

import java.net.URISyntaxException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.project.consumers.BudgetConsumer;
import br.com.project.consumers.SecretariatConsumer;
import br.com.project.dto.BudgetDTO;
import br.com.project.dto.SecretariatDTO;
import br.com.project.exceptions.BusinessException;
import br.com.project.model.Project;
import br.com.project.repository.ProjectRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProjectService {

	private ProjectRepository projectRepository;

	private SecretariatConsumer secretariatConsumer;

	private BudgetConsumer budgetConsumer;

	public void valid(Project project, SecretariatDTO secretariatDTO, BudgetDTO budgetDTO) throws BusinessException {
		StringBuilder errors = new StringBuilder();

		if (project.getSecretariatId() == null || secretariatDTO == null) {
			errors.append("The secretariat needs to be informed.");
		}
		if (secretariatDTO.isUnderInvestigation()) {
			errors.append("Can't to assign a project to a secretariat under investigation.");
		}
		if (budgetDTO == null || budgetDTO.getId() == null) {
			errors.append("Hasn't available resource to project.");
		}

		if (!errors.isEmpty()) {
			throw new BusinessException(errors.toString());
		}

	}

	@Transactional
	public Project save(Project project) throws BusinessException, URISyntaxException {

		SecretariatDTO secretariatDTO = secretariatConsumer.getById(project.getSecretariatId());
		BudgetDTO budgetDTO = budgetConsumer.getByPossibleDestinationsWithAvailableResource(project.getFolder(),
				project.getCost());
		valid(project, secretariatDTO, budgetDTO);

		budgetDTO.setSpentAmount(project.getCost());
		budgetConsumer.updateExpenseValue(budgetDTO);

		return projectRepository.save(project);
	}

}
