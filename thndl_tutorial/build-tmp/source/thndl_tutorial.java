import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class thndl_tutorial extends PApplet {

/*

 * This sketch and the accompanying shader file are 
 * intended as a support for the GLSL introductory ThNdl 
 * tutorials by Andrew Baldwin (find them at http://thndl.com/)
 
 * Original code \u00a92012 Andrew Baldwin
 * Processing port by Rapha\u00ebl de Courville
 
 * Tested in Processing 2.0b8
 * this sketch might not work anymore later releases.
 
 * Left-click to change the background
 
 */

PShader myShader;

PImage bg;

// If true, shows a textured background
// Else, the background is black 
// (left-click to switch)
Boolean isImageBackground = true;

public void setup() {
  size(250, 250, P2D);
  //size(512, 128, P2D); // for the "Noise From Numbers" chapter
  //size(512, 256, P2D); // for the "More Noise" chapter
  noSmooth();
  
  bg = loadImage("background.jpg");
  
  /*
  myShader = loadShader("shader.frag");
  myShader.set("resolution", float(width), float(height));
  shader(myShader); 
  */
}

public void draw() {
  if (isImageBackground == true)  { image(bg, 0, 0, width, height); }
  else                            { background(0); }

  // The code below would normally go in setup() as 
  // you would not need to load the shader on every 
  // frame but right now, this allows you to change 
  // the shader file and see the result in the active 
  // sketch window without reloading it every time.
  // Just edit and save shader.frag to apply the changes.
  myShader = loadShader("shader.frag");
  myShader.set("resolution", PApplet.parseFloat(width), PApplet.parseFloat(height));
  shader(myShader);
  
  noStroke();
  fill(0);
  rect(0, 0, width, height);  
  
  resetShader();
}

public void mousePressed() {
  isImageBackground = !isImageBackground;
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "thndl_tutorial" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
