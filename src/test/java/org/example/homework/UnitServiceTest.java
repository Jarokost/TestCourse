package org.example.homework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UnitServiceTest {


    @Mock
    CargoRepository cargoRepository;

    @Mock
    UnitRepository unitRepository;

    @InjectMocks
    UnitService unitService;

    @Test
    void addedCargoShouldBeLoadedOnUnit() {

        //given
        Unit unit = new Unit(new Coordinates(0,0),0,10);
        Cargo cargo = new Cargo("cargo", 5);

        given(cargoRepository.findCargoByName(cargo.getName())).willReturn(Optional.of(cargo));

        //when
        unitService.addCargoByName(unit, cargo.getName());

        //then
        verify(cargoRepository).findCargoByName(cargo.getName());
        assertThat(unit.getLoad(), is(5));
        assertThat(unit.getCargo().getFirst(), equalTo(cargo));
    }

    @Test
    void shouldThrowExceptionIfNoCargoIsFoundToAdd() {

        //given
        Unit unit = new Unit(new Coordinates(0,0),0,10);

        given(cargoRepository.findCargoByName("cargo")).willReturn(Optional.empty());

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> unitService.addCargoByName(unit, "cargo"));
    }

    @Test
    void shouldReturnUnitByCoordinates() {

        //given
        Unit unit = new Unit(new Coordinates(0,0),0,10);

        given(unitRepository.getUnitByCoordinates(new Coordinates(0,0))).willReturn(unit);

        //when
        Unit unit2 = unitService.getUnitOn(new Coordinates(0,0));

        //then
        assertThat(unit2, sameInstance(unit));
    }

    @Test
    void shouldReturnExceptionIfUnitNotFound() {

        //given
        given(unitRepository.getUnitByCoordinates(new Coordinates(0,0))).willReturn(null);

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> unitService.getUnitOn(new Coordinates(0,0)));
    }
}
