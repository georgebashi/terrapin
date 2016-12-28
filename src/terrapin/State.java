package terrapin;

// Encapsulate state of terrapin to push and pop.

class State {
  float mX, mY, mRotation;
  int mDrawColor;
  boolean mDrawing;

  // Constructor from a terrapin
  State(Terrapin t) {
    mX = t.x;
    mY = t.y;
    mRotation = t.rotation;
    mDrawColor = t.drawColor;
    mDrawing = t.drawing;
  }    
}

