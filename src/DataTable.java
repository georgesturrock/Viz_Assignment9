// class DrawTable use to build a data table
// position in the screen can be defined 
// number of columns are defined by the table used to populate the data table
// orientation vertical/horizontal is also dynamic
import processing.core.PApplet;
import processing.core.PConstants;
import processing.data.Table;
import processing.data.TableRow;

public class DataTable {
	int i =0;
	int distanceCell = 35;
	 
	// size of a cell
	int sizeCellX = 36;
	int sizeCellY = 27;
	int xScreen=0;
	int yScreen=0;
	int text_size = 12; 
	boolean vertical=true;
	Table dataCells;
	PApplet parent;
	         
	//Class Constructors
	DataTable(){    
	}
	
	DataTable(PApplet p){
		this.parent = p;
	}
	
	// with position definition
	DataTable(int xScreen, int yScreen){
		this.xScreen = xScreen;
	    this.yScreen = yScreen;
	}
	  
	DataTable(int xScreen, int yScreen, int sizeCellX, int sizeCellY){
	    this.xScreen = xScreen;
	    this.yScreen = yScreen;
	    this.sizeCellX = sizeCellX;
	    this.sizeCellY = sizeCellY;
	}
	  
	DataTable(int xScreen, int yScreen, int sizeCellX, int sizeCellY, Table dataCells){
	    this.xScreen = xScreen;
	    this.yScreen = yScreen;
	    this.sizeCellX = sizeCellX;
	    this.sizeCellY = sizeCellY;
	    this.dataCells = dataCells;    
	}
	  
	//Class Functions
	// Number of columns are defined by the table passed as parameter 
	void drawTable(int posx, int posy, Table dataCells, Boolean vertical){
	    int distanceCell = this.sizeCellX-1;
	    int i=0, numCols=1;
	    // initial x and y positions
	    this.xScreen = posx;
	    this.yScreen = posy;
	    this.dataCells = dataCells;
	    this.vertical = vertical;
	    
	    // draw header
	    numCols = dataCells.getColumnCount();        
	    for (int cell=0; cell<numCols; cell++) { 
	    	parent.textSize(this.text_size);
	    	parent.textAlign(PConstants.LEFT);
	    	parent.fill(255);
	    	parent.stroke(parent.color(71,49,93));
	    	parent.rect(this.xScreen, this.yScreen, this.sizeCellX+10, this.sizeCellY);
	    	parent.fill(0);
	    	parent.text(this.dataCells.getColumnTitle(cell), xScreen+8, yScreen+16);
	        if (vertical) {  // vertical
	          this.xScreen = this.xScreen+distanceCell+10;
	        }
	        else {   // horizontal
	          this.yScreen += this.sizeCellY+1;
	        }
	    }
	    
	    // new line
	    if (vertical) { 
	      this.xScreen=posx;   // back to left side again
	      this.yScreen += this.sizeCellY+1;  //  new line
	    }
	    else {  // horizontal
	      this.xScreen += distanceCell+10;   // back to left side again
	      this.yScreen = posy;  //  new line
	    }
	    for (TableRow row : this.dataCells.rows()) {
	      for (int x=0; x < numCols; x++) { // build rows with numCols + sequence number cells
	    	parent.fill(255);
	    	parent.stroke(parent.color(71,49,93));
	    	parent.rect(this.xScreen, this.yScreen, this.sizeCellX, this.sizeCellY);
	    	parent.fill(0);
	    	parent.text(dataCells.getString(i,x), this.xScreen+8, this.yScreen+16);
	    
	        if (vertical) {
	          // just move to the right 
	          this.xScreen = this.xScreen + distanceCell;
	        }
	        else {   // horizontal
	          // just move to the bottom
	          this.yScreen += this.sizeCellY+1; 
	        }
	      }
	      if (vertical) {
	        // new line
	        this.xScreen=posx;   // back to left side again
	        this.yScreen += this.sizeCellY+1;  //  new line
	      }
	      else {  // horizontal
	        // new line
	        this.xScreen += distanceCell;  // back to left side again
	        this.yScreen = posy;  //  new line
	      }
	      i++;
	    } // for      
	  }  

}
