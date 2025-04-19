package com.restaurent.service.interfaces;

import java.util.List;

import com.restaurent.dto.BranchTableDto;
import com.restaurent.entity.User;

public interface BranchTableService {

	BranchTableDto createTable(long branchId, long tableId);

	List<BranchTableDto> getTablesByBranchId(long branchId);

	void deleteTable(long branchTableId);

	boolean isOwner(Long branchTableId, User authUser);

}
