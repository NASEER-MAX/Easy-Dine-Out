package com.restaurent.adapter.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractAdapter<E, D> implements Adapter<E, D> {

	@Override
	public abstract E toEntity(D dto);

	@Override
	public abstract D toDto(E entity);

	@Override
	public List<E> toEntity(List<D> dtos) {
		if (dtos == null)
			return new ArrayList<>();
		return dtos.stream().map(this::toEntity).collect(Collectors.toList());
	}

	@Override
	public List<D> toDto(List<E> entities) {
		if (entities == null)
			return new ArrayList<>();
		return entities.stream().map(this::toDto).collect(Collectors.toList());
	}

}
