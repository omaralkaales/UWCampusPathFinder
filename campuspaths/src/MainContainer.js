/*
 * Copyright ©2019 Omar Alkaales. All rights reserved.
 *
 *
/* The main component that holds all the other elements of the React App */


import React, {Component} from 'react';
import Button from './Button';
import Map from './Map'
import * as fetch from "node-fetch";
import Select from './Select';



class MainContainer extends Component {
    constructor(props) {
      super(props);
      this.buildingNamesReq = this.buildingNamesReq.bind(this);
      this.findPath = this.findPath.bind(this);
      this.state = {
          buildingNames : "",
          shortestPath : "",
          start : "",
          dest : ""
    }
    this.buildingNamesReq();

    }


    buildingNamesReq() {
        fetch('http://localhost:4567/buildingNames')
        .then((res) => {
            if (!res.ok) {
                alert ("there is a bad request");
            } else {
                return res.json();
            }
            }).then((resJson) => {
            this.setState({
            buildingNames : resJson
            });
        }
        ).catch((error) => {
        alert(error);
        });
    }

    findPath() {
        let url = 'http://localhost:4567/findPath?start='+this.state.start+'&end='+this.state.dest;
        fetch(url)
        .then((res) => {
            if (!res.ok) {
                alert ("there is a bad request");
            } else {
                return res.json();
            }
            }).then((resJson) => {
            this.setState({
            shortestPath : resJson
            });
        }
        ).catch((error) => {
        alert(error);
        });
        
    }


    render() {
      return (
          <div>
            <div><Map newPath = {this.state.shortestPath}></Map> </div>
            <br></br>
            <div>
            <Button variant="contained" color="primary" onClick={() => {this.findPath()}} 
            value="Find path" />
            <br></br>
            <Button variant="contained" color="secondary" onClick={() => {
                this.setState({shortestPath:"",start:"",dest:""})
            }} value="Clear map" />
            </div>
            <br></br>
                <div className="select-list">
                        From: {this.state.start} <Select buildings = {this.state.buildingNames} 
                        onChange={(event) => { this.setState({start : event.target.value})}}
                        value = {this.state.start}></Select> 
                        <br></br>
                        To: {this.state.dest} <Select buildings = {this.state.buildingNames}
                        onChange={(event) => { this.setState({dest : event.target.value})}} 
                        value = {this.state.dest}></Select> 
                </div>
          </div>);
    }
  }
  
  export default MainContainer;
  