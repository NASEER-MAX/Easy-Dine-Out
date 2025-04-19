package com.restaurent.dto;

import com.restaurent.dto.request.TableTypeRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableTypeDto extends TableTypeRequest {

	private Long id;
}
