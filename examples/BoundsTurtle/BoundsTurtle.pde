import turtle.Turtle;    // always import the turtle library

Turtle t;    // declare the turtle

void setup() {
  size(600, 600);        // set the size of the drawing window
  background(0);        // paint the drawing window black

  t = new Turtle(this);  // create the turtle
}

void draw() {
  // make the turtle draw a jagged line
  t.forward(20);
  t.right(20);
  t.forward(20);
  t.left(20);

  // if the turtle has gone out of the drawing window, move it
  if(t.x > width) t.x = 0; // turtle went off the right side, move it to the left
  if(t.x < 0) t.x = width;  // turtle went off the left side, move it to the right
  if(t.y > height) t.y = 0;
  if(t.y < 0) t.y = height;

  delay(125);       // wait for an eight of a second
}

