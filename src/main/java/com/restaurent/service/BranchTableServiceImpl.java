package com.restaurent.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurent.adapter.BranchTableAdapter;
import com.restaurent.dto.BranchTableDto;
import com.restaurent.entity.Branch;
import com.restaurent.entity.BranchTable;
import com.restaurent.entity.TableType;
import com.restaurent.entity.User;
import com.restaurent.repository.BranchRepository;
import com.restaurent.repository.BranchTableRepository;
import com.restaurent.repository.TableTypeRepository;
import com.restaurent.service.interfaces.BranchTableService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class BranchTableServiceImpl implements BranchTableService {

	BranchTableRepository branchTableRepository;
	BranchRepository branchRepository;
	TableTypeRepository tableTypeRepository;

	BranchTableAdapter branchTableAdapter;

	@Transactional
	@Override
	public BranchTableDto createTable(long branchId, long tableId) {

		Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new EntityNotFoundException(
				"Branch not found with Id: " + branchId));

		TableType tableType = tableTypeRepository.findById(tableId).orElseThrow(() -> new EntityNotFoundException(
				"Table type not found with Id: " + tableId));

		BranchTable branchTable = new BranchTable();
		branchTable.setBranch(branch);
		branchTable.setTableType(tableType);

		BranchTable savedBranchTable = branchTableRepository.save(branchTable);

		return branchTableAdapter.toDto(savedBranchTable);
	}

	@Override
	public List<BranchTableDto> getTablesByBranchId(long branchId) {
		if (!branchRepository.existsById(branchId)) {
			throw new EntityNotFoundException("Branch not found with Id: " + branchId);
		}
		List<BranchTable> tables = branchTableRepository.findByBranchId(branchId);

		return branchTableAdapter.toDto(tables);
	}

	@Override
	public void deleteTable(long branchTableId) {
		if (!branchTableRepository.existsById(branchTableId)) {
			throw new EntityNotFoundException("Branch table not found with ID: " + branchTableId);
		}
		branchTableRepository.deleteById(branchTableId);

	}

	@Override
	public boolean isOwner(Long branchTableId, User authUser) {
		BranchTable branchTable = branchTableRepository.findById(branchTableId).orElseThrow(
				() -> new EntityNotFoundException(
						"Branch table not found with Id: " + branchTableId));

		Long ownerId = branchTable.getBranch().getRestaurant().getOwner().getId();
		return ownerId.equals(authUser.getId());
	}

}
