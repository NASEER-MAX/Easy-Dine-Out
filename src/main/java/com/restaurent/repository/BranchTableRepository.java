package com.restaurent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurent.entity.BranchTable;

public interface BranchTableRepository extends JpaRepository<BranchTable, Long> {

	List<BranchTable> findByBranchId(Long branchId);

}
