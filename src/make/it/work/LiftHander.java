package make.it.work;

/**
 Synopsis

 A multi-floor building has a Elevator in it.

 People are queued on different floors waiting for the Elevator.

 Some people want to go up. Some people want to go down.

 The floor they want to go to is represented by a number (i.e. when they enter the Elevator this is the button they will press)

 BEFORE (people waiting in queues)               AFTER (people at their destinations)
 +--+                                          +--+
 /----------------|  |----------------\        /----------------|  |----------------\
 10|                |  | 1,4,3,2        |      10|             10 |  |                |
 |----------------|  |----------------|        |----------------|  |----------------|
 9|                |  | 1,10,2         |       9|                |  |                |
 |----------------|  |----------------|        |----------------|  |----------------|
 8|                |  |                |       8|                |  |                |
 |----------------|  |----------------|        |----------------|  |----------------|
 7|                |  | 3,6,4,5,6      |       7|                |  |                |
 |----------------|  |----------------|        |----------------|  |----------------|
 6|                |  |                |       6|          6,6,6 |  |                |
 |----------------|  |----------------|        |----------------|  |----------------|
 5|                |  |                |       5|            5,5 |  |                |
 |----------------|  |----------------|        |----------------|  |----------------|
 4|                |  | 0,0,0          |       4|          4,4,4 |  |                |
 |----------------|  |----------------|        |----------------|  |----------------|
 3|                |  |                |       3|            3,3 |  |                |
 |----------------|  |----------------|        |----------------|  |----------------|
 2|                |  | 4              |       2|          2,2,2 |  |                |
 |----------------|  |----------------|        |----------------|  |----------------|
 1|                |  | 6,5,2          |       1|            1,1 |  |                |
 |----------------|  |----------------|        |----------------|  |----------------|
 G|                |  |                |       G|          0,0,0 |  |                |
 |====================================|        |====================================|

 Rules
 Elevator Rules

 The Elevator only goes up or down!
 Each floor has both UP and DOWN Elevator-call buttons (except top and ground floors which have only DOWN and UP respectively)
 The Elevator never changes direction until there are no more people wanting to get on/off in the direction it is already travelling
 When empty the Elevator tries to be smart. For example,
 If it was going up then it may continue up to collect the highest floor person wanting to go down
 If it was going down then it may continue down to collect the lowest floor person wanting to go up
 The Elevator has a maximum capacity of people
 When called, the Elevator will stop at a floor even if it is full, although unless somebody gets off nobody else can get on!
 If the lift is empty, and no people are waiting, then it will return to the ground floor

 People Rules

 People are in "queues" that represent their order of arrival to wait for the Elevator
 All people can press the UP/DOWN Elevator-call buttons
 Only people going the same direction as the Elevator may enter it
 Entry is according to the "queue" order, but those unable to enter do not block those behind them that can
 If a person is unable to enter a full Elevator, they will press the UP/DOWN Elevator-call button again after it has departed without them

 Kata Task

 Get all the people to the floors they want to go to while obeying the Elevator rules and the People rules
 Return a list of all floors that the Elevator stopped at (in the order visited!)

 NOTE: The Elevator always starts on the ground floor (and people waiting on the ground floor may enter immediately)
 I/O
 Input

 queues a list of queues of people for all floors of the building.
 The height of the building varies
 0 = the ground floor
 Not all floors have queues
 Queue index [0] is the "head" of the queue
 Numbers indicate which floor the person wants go to
 capacity maximum number of people allowed in the lift

 Parameter validation - All input parameters can be assumed OK. No need to check for things like:

 People wanting to go to floors that do not exist
 People wanting to take the Elevator to the floor they are already on
 Buildings with < 2 floors
 Basements

 Output

 A list of all floors that the Elevator stopped at (in the order visited!)

 Example

 Refer to the example test cases.

 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class LiftHander {
  
  public int[] goElevatorGo(final int[][] queues, final int CAPACITY) {
    
    final PriorityQueue<Integer> downWardsFlrToVisit = new PriorityQueue<Integer>(Comparator.reverseOrder());
    final PriorityQueue<Integer> upWardsFlrToVisit = new PriorityQueue<Integer>();
    final int GROUND_FLOOR = 0;
    /***
     * start from ground location to upwards
     */
    List<Integer> liftTraversalOrder = new ArrayList<Integer>();
    
    Lift lift = new Lift(DIRECTION.UP, CAPACITY);
    //get the initial floor list to visit
    boolean onlyGoingDown = true;
    for(int currentflr = 0; currentflr < queues.length; currentflr++) {
      int[] pplsOnFlr = queues[currentflr];
      // flr has people to enter 
      if(pplsOnFlr.length > 0) {
        // this flr has to be visited
        upWardsFlrToVisit.offer(currentflr);
        //check if every person wants to go up only.
        onlyGoingDown = onlyGoingDown && isEveryOneWantsToGoDown(currentflr, pplsOnFlr);
      }
    }
    
    // start from ground floor if not already.
    if(!upWardsFlrToVisit.contains(GROUND_FLOOR)) {
      liftTraversalOrder.add(GROUND_FLOOR);
    }
    //check if every person wants to go up only.
    if(onlyGoingDown) {
      //visit from top floor to ground floor
      downWardsFlrToVisit.addAll(upWardsFlrToVisit);
      //set lift direction to down
      lift.setDirection(DIRECTION.DOWN);
      //clear initial floor list
      upWardsFlrToVisit.clear();
    }
    
    while(!lift.isEmpty() || !upWardsFlrToVisit.isEmpty() || !downWardsFlrToVisit.isEmpty()) {
      
      //remove the first entry ( which is floor to visit )
      Integer currentFlr = !upWardsFlrToVisit.isEmpty() ? upWardsFlrToVisit.remove() : downWardsFlrToVisit.remove();
      liftTraversalOrder.add(currentFlr);
      
      if(null != currentFlr && lift.getDirection() == DIRECTION.UP) {
        currentFlr = moveUpWards(queues, downWardsFlrToVisit, upWardsFlrToVisit, lift, currentFlr);
      }
      
      if(null != currentFlr && lift.getDirection() == DIRECTION.DOWN) {
        moveDownWards(queues, downWardsFlrToVisit, upWardsFlrToVisit, lift, currentFlr);
      }
    }
    
    //move back to ground floor is not already.
    if(liftTraversalOrder.get(liftTraversalOrder.size() - 1) != 0) {
      liftTraversalOrder.add(0);
    }
    int[] traversalOrder = new int[liftTraversalOrder.size()];
    for(int i = 0; i < traversalOrder.length; i++) {
      traversalOrder[i] = liftTraversalOrder.get(i);
    }
    return traversalOrder;
  }
  
  /**
   * Move Lift downward direction while clearing out existing ppl's who want's to get down and new ppl's who want to get
   * in.
   * 
   * @param queues
   * @param downWardsFlrToVisit
   * @param upWardsFlrToVisit
   * @param lift
   * @param currentFlr
   */
  protected void moveDownWards(final int[][] queues, PriorityQueue<Integer> downWardsFlrToVisit,
                               PriorityQueue<Integer> upWardsFlrToVisit, Lift lift, Integer currentFlr) {
    //check if some one is getting out?
	  lift.clearWhoAlreadyReachedToDestination( currentFlr);
    
    //check if some one is getting in?
    int[] pplsOnFlr = queues[currentFlr];
    
    // check if we have ppl
    if(pplsOnFlr.length > 0) {
      
      for(int i = 0; i < pplsOnFlr.length; i++) {
        int flrPersonWantToVisit = pplsOnFlr[i];
        
        //allow only person is going donw from current flr and lift is not full.
        if(flrPersonWantToVisit < currentFlr && !lift.isFull()) {
          Person person = new Person(currentFlr, flrPersonWantToVisit, DIRECTION.DOWN);
          //person boarded the lift.
          lift.in(person);
          // now this person is already get into the lift and no more present over the floor.
          pplsOnFlr[i] = 0;
          
          //add their floor to visit + taking care of duplicate floor 
          if(!downWardsFlrToVisit.contains(flrPersonWantToVisit)) {
            downWardsFlrToVisit.offer(flrPersonWantToVisit);
          }
        }
        // they will be either going to up or we have to return the same floor to pick them as lift was full.
        else {
          //add their floor to visit + taking care of duplicate floor 
          if(!upWardsFlrToVisit.contains(currentFlr)) {
            upWardsFlrToVisit.offer(currentFlr);
          }
        }
      }
      //Assuming we reached to ground floor
      if(downWardsFlrToVisit.isEmpty()) {
        lift.setDirection(DIRECTION.UP);
      }
    }
  }
  
  /**
   * Move Lift upward direction while clearing out existing ppl's who want's to get down and new ppl's who want to get
   * in.
   * 
   * @param queues
   * @param downWardsFlrToVisit
   * @param upWardsFlrToVisit
   * @param lift
   * @param currentFlr
   * @return
   */
  protected Integer moveUpWards(final int[][] queues, PriorityQueue<Integer> downWardsFlrToVisit,
                                PriorityQueue<Integer> upWardsFlrToVisit, Lift lift, Integer currentFlr) {
    //check if some one is getting out?
    lift.clearWhoAlreadyReachedToDestination(currentFlr);
    
    //check if some one is getting in?
    int[] pplsOnFlr = queues[currentFlr];
    // check if we have ppl
    if(pplsOnFlr.length > 0) {
      
      for(int i = 0; i < pplsOnFlr.length; i++) {
        int flrPersonWantToVisit = pplsOnFlr[i];
        
        //allow only person is going up from current flr and lift is not full.
        if(flrPersonWantToVisit > currentFlr && !lift.isFull()) {
          Person person = new Person(currentFlr, flrPersonWantToVisit, DIRECTION.UP);
          //person boarded the lift.
          lift.in(person);
          // now this person is already get into the lift and no more present over the floor.
          pplsOnFlr[i] = 0;
          
          //add their floor to visit + taking care of duplicate floor 
          if(!upWardsFlrToVisit.contains(flrPersonWantToVisit)) {
            upWardsFlrToVisit.offer(flrPersonWantToVisit);
          }
        }
        // they will be either going to down or we have to return the same floor to pick them as lift was full.
        else {
          //add their floor to visit + taking care of duplicate floor 
          if(!downWardsFlrToVisit.contains(currentFlr)) {
            downWardsFlrToVisit.offer(currentFlr);
          }
        }
      }
      //Assuming we reached to top floor
      if(upWardsFlrToVisit.isEmpty() && !downWardsFlrToVisit.isEmpty()) {
        lift.setDirection(DIRECTION.DOWN);
        //start reverse traversing when no more upward floor are available.
        currentFlr = downWardsFlrToVisit.remove();
      }
    }
    return currentFlr;
  }
  
  
  /***
   * verify if every person on current floor want to go down only.
   * 
   * @param currentflr
   * @param pplsOnFlr
   * @return
   */
  protected boolean isEveryOneWantsToGoDown(int currentflr, int[] pplsOnFlr) {
    boolean status = true;
    for(int i = 0; i < pplsOnFlr.length; i++) {
      int flrPersonWantToVisit = pplsOnFlr[i];
      // just check if every one wants to go down.
      if(flrPersonWantToVisit > currentflr) {
        status = false;
        return status;
      }
    }
    return status;
  }
  
  public static void main(String[] args) {
    System.out.println("Hello! Implement the solution and proof it with the unit tests.");

  }
}
