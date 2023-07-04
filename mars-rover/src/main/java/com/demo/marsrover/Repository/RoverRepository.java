/**
 * 
 */
package com.demo.marsrover.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.demo.marsrover.Model.RoverPosition;
import com.demo.marsrover.Model.Rover;

/**
 * @author Vanitha
 *
 */
public interface RoverRepository extends JpaRepository<Rover, Long>{
	
	public Rover findRoverById(Long Id);
	
	@Transactional
	@Modifying
	@Query("update Rover r set r.roverName = :roverName, r.xCoordinate = :xCoordinate, r.yCoordinate = :yCoordinate  where r.id = :id")
	void updateRoverInfo(@Param(value = "id") Long id, @Param(value = "roverName") String roverName, @Param(value = "xCoordinate") Long xCoordinate, @Param(value = "yCoordinate") Long yCoordinate);
	
	public Rover findRoverByRoverId(Long roverId);

}
