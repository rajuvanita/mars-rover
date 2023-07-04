package com.demo.marsrover.Controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.marsrover.Model.Rover;
import com.demo.marsrover.Model.RoverPosition;
import com.demo.marsrover.Service.RoverService;

@RestController
public class MarsRoverController {
	private static final Logger log = LoggerFactory.getLogger(MarsRoverController.class);

	@Autowired
	private RoverService roverService;

	@PostMapping("/createRover")
	String createRover(@RequestBody Rover newRover) {
		try {
			roverService.saveEvent(newRover);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in the Save Rover {} ", e.getMessage());
			return "Not successfully added Rover!";
		}
		return "Successfully added Rover!";
	}

	@GetMapping("/allPosition")
	Map<String, RoverPosition> getAllRoverPosition() {
		return roverService.findAllRoverPosition();
	}

	@GetMapping("/currentPosition")
	Map<String, RoverPosition> getRoverCurrentPosition(@RequestBody Rover newRover) {
		return roverService.findCurrentPostion(newRover);
	}

	@GetMapping("/moveRover")
	Map<String, RoverPosition> getRoverFinalPosition(@RequestBody Rover newRover, @RequestParam String args) {
		return roverService.findRoverMove(newRover, args);
	}

}
