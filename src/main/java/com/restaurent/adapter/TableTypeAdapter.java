package com.restaurent.adapter;

import org.springframework.stereotype.Service;

import com.restaurent.adapter.interfaces.AbstractAdapter;
import com.restaurent.dto.TableTypeDto;
import com.restaurent.entity.TableType;

@Service
public class TableTypeAdapter extends AbstractAdapter<TableType, TableTypeDto> {

	@Override
	public TableType toEntity(TableTypeDto dto) {
		return TableType.builder().chairs(dto.getChairs()).name(dto.getName()).build();
	}

	@Override
	public TableTypeDto toDto(TableType entity) {
		return TableTypeDto.builder().id(entity.getId()).chairs(entity.getChairs()).name(entity.getName()).build();
	}

}
