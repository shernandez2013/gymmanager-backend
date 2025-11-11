package com.mycompany.gymmanager.controller;

import com.mycompany.gymmanager.entity.Branch;
import com.mycompany.gymmanager.exception.ResourceNotFoundException;
import com.mycompany.gymmanager.service.BranchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BranchControllerTest {

    @Mock
    private BranchService service;

    private BranchController controller;

    @BeforeEach
    void setUp() {
        controller = new BranchController(service);
    }

    @Test
    void createBranch_returnsCreated() {
        Branch input = new Branch();
        input.setName("Main");

        Branch saved = new Branch();
        UUID id = UUID.randomUUID();
        saved.setId(id);
        saved.setName("Main");

        when(service.createBranch(any(Branch.class))).thenReturn(saved);

        ResponseEntity<Branch> resp = controller.createBranch(input);

        assertEquals(HttpStatus.CREATED, resp.getStatusCode());
        assertNotNull(resp.getBody());
        assertSame(saved, resp.getBody());

        verify(service).createBranch(any(Branch.class));
    }

    @Test
    void getAllBranches_returnsList() {
        Branch b = new Branch();
        b.setId(UUID.randomUUID());
        b.setName("Branch1");

        List<Branch> list = List.of(b);
        when(service.getAllBranches()).thenReturn(list);

        ResponseEntity<List<Branch>> resp = controller.getAllBranches();

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertNotNull(resp.getBody());
        assertEquals(1, resp.getBody().size());
        assertSame(b, resp.getBody().getFirst());

        verify(service).getAllBranches();
    }

    @Test
    void getBranchById_returnsBranch() {
        UUID id = UUID.randomUUID();
        Branch b = new Branch();
        b.setId(id);
        b.setName("BranchX");

        when(service.getBranchById(id)).thenReturn(b);

        ResponseEntity<Branch> resp = controller.getBranchById(id);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertNotNull(resp.getBody());
        assertSame(b, resp.getBody());

        verify(service).getBranchById(id);
    }

    @Test
    void getBranchById_notFound_throwsResourceNotFoundException() {
        UUID id = UUID.randomUUID();
        when(service.getBranchById(any(UUID.class))).thenThrow(new ResourceNotFoundException("not found"));

        assertThrows(ResourceNotFoundException.class, () -> controller.getBranchById(id));

        verify(service).getBranchById(id);
    }

    @Test
    void updateBranch_returnsUpdated() {
        UUID id = UUID.randomUUID();
        Branch update = new Branch();
        update.setName("Updated");

        Branch updated = new Branch();
        updated.setId(id);
        updated.setName("Updated");

        when(service.updateBranch(eq(id), any(Branch.class))).thenReturn(updated);

        ResponseEntity<Branch> resp = controller.updateBranch(id, update);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertNotNull(resp.getBody());
        assertSame(updated, resp.getBody());

        verify(service).updateBranch(eq(id), any(Branch.class));
    }

    @Test
    void deleteBranch_returnsNoContent() {
        UUID id = UUID.randomUUID();
        doNothing().when(service).deleteBranch(id);

        ResponseEntity<Void> resp = controller.deleteBranch(id);

        assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());
        assertNull(resp.getBody());

        verify(service).deleteBranch(id);
    }
}
