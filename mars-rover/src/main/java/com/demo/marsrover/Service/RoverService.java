package com.demo.marsrover.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.marsrover.Model.Rover;
import com.demo.marsrover.Model.RoverPosition;
import com.demo.marsrover.Repository.RoverRepository;
import com.demo.marsrover.Util.Constants;

@Component
public class RoverService {
	private static final Logger log = LoggerFactory.getLogger(RoverService.class);

	@Autowired
	private RoverRepository repository;

	public void saveEvent(Rover newRover) {

		try {
			Rover rover = repository.findRoverById(newRover.getId());
			if (rover != null) {
				if (newRover.getxCoordinate() > rover.getxCoordinate()
						&& newRover.getyCoordinate() > rover.getyCoordinate()) {
					log.info("Calling rover--->update" + rover.toString());

					repository.updateRoverInfo(rover.getId(), newRover.getRoverName(), newRover.getxCoordinate(),
							newRover.getyCoordinate());

				}
			} else {
				log.info("Calling newRover-->insert");
				repository.save(newRover);
			}

		} catch (Exception e) {
			log.error("Error in the Save Rover {} ", e.getMessage());
			throw e;

		}
	}

	public Map<String, RoverPosition> findAllRoverPosition() {
		try {
			log.info("Calling findRoverPosition---");

			List<Rover> rovers = repository.findAll();
			Map<String, RoverPosition> position = new HashMap<>();

			for (Rover rover : rovers) {

				String mapKey = "Rover-" + rover.getRoverName() + ",Position";
				if (position.containsKey(mapKey)) {
					RoverPosition roverPosition = position.get(mapKey);
					roverPosition.setRovers(rover.getRoverId());
					if (Constants.DIRECTION_N.equalsIgnoreCase(rover.getDirection())) {
						roverPosition.setDirection(Constants.DIRECTION_NORTH);
					} else if (Constants.DIRECTION_S.equalsIgnoreCase(rover.getDirection())){
						roverPosition.setDirection(Constants.DIRECTION_SOUTH);
					} else if (Constants.DIRECTION_E.equalsIgnoreCase(rover.getDirection())) {
						roverPosition.setDirection(Constants.DIRECTION_EAST);
					} else if (Constants.DIRECTION_W.equalsIgnoreCase(rover.getDirection())){
						roverPosition.setDirection(Constants.DIRECTION_WEST);
					}
					position.put(mapKey, roverPosition);

				} else {
					RoverPosition roverPosition = new RoverPosition();
					roverPosition.setX(rover.getxCoordinate());
					roverPosition.setY(rover.getyCoordinate());
					roverPosition.setName(rover.getRoverName());
					if (Constants.DIRECTION_N.equalsIgnoreCase(rover.getDirection())) {
						roverPosition.setDirection(Constants.DIRECTION_NORTH);
					} else if (Constants.DIRECTION_S.equalsIgnoreCase(rover.getDirection())){
						roverPosition.setDirection(Constants.DIRECTION_SOUTH);
					} else if (Constants.DIRECTION_E.equalsIgnoreCase(rover.getDirection())) {
						roverPosition.setDirection(Constants.DIRECTION_EAST);
					} else if (Constants.DIRECTION_W.equalsIgnoreCase(rover.getDirection())){
						roverPosition.setDirection(Constants.DIRECTION_WEST);
					} else {
						log.info("Wrong direction....");
					}

					roverPosition.setRovers(rover.getRoverId());
					roverPosition.setCoordinate(roverPosition.getX() + "," + roverPosition.getY());
					position.put(mapKey, roverPosition);
				}

			}

			return position;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}

	}

	public Map<String, RoverPosition> findCurrentPostion(Rover newRover) {

		try {
			Rover rover = repository.findRoverByRoverId(newRover.getRoverId());
			RoverPosition roverPosition = new RoverPosition();
			Map<String, RoverPosition> position = new HashMap<>();
			if (rover != null) {
				roverPosition.setName(rover.getRoverName());
				roverPosition.setDirection(getDirection(rover.getDirection()));
				String mapKey = "Rover-" + rover.getRoverName() + ", Current Position";
				String coord = String.valueOf(rover.getxCoordinate()) + "," + String.valueOf(rover.getyCoordinate());
				roverPosition.setCoordinate(coord);
				roverPosition.setX(rover.getxCoordinate());
				roverPosition.setY(rover.getyCoordinate());
				roverPosition.setRovers(rover.getRoverId());
				position.put(mapKey, roverPosition);
			}else {
				log.error("Unable to find the position");
			}
			return position;
		} catch (Exception e) {
			log.error("Error in the Rover move {} ", e.getMessage());
			throw e;

		}
	}

