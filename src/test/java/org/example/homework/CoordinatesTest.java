package org.example.homework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @ParameterizedTest
    @MethodSource("createCoordinatesWithXAndY")
    void exceptionShouldBeThrownIfCoordinatesAreOutOfBoundary(int x, int y) {

        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(x, y));
    }

    private static Stream<Arguments> createCoordinatesWithXAndY() {
        return Stream.of(
                Arguments.of(0, -1),
                Arguments.of(-1, 0),
                Arguments.of(-1, -1),
                Arguments.of(100, 101),
                Arguments.of(101, 100),
                Arguments.of(101, 101)
        );
    }

    @Test
    void copyShouldReturnNewObject() {
        //given
        Coordinates coordinates = new Coordinates(10,10);

        //when
        Coordinates copy = Coordinates.copy(coordinates, 0, 0);

        //then
        assertThat(copy, not(sameInstance(coordinates)));
        assertThat(copy, equalTo(coordinates));
    }

    @Test
    void copyShouldReturnAddCoordinates() {
        //given
        Coordinates coordinates = new Coordinates(10,10);

        //when
        Coordinates copy = Coordinates.copy(coordinates, 5, 6);

        //then
        assertThat(copy.getX(), equalTo(15));
        assertThat(copy.getY(), equalTo(16));

    }
}