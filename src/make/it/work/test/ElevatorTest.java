package make.it.work.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import make.it.work.Elevator;

/**
 * Unit test for Elevator assignment.
 */
public class ElevatorTest {
  /**
   * Rigorous Test :-)
   */
  @Test
  public void shouldAnswerWithTrue() {
    assertTrue(true);
  }
  
  /**
   * Example Test, just for you to understand the assignment.
   */
  @Test
  public void testUp() {
    final int[][] queues = {new int[0], // G
                            new int[0], // 1
                            new int[] {5, 5, 5}, // 2
                            new int[0], // 3
                            new int[0], // 4
                            new int[0], // 5
                            new int[0], // 6
    };
    final int[] result = new Elevator().goElevatorGo(queues, 5);
    assertArrayEquals(new int[] {0, 2, 5, 0}, result);
  }
  
  @Test
  public void testDown() {
    final int[][] queues = {new int[0], // G
                            new int[0], // 1
                            new int[] {1, 1}, // 2
                            new int[0], // 3
                            new int[0], // 4
                            new int[0], // 5
                            new int[0], // 6
    };
    final int[] result = new Elevator().goElevatorGo(queues, 5);
    assertArrayEquals(new int[] {0, 2, 1, 0}, result);
  }
  
  @Test
  public void testUpAndUp() {
    final int[][] queues = {new int[0], // G
                            new int[] {3}, // 1
                            new int[] {4}, // 2
                            new int[0], // 3
                            new int[] {5}, // 4
                            new int[0], // 5
                            new int[0], // 6
    };
    final int[] result = new Elevator().goElevatorGo(queues, 5);
    assertArrayEquals(new int[] {0, 1, 2, 3, 4, 5, 0}, result);
  }
  
  @Test
  public void testDownAndDown() {
    final int[][] queues = {new int[0], // G
                            new int[] {0}, // 1
                            new int[0], // 2
                            new int[0], // 3
                            new int[] {2}, // 4
                            new int[] {3}, // 5
                            new int[0], // 6
    };
    final int[] result = new Elevator().goElevatorGo(queues, 5);
    assertArrayEquals(new int[] {0, 5, 4, 3, 2, 1, 0}, result);
  }
  
  @Test
  public void testUpAndDown_1() {
    final int[][] queues = {new int[0], // G
                            new int[] {2}, // 1
                            new int[] {1, 4}, // 2
                            new int[0], // 3
                            new int[] {2}, // 4
                            new int[0], // 5
                            new int[0], // 6
    };
    final int[] result = new Elevator().goElevatorGo(queues, 5);
    assertArrayEquals(new int[] {0, 1, 2, 4, 2, 1, 0}, result);
  }
  
  @Test
  public void testUpAndDown_2() {
    final int[][] queues = {new int[0], // G
                            new int[] {4}, // 1
                            new int[] {1, 3}, // 2
                            new int[0], // 3
                            new int[] {3}, // 4
                            new int[0], // 5
                            new int[0], // 6
    };
    final int[] result = new Elevator().goElevatorGo(queues, 5);
    assertArrayEquals(new int[] {0, 1, 2, 3, 4, 3, 2, 1, 0}, result);
  }
  
  @Test
  public void testUpAndDown_3() {
    final int[][] queues = {new int[0], // G
                            new int[] {5, 6}, // 1
                            new int[] {1, 3}, // 2
                            new int[0], // 3
                            new int[] {1}, // 4
                            new int[0], // 5
                            new int[] {1}, // 6
    };
    final int[] result = new Elevator().goElevatorGo(queues, 5);
    assertArrayEquals(new int[] {0, 1, 2, 3, 4, 5, 6, 4, 2, 1, 0}, result);
  }
  
  @Test
  public void testUpFromGround() {
    final int[][] queues = {new int[] {3, 2}, // G
                            new int[0], // 1
                            new int[0], // 2
                            new int[0], // 3
                            new int[0], // 4
                            new int[0], // 5
                            new int[0], // 6
    };
    final int[] result = new Elevator().goElevatorGo(queues, 5);
    assertArrayEquals(new int[] {0, 2, 3, 0}, result);
  }
  
  private void assertArrayEquals(int[] is, int[] result) {
    assertTrue(Arrays.equals(is, result));
  }
  
}
