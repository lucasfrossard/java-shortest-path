PURPOSE
=======

The purpose of this program is to make some experiments with some graphs algorithms. I made this as a solution for an exercise I found somewhere. Then a tried to remeber my grad student time where I learned algorithms related to shortest paths problem. I am pretty sure I could make those algorithm a litle bit more readable. Maybe I will do it later.


RUNNING
=======

After generating the jar, you may run this program by running the following comand:

java -jar RailService.jar "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7"

or by just running 

java -jar RailService.jar

This last option will run it with its defaul input.


DESIGN
======

This just a brief description for the solution:

There is a package com.lucasfrossard.railway.route.finder where all the algorithms
for finding shortest routes, or finding routes with a maximum number of stops or
a route with a maximum distance.

On the package com.lucasfrossard.railway.trip is the algorithm that calculate the
covered distance when the train moves.

The main funcion is in the RailroadServicesApp.java file that is within the package
com.lucasfrossard.railway.main

TESTS
=====

I made simple tests based on the input provided. Of course they should be more robust and
include other scenarios. As time was short I made the minimum acceptable ammount providing 
a reasonable code coverage, even though I definetely do not like that approach. The next step
would certainly be to improve those tests.

KNOWN ISSUES
============

Not sure if it is an issue, but I beleive the ShortestPath algorithm could be improved. 
The performance probably, and also the readability.
Some javadocs may be missing and all the other should be improved as well. So after improving 
the tests, improving the javadoc should be the next step. Together with some optmization 
for the shortest path algorithm.

Thank you, 

Bye!

