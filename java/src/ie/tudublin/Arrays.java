package ie.tudublin;

import processing.core.PApplet;



public class Arrays extends PApplet
{
	String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

	float[] rainfall = {45, 37, 55, 25, 37, 40, 50, 80, 50, 105, 30, 60};

	float lenYaxis = 400;
	float lenXaxis = 400;

	public float map1(float a, float b, float c, float d, float e)
	{
		float r1 = c -b;
		float r2 = e - d;

		float howFar = a - b;
		return d + (howFar / r1) * r2;
	}

	void randomize()
	{
		for (int i = 0; i < rainfall.length; i++) {
			rainfall[i] = random(500);
		}
	}

	public void settings()
	{
		size(500, 500);


	

		for(int i = 0; i < months.length; i ++)
		{
			println("Month: " + months[i] + "\t" + rainfall[i]);
		}


		int minIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] < rainfall[minIndex])
			{
				minIndex = i;
			}
		}
		
		int maxIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] > rainfall[maxIndex])
			{
				maxIndex = i;
			}
		}

		println("The month with the minimum rainfall was " + months[minIndex] + " with " + rainfall[minIndex] + "rain");
		println("The month with the max rainfall was " + months[maxIndex] + " with " + rainfall[maxIndex] + "rain");
		
		
		float tot = 0;
		for(float f:rainfall)
		{
			tot += f;
		}

		float avg = tot / (float) rainfall.length;

		// a b-c d-e;
		println(map1(5, 0, 10, 0, 100));
		// 50

		println(map1(25, 20, 30, 200, 300));
		// 250

		println(map1(26, 25, 35, 0, 100));
		// 10 


	}

	public void setup() {
		colorMode(HSB);
		background(0);
		//randomize();
		
		
	}

	
	public void draw()
	{	

		background(0);

		//draw graph axis
		stroke(0, 0, 255);
		line(50, 50, 50, 450);
		line(50, 450, 450, 450);
		
		//draw graph labels to the left of the y axis with the rainfall values from 0 to 120 increasing in 10s
		for(int i = 0 ; i <= 120 ;  i += 10)
		{
			fill(0,0, 255); // sets the colour of the text
			float y = map1(i, 0, 120, 450, 50); // maps the rainfall values to the height of the graph
			textAlign(RIGHT, CENTER); // sets the alignment of the text
			text(i, 40, y); // draws the rainfall values to the left of the y axis
		}

		//draw the graph
		float w = lenXaxis / (float)months.length; // sets the width of the bars to the width of the graph divided by the number of months
		float hue = 0; // sets the hue value to 0

		//draw the bars with the months underneath the x axis
		for(int i = 0; i < months.length; i ++)
    {
        float x = map1(i, 0, months.length, 50, 450); // maps the months to the width of the graph
        float h = map1(rainfall[i], 0, 120, 0, 400); // maps the rainfall values to the height of the graph
        fill(hue, 255, 255); // sets the colour of the bars
        rect(x, 450 - h, w, h); // draws the bars
        fill(0,0,255); // sets the colour of the text
        textAlign(CENTER, CENTER); // sets the alignment of the text
        text(months[i], x + w * 0.5f, 460); // draws the months underneath the x axis
        hue += 18; // increments the hue value
    }
	}
}