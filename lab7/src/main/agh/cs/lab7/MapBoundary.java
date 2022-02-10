package agh.cs.lab7;


import agh.cs.oop.IPositionChangeObserver;

import java.util.*;

class MapBoundary implements IPositionChangeObserver {
  private final xComparator xcomp = new xComparator();
  private final yComparator ycomp = new yComparator();
  private final SortedSet<Vector2d> x = new TreeSet<Vector2d>(xcomp);
  private final SortedSet<Vector2d> y = new TreeSet<Vector2d>(ycomp);
  public void addtoset(Vector2d a){
      x.add(a);
      y.add(a);
  }
  public Vector2d[] getboundaries(){
      return new Vector2d[] {x.first(), y.first(), x.last(), y.last()};
  }
  public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
      this.x.remove(oldPosition);
      this.x.add(newPosition);
      this.y.remove(oldPosition);
      this.y.add(newPosition);
  }

}
