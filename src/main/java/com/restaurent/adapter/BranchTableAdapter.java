package com.restaurent.adapter;

import org.springframework.stereotype.Service;

import com.restaurent.adapter.interfaces.AbstractAdapter;
import com.restaurent.dto.BranchTableDto;
import com.restaurent.entity.BranchTable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BranchTableAdapter extends AbstractAdapter<BranchTable, BranchTableDto> {

	TableTypeAdapter tableTypeAdapter;

	@Override
	public BranchTable toEntity(BranchTableDto dto) {
		return BranchTable.builder().build();
	}

	@Override
	public BranchTableDto toDto(BranchTable entity) {
		return BranchTableDto.builder()
				.id(entity.getId())
				.tableType(tableTypeAdapter.toDto(entity.getTableType()))
				.build();

	}

}
