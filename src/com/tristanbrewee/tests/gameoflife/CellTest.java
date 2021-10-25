package com.tristanbrewee.tests.gameoflife;

import com.tristanbrewee.gameoflife.model.Cell;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    Cell cell;

    @BeforeEach
    void setUp() {
        cell = new Cell(true);
    }

    @AfterEach
    void tearDown() {
        cell = null;
    }

    @Test
    void getIsAlive() {
        assertTrue(cell.getIsAlive());
    }

    @Test
    void getWillLiveNextRound() {
        assertFalse(cell.getWillLiveNextRound());
    }

    @Test
    void setIsAlive() {
        cell.setIsAlive(false);
        assertFalse(cell.getIsAlive());
    }

    @Test
    void setWillLiveNextRound() {
        cell.setWillLiveNextRound(true);
        assertTrue(cell.getWillLiveNextRound());
    }

    @Test
    void testToString() {
        assertEquals(cell.toString(), "*");
        cell.setIsAlive(false);
        assertEquals(cell.toString(), " ");
    }
}