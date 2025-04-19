package com.restaurent.adapter;

import org.springframework.stereotype.Service;

import com.restaurent.adapter.interfaces.AbstractAdapter;
import com.restaurent.dto.BookingDto;
import com.restaurent.entity.Booking;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookingAdapter extends AbstractAdapter<Booking, BookingDto> {

	TableTypeAdapter tableTypeAdapter;

	@Override
	public Booking toEntity(BookingDto dto) {
		return Booking.builder()
				.startDateTime(dto.getStartDateTime())
				.build();
	}

	@Override
	public BookingDto toDto(Booking entity) {
		return BookingDto.builder()
				.id(entity.getId())
				.startDateTime(entity.getStartDateTime())
				.endDateTime(entity.getEndDateTime())
				.branchTableId(entity.getBranchTable().getId())
				.build();


	}

}
