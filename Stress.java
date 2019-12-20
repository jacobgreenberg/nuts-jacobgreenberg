

import java.util.List;
import java.util.ArrayList;


public class Stress
{
  public static void main(String [] args)
  {
    int n = 5000000;
    List<Integer> ns = new ArrayList<Integer>();
    for(int i=0; i<n; i++)
      ns.add((int) (Math.random()*10000000));
    long startTime = System.currentTimeMillis();
    Tree<Integer> t = new Tree<>(ns, false);
    List<Integer> it = t.sort();
    long time = System.currentTimeMillis();
    long memoryUsed = Runtime.getRuntime().totalMemory()
                    - Runtime.getRuntime().freeMemory();
    System.out.println("@@ n = " + n + " time " + (time - startTime) 
                       + "ms, using " + memoryUsed/(1024*1024) + "Mb");

    System.out.println("it.length = " + it.size());
    int last = it.get(0);
    for(int v : it)
    {
      if (last > v)
      {
        System.out.println("OOPS! " + last + " " + v);
        break;
      }
      last = v;
    }
  }
}
