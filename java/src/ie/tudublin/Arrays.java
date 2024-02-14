package ie.tudublin;

import processing.core.PApplet;



public class Arrays extends PApplet
{
	int mode = 0;

	String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

	float[] rainfall = {45, 37, 55, 25, 37, 50, 80, 50, 105, 30, 100, 60};

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
		switch(mode)
		{
			case 0:
				background(0);
				//display message for options
				fill(255);
				textSize(20);
				textAlign(CENTER, CENTER);
				text("Press 1 for Bar Chart", 250, 200);
				text("Press 2 for Line Chart", 250, 250);
				text("Press 3 for Pie Chart", 250, 300);
			break;

			case 1: //first mode
				background(0);

				//draw graph axis
				stroke(0, 0, 255);
				line(50, 50, 50, 450);
				line(50, 450, 450, 450);
				textSize(15);
				
				//draw graph labels to the left of the y axis with the rainfall values from 0 to 120 increasing in 10s
				for(int i = 0 ; i <= 120 ;  i += 10)
				{
					fill(0,0, 255); // sets the colour of the text
					float y = map1(i, 0, 120, 450, 50); // maps the rainfall values to the height of the graph
					textAlign(RIGHT, CENTER); // sets the alignment of the text
					text(i, 40, y); // draws the rainfall values to the left of the y axis
					stroke(0,0,255);
					line(45, y, 50, y);
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
					hue += 20; // increments the hue value
				}
			break;

			case 2:
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
					stroke(0,0,255);
					line(45, y, 50, y);
				}

				//draw trend lines and months underneath the x axis
				float w1 = lenXaxis / (float)months.length; // sets the width of the bars to the width of the graph divided by the number of months
				for (int i = 1; i < months.length; i++)
				{
					float x1 = map1(i-1, 0, months.length, 50, 450); // maps the months to the width of the graph
					float y1 = map1(rainfall[i-1], 0, 120, 0, 400); // maps the rainfall values to the height of the graph
					float x2 = map1(i, 0, months.length, 50, 450); // maps the next month to the width of the graph
					float y2 = map1(rainfall[i], 0, 120, 0, 400); // maps the next rainfall value to the height of the graph


					stroke(0, 0, 255); // sets the colour of the trend lines
					line(x1 + w1 * 0.5f, 450 - y1, x2 + w1 * 0.5f, 450 - y2); // draws the trend lines
					fill(0,0,255); // sets the colour of the text
					textAlign(CENTER, CENTER); // sets the alignment of the text
					text(months[i-1], x1 + w1 * 0.5f, 460); // draws the months underneath the x axis
					if (i == months.length - 1) // if the month is the last month
					{
						text(months[i], x2 + w1 * 0.5f, 460); // draws the months underneath the x axis
					}
				}
			break;
			case 3:
				background(0);

				//draw cirlce for pichart
				float total = 0;
				for (int i = 0; i < rainfall.length; i++)
				{
					total += rainfall[i];
				}

				float x = 250;
				float y = 250;
				float radius = 200;
				float lastAngle = 0;
				for (int i = 0; i < rainfall.length; i++)
				{
					float angle = map1(rainfall[i], 0, total, 0, TWO_PI); // maps the rainfall values to the angle of the circle segments 
					fill(map1(i, 0, rainfall.length, 0, 255), 255, 255); // sets the colour of the segments of the circle to a hue value
					arc(x, y, radius * 2, radius * 2, lastAngle, lastAngle + angle); // draws the segments of the circle with the rainfall values
					lastAngle += angle; // increments the angle
				}

				//print the months outside the circle
				for (int i = 0; i < rainfall.length; i++)
				{
					float angle = map1(rainfall[i], 0, total, 0, TWO_PI); // maps the rainfall values to the angle of the circle segments
					float midAngle = lastAngle + angle * 0.5f; // calculates the middle angle of the circle segments
					float x1 = x + cos(midAngle) * 450 * 0.5f; // calculates the x position of the text
					float y1 = y + sin(midAngle) * 450 * 0.5f; // calculates the y position of the text
					fill(0,0,255); // sets the colour of the text
					textAlign(CENTER, CENTER); // sets the alignment of the text
					text(months[i], x1, y1); // draws the months outside the circle
					lastAngle += angle; // increments the angle
				}
				
			break;
				
		}
	}

	public void keyPressed()
    {
        
        if (key >= '0' && key <= '9')
        {
            mode = key - '0';
        }
    }
}