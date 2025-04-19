package com.restaurent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurent.adapter.TableTypeAdapter;
import com.restaurent.dto.TableTypeDto;
import com.restaurent.dto.request.TableTypeRequest;
import com.restaurent.entity.TableType;
import com.restaurent.repository.TableTypeRepository;
import com.restaurent.service.interfaces.TableTypeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class TableTypeServiceImpl implements TableTypeService {

	TableTypeRepository tableTypeRepository;
	TableTypeAdapter tableTypeAdapter;

	@Override
	public List<TableTypeDto> getAllTableTypes() {
		return tableTypeAdapter.toDto(tableTypeRepository.findAll());
	}

	@Override
	public TableTypeDto getTableTypeById(long id) {
		Optional<TableType> tableType = tableTypeRepository.findById(id);
		if (tableType.isEmpty()) {
			throw new EntityNotFoundException("TableType not found with ID: " + id);
		}
		return tableTypeAdapter.toDto(tableType.get());
	}

	@Override
	public TableTypeDto createTableType(TableTypeRequest tableTypeRequest) {
		TableType tableType = new TableType();
		tableType.setChairs(tableTypeRequest.getChairs());
		tableType.setName(tableTypeRequest.getName());

		tableTypeRepository.save(tableType);
		return tableTypeAdapter.toDto(tableType);
	}

	@Transactional
	@Override
	public TableTypeDto updateTableType(long id, TableTypeRequest tableTypeRequest) {

		TableType tableType = tableTypeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Table type not found with ID: " + id));

		tableType.setChairs(tableTypeRequest.getChairs());
		tableType.setName(tableTypeRequest.getName());

		tableTypeRepository.save(tableType);
		return tableTypeAdapter.toDto(tableType);

	}

	@Transactional
	@Override
	public void deleteTableType(long id) {
		if (!tableTypeRepository.existsById(id)) {
			throw new EntityNotFoundException("TableType not found with ID: " + id);
		}

		tableTypeRepository.deleteById(id);

	}
}
