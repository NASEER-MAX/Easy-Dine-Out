package com.restaurent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurent.entity.TableType;

@Repository
public interface TableTypeRepository extends JpaRepository<TableType, Long> {

}
