package com.mycompany.gymmanager.mapper;


import com.mycompany.gymmanager.dto.*;
import com.mycompany.gymmanager.entity.Workout;
import org.mapstruct.*;
import java.util.*;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    @Mapping(target = "branchId", source = "branch.id")
    WorkoutResponseDTO toDTO(Workout workout);

    List<WorkoutResponseDTO> toDTOList(List<Workout> workouts);
}
