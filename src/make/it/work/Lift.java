package make.it.work;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Lift model, containing information direction, capacity, person inside lift,
 * current floor for lift etc.
 */
public class Lift {

	private DIRECTION direction = null;
	private int capacity = 0;
	private List<Person> personInLift = new ArrayList<>();

	public Lift(DIRECTION lftdirection, int liftCapacity) {
		this.direction = lftdirection;
		this.capacity = liftCapacity;
	}

	/**
	 * Validate if Lift is full.
	 * 
	 * @return boolean - true is full else false.
	 */
	public boolean isFull() {
		return personInLift.size() >= capacity;
	}

	/**
	 * Board person into lift.
	 * 
	 * @param person - person object
	 */
	public void in(Person person) {
		personInLift.add(person);
	}

	/**
	 * Unboard person from Lift.
	 * 
	 * @param person - person object
	 * @return boolean - true is successfully un-board else false
	 */
	public boolean out(Person person) {
		return personInLift.remove(person);
	}

	/**
	 * Return list of person present inside Lift.
	 * 
	 * @return list of person.
	 */
	public List<Person> listOfPeopleInsideLift() {
		return personInLift;
	}

	/**
	 * Return Direction of Lift.
	 * 
	 * @return direction - DIRECTION.
	 */
	public DIRECTION getDirection() {
		return direction;
	}

	/**
	 * Set Direction of lift.
	 * 
	 * @param directionToTravel - DIRECTION.
	 */
	public void setDirection(DIRECTION directionToTravel) {
		this.direction = directionToTravel;
	}

	/**
	 * Validate if Lift is emmpty.
	 * 
	 * @return boolean - true if empty else false.
	 */
	public boolean isEmpty() {
		return personInLift.isEmpty();
	}

	/**
	 * Remove people from list who already reached to destination.
	 * 
	 * @param lift
	 * @param currentFlr
	 */
	protected void clearWhoAlreadyReachedToDestination(Integer currentFlr) {
		List<Person> listOfPeopleInsideLift = listOfPeopleInsideLift();
		for (Iterator<Person> iterator = listOfPeopleInsideLift.iterator(); iterator.hasNext();) {
			Person person = iterator.next();
			if (person.outFloor() == currentFlr) {
				// remove this person from lift
				iterator.remove();
			}
		}
	}
}
