//
//package com.mycompany.gymmanager.controller;
//
//import com.mycompany.gymmanager.dto.WorkoutRequestDTO;
//import com.mycompany.gymmanager.dto.WorkoutResponseDTO;
//import com.mycompany.gymmanager.service.WorkoutService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@WebMvcTest(WorkoutController.class)
//class WorkoutControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private WorkoutService workoutService;
//
//    private WorkoutRequestDTO requestDTO;
//    private WorkoutResponseDTO responseDTO;
//    private UUID workoutId;
//
//    @BeforeEach
//    void setUp() {
//        workoutId = UUID.randomUUID();
//
//        requestDTO = new WorkoutRequestDTO(1,
//                "Full Body Workout",
//                "A complete workout for the entire body","Intermediate"
//        );
//
//        responseDTO = new WorkoutResponseDTO(
//                workoutId,
//                1,
//                "Full Body Workout",
//                "A complete workout for the entire body","Intermediate"
//        );
//    }
//
//    // -------------------------
//    // POST /api/workouts
//    // -------------------------
//    @Test
//    void shouldCreateWorkout() throws Exception {
//        when(workoutService.create(any())).thenReturn(responseDTO);
//
//        mockMvc.perform(post("/api/workouts")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                {
//                                    "name": "Full Body Workout",
//                                    "description": "A complete workout for the entire body"
//                                }
//                                """))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(workoutId.toString()))
//                .andExpect(jsonPath("$.name").value("Full Body Workout"));
//
//        verify(workoutService).create(any());
//    }
//
//    // -------------------------
//    // GET /api/workouts
//    // -------------------------
//    @Test
//    void shouldReturnAllWorkouts() throws Exception {
//        when(workoutService.getAll()).thenReturn(List.of(responseDTO));
//
//        mockMvc.perform(get("/api/workouts"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(1))
//                .andExpect(jsonPath("$[0].id").value(workoutId.toString()));
//
//        verify(workoutService).getAll();
//    }
//
//    // -------------------------
//    // GET /api/workouts/{id}
//    // -------------------------
//    @Test
//    void shouldReturnWorkoutById() throws Exception {
//        when(workoutService.getById(workoutId)).thenReturn(responseDTO);
//
//        mockMvc.perform(get("/api/workouts/" + workoutId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(workoutId.toString()))
//                .andExpect(jsonPath("$.name").value("Full Body Workout"));
//
//        verify(workoutService).getById(workoutId);
//    }
//
//    // -------------------------
//    // PUT /api/workouts/{id}
//    // -------------------------
//    @Test
//    void shouldUpdateWorkout() throws Exception {
//        when(workoutService.update(eq(workoutId), any())).thenReturn(responseDTO);
//
//        mockMvc.perform(put("/api/workouts/" + workoutId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                {
//                                    "name": "Full Body Workout",
//                                    "description": "A complete workout for the entire body"
//                                }
//                                """))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(workoutId.toString()));
//
//        verify(workoutService).update(eq(workoutId), any());
//    }
//
//    // -------------------------
//    // DELETE /api/workouts/{id}
//    // -------------------------
//    @Test
//    void shouldDeleteWorkout() throws Exception {
//        doNothing().when(workoutService).delete(workoutId);
//
//        mockMvc.perform(delete("/api/workouts/" + workoutId))
//                .andExpect(status().isNoContent());
//
//        verify(workoutService).delete(workoutId);
//    }
//}
