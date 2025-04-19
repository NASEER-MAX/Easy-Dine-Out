package com.restaurent.adapter.interfaces;

import java.util.List;

public interface Adapter<E, D> {
	E toEntity(D dto);

	D toDto(E entity);

	List<E> toEntity(List<D> dtos);

	List<D> toDto(List<E> entities);
}