	public Map<String, RoverPosition> findRoverMove(Rover newRover, String args) {
		log.info("Calling findRoverMove---");
		String[] s = args.split(" ");
		
		try {
			Rover rover = repository.findRoverByRoverId(newRover.getRoverId());
			RoverPosition roverPosition = new RoverPosition();
			Map<String, RoverPosition> position = new HashMap<>();
			String direc = "";
			Long xCoord = 0L;
			Long yCoord = 0L;

			if (rover != null) {
				roverPosition.setName(rover.getRoverName());
				if (s[0].equals(Constants.COMMAND_FORWARD)) {
					xCoord = rover.getxCoordinate() + 1;
					roverPosition.setX(xCoord);
				} else {
					xCoord = rover.getxCoordinate() - 1;
					roverPosition.setX(xCoord);
				}
				if (s[1].equals(Constants.COMMAND_FORWARD)) {
					roverPosition.setX(xCoord++);
				} else {
					roverPosition.setX(xCoord--);
				}
				if (s[2].equalsIgnoreCase(Constants.ROTATION_CLOCKWISE))
					direc = moveToRight(rover.getDirection());
				else
					direc = moveToLeft(rover.getDirection());

				roverPosition.setDirection(direc);

				if (s[3].equals(Constants.COMMAND_FORWARD)) {
					yCoord = rover.getyCoordinate() + 1;
					roverPosition.setY(yCoord);
				} else {
					yCoord = rover.getyCoordinate() - 1;
					roverPosition.setY(yCoord);
				}
				if (s[4].equals(Constants.COMMAND_FORWARD)) {
					roverPosition.setY(yCoord++);
				} else {
					roverPosition.setY(yCoord--);
				}

				String mapKey = "Rover-" + rover.getRoverName() + ", Final Position";
				String coord = String.valueOf(roverPosition.getX()) + "," + String.valueOf(roverPosition.getY());
				roverPosition.setCoordinate(coord);
				roverPosition.setRovers(rover.getRoverId());
				position.put(mapKey, roverPosition);

			}
			return position;

		} catch (Exception e) {
			log.error("Error in the Rover move {} ", e.getMessage());
			throw e;

		}
	}

	// Rover move 90 clockwise
	private String moveToRight(String direction) {
		if (Constants.DIRECTION_N.equalsIgnoreCase(direction))
			return Constants.DIRECTION_EAST;
		if (Constants.DIRECTION_E.equalsIgnoreCase(direction))
			return Constants.DIRECTION_SOUTH;
		if (Constants.DIRECTION_S.equalsIgnoreCase(direction))
			return Constants.DIRECTION_WEST;
		return Constants.DIRECTION_NORTH;
	}

	// Rover move 90 anti clockwise
	private String moveToLeft(String direction) {
		if (Constants.DIRECTION_N.equalsIgnoreCase(direction))
			return Constants.DIRECTION_WEST;
		if (Constants.DIRECTION_W.equalsIgnoreCase(direction))
			return Constants.DIRECTION_SOUTH;
		if (Constants.DIRECTION_S.equalsIgnoreCase(direction))
			return Constants.DIRECTION_EAST;
		return Constants.DIRECTION_NORTH;
	}
	
	//Get full direction
	private String getDirection(String instruct) {
		if (Constants.DIRECTION_E.equalsIgnoreCase(instruct))
			return Constants.DIRECTION_EAST;
		if (Constants.DIRECTION_W.equalsIgnoreCase(instruct))
			return Constants.DIRECTION_WEST;
		if (Constants.DIRECTION_S.equalsIgnoreCase(instruct))
			return Constants.DIRECTION_SOUTH;
		return Constants.DIRECTION_NORTH;
	}

}
