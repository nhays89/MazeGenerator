# MazeGenerator

This program builds mazes using spanning trees in a 2 dimensional grid of coordinates.

<strong> Depth-First Search</strong>
<p>This algorithm is a randomized version of the depth-first search algorithm. 
Frequently implemented with a stack, this approach is one of the simplest ways 
to generate a maze. Consider the space for a maze being a large
grid of cells (like a large chess board), each cell starting with four walls. 
Starting from a random cell, the algorithm selects a random neighbouring cell
that has not yet been visited. The algorithm removes the wall between the two cells
and marks the new cell as visited, and adds it to the stack to facilitate backtracking.
The algorithm continues this process, with a cell that has no unvisited neighbours being
considered a dead-end. When at a dead-end it backtracks through the path until it reaches
a cell with an unvisited neighbour, continuing the path generation by visiting this new,
unvisited cell (creating a new junction). This process continues until every cell has
been visited, causing the algorithm to backtrack all the way back to the beginning cell.</p>
<ol type="1">
  <li>Depth-first search algorithm</li>
  <li>Recursive back-tracker implemented with Java.Util.Stack</li>
    <ol type="1">
       1. Make the initial cell the current cell and mark it as visited </br>
       2. While there are unvisited cells </br>
        <ol>
          1. If the current cell has any neighbours which have not been visited </br>
            <ol>
                1. Choose randomly one of the unvisited neighbours <br>
                2. Push the current cell to the stack <br>
                3. Remove the wall between the current cell and the chosen cell <br>
                4. Make the chosen cell the current cell and mark it as visited <br>
            </ol>
          2. Else if stack is not empty </br>
            <ol>
              1. Pop a cell from the stack </br>
              2. Make it the current cell </br>
         </ol>
    </ol>
</ol>


***Maze***

![maze](https://github.com/nhays89/MazeGenerator/blob/master/maze.gif)

