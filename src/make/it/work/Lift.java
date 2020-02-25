package make.it.work;

import java.util.ArrayList;
import java.util.List;

/**
 * Lift model, containing information direction, capacity, person inside lift, current floor for lift etc.
 */
public class Lift {
  
  DIRECTION    direction    = null;
  int          capacity     = 0;
  List<Person> personInLift = new ArrayList<>();
  //Ground floor
  int          currentFlr   = 0;
  int          toFlr        = 0;
  
  public Lift() {
  }
  
  public Lift(DIRECTION lftdirection, int liftCapacity) {
    this.direction = lftdirection;
    this.capacity = liftCapacity;
  }

  /**
   * Validate if Lift is full.
   * @return boolean - true is full else false.
   */
  public boolean isFull() {
    return personInLift.size() >= capacity;
  }

  /**
   * Board person into lift.
   * @param person - person object
   */
  public void in(Person person) {
    personInLift.add(person);
  }

  /**
   * Unboard person from Lift.
   * @param person - person object
   * @return boolean - true is successfully un-board else false
   */
  public boolean out(Person person) {
    return personInLift.remove(person);
  }

  /**
   * Return list of person present inside Lift.
   * @return list of person.
   */
  public List<Person> listOfPeopleInsideLift() {
    return personInLift;
  }

  /**
   * Current floor of Lift.
   * @return currentFloor - integer
   */
  public int getCurrentFloor() {
    return currentFlr;
  }

  /**
   * Set current floor for Lift.
   * @param flr- integer
   */
  public void setFloorToVisit(int flr) {
    currentFlr = flr;
  }

  /**
   * Return Direction of Lift.
   * @return direction - DIRECTION.
   */
  public DIRECTION getDirection() {
    return direction;
  }

  /**
   * Set Direction of lift.
   * @param directionToTravel - DIRECTION.
   */
  public void setDirection(DIRECTION directionToTravel) {
    this.direction = directionToTravel;
  }

  /**
   * Validate if Lift is emmpty.
   * @return boolean - true if empty else false.
   */
  public boolean isEmpty() {
    return personInLift.isEmpty();
  }
}
