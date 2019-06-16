/*  Copyright ©2019 Omar Alkaales. All rights reserved.
*
*   
*
*   The component that holds the canvas and the draws the path on the campus image
*/
import React, {Component} from 'react';
import "./Map.css";


class Map extends Component {


  constructor(props) {
    super(props);
    this.backgroundImage = new Image();
    this.canvasReference = React.createRef();

    this.backgroundImage.onload = () => {
      // TODO: Do something when the image is ready?
      this.drawBackgroundImage();
    };
    this.backgroundImage.src = "campus_map.jpg"; // TODO: Fill this in.
  }

  drawBackgroundImage() {
    let canvas = this.canvasReference.current; // TODO Fill this in with the canvas, not the context.
    let ctx = canvas.getContext("2d");
    //
    if (this.backgroundImage.complete) { // This means the image has been loaded.
      canvas.width = this.backgroundImage.width;
      canvas.height = this.backgroundImage.height;
      ctx.drawImage(this.backgroundImage, 0, 0);
    }
  }

  componentDidUpdate() {
    this.drawPath();

  }

  drawPath() {
    let canvas = this.canvasReference.current;
    let paths = this.props.newPath;
    // get the path array from the Json object
    if (paths === "") {
      this.drawBackgroundImage();
    }
    let lines = paths.path;
    
    if (lines ===null || lines === undefined || lines.length === 0) {
      return;
    }
    let ctx = canvas.getContext('2d');

    // loop through each segment in the lines array and draw it on canvas
    for (let i=0; i< lines.length;i++) {
      let segment = lines[i];

      // extract coordinates for each segment
      let start_point_x = segment.start.x;
      let start_point_y = segment.start.y;
      let end_point_x = segment.end.x;
      let end_point_y = segment.end.y;

      // draw segment on canvas
      ctx.beginPath();
      ctx.moveTo(start_point_x, start_point_y);
      ctx.strokeStyle = "red"; 
      ctx.lineWidth = 5;       
      ctx.lineTo(end_point_x, end_point_y);
      ctx.stroke();

      
    }
  }

  render() {
    // TODO: You should put a <canvas> inside the <div>. It has a className
    // that's set up to center the canvas for you. See Map.css for more details.
    // Make sure you set up the React references for the canvas correctly so you
    // can get the canvas object and call methods on it.
    return (
      <div className="canvasHolder">
        <canvas ref={this.canvasReference}/>
      </div>
    )
  }
}

export default Map;