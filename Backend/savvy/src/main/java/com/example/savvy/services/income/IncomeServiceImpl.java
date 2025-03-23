package com.example.savvy.services.income;

import com.example.savvy.dto.IncomeDTO;
import com.example.savvy.entity.Income;
import com.example.savvy.repository.IncomeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    @Override
    public Income createIncome(IncomeDTO incomeDTO) {
        Income income = new Income();
        return saveOrUpdateIncome(income, incomeDTO);
    }

    private Income saveOrUpdateIncome(Income income, IncomeDTO incomeDTO) {
        income.setTitle(incomeDTO.getTitle());
        income.setDate(incomeDTO.getDate());
        income.setAmount(incomeDTO.getAmount());
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());
        return incomeRepository.save(income);
    }

    @Override
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Income getIncomeById(Long id) {
        return incomeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Income with id " + id + " not found."));
    }

    @Override
    public Income updateIncome(IncomeDTO incomeDTO, Long id) {
        Income income = incomeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Income with id " + id + " not found."));
        return saveOrUpdateIncome(income, incomeDTO);
    }

    @Override
    public void deleteIncome(Long id) {
    if(!incomeRepository.existsById(id)){
        throw new EntityNotFoundException("Income with id " + id + " not found.");
    }
    incomeRepository.deleteById(id);
    }
}
