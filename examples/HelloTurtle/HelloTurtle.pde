import turtle.Turtle;    // always import the turtle library

Turtle t;    // declare the turtle

void setup() {
  size(600, 600);        // set the size of the drawing window
  background(0);        // paint the drawing window black
  
  t = new Turtle(this);  // create the turtle
}

void draw() {
  t.forward(200);    // move the turtle forward
  t.right(95);      // turn the turtle right
  
  delay(500);       // wait for half a second
}

