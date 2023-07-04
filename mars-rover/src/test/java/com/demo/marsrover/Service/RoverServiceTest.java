package com.demo.marsrover.Service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
//import org.junit.runner.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.marsrover.Model.Rover;
import com.demo.marsrover.Model.RoverPosition;
import com.demo.marsrover.Repository.RoverRepository;
import com.demo.marsrover.Service.RoverService;


@SpringBootTest
class RoverServiceTest {

	@Autowired
	RoverService roverService;

	@MockBean
	RoverRepository roverRepository;

	@SuppressWarnings("deprecation")
	@Before(value = "")
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testSaveRover() {
		Rover rover = new Rover();
		rover.setRoverId(1111l);
		rover.setRoverName("R1");
		rover.setxCoordinate((long)3);
		rover.setyCoordinate((long)4);
		rover.setDirection("N");

		//when(roverRepository.findRoverById(1111l, "R1", (long) 3, (long) 4)).thenReturn(null);
		when(roverRepository.findRoverById(1111l)).thenReturn(null);
		roverService.saveEvent(rover);
		verify(roverRepository, times(1)).save(rover);
	}


	void assertCurrentPosition(String expected, String actual, String key, List<Rover> rovers) {

		when(roverRepository.findAll()).thenReturn(rovers);
		Map<String, RoverPosition> position = roverService.findCurrentPostion(rovers.get(0));
		RoverPosition positions = position.get(key);
		org.junit.Assert.assertEquals(positions.getName(), "R1");
	}

	void assertFinalPosition(String expected, String actual, String key, List<Rover> rovers) {

		when(roverRepository.findAll()).thenReturn(rovers);
		Map<String, RoverPosition> position = roverService.findRoverMove(rovers.get(1), key);
		RoverPosition positions = position.get(key);
		assertEquals(positions.getDirection(), "South");

	}

}
