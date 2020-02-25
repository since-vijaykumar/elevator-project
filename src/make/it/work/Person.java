package make.it.work;

/**
 * Person model, containing information like starting floor, floor where to get down and direction to move.
 */
public class Person {
  
  int       inFlr     = -1;
  int       outFlr    = -1;
  DIRECTION direction = null;
  
  public Person(int start, int end, DIRECTION currentDirection) {
    this.inFlr = start;
    this.outFlr = end;
    this.direction = currentDirection;
  }

  /**
   *
   * @return inFlr - floor where person entered into lift.
   */
  public int inFloor() {
    return inFlr;
  }

  /**
   * Exit floor where person wants to go.
   * @return outFlr- person wants to go.
   */
  public int outFloor() {
    return outFlr;
  }

  /**
   * Direction which person wants to move.
   * @return direction - up or down.
   */
  public DIRECTION getDirection() {
    return direction;
  }
  //dont implement hashcode as we want to relay on object reference then object values.
}
