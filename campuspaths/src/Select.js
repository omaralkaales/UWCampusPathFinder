/*  Copyright ©2019 Omar Alkaales. All rights reserved.
*
*   
*
*   The component that have the select button component and it's items list
*/


import React, {Component} from 'react';
import {MenuItem, Select} from "@material-ui/core";

// This is an example of using the <Select>
// MaterialUI component in React to create a drop-down
// list.
class SelectCustom extends Component {

  constructor(props) {
    super(props);
    this.state = {
      selection: ""
    };
  }

  // Creates an array of <MenuItem /> components. This makes it
  // easier to put them into the <Select />, which requires that there be
  // MenuItems inside of it.
  generateMenuItems = () => {
    let buildingNames = this.props.buildings;
    let menuItems = [];
    let keyid = 0;
    for (let key in buildingNames) {
      let longName = buildingNames[key];
      // Creates a <MenuItem> component and stores it in a variable.
      // The "value" prop decides what get's returned by the <Select>
      // when this item is selected. The "children" (what's inside of the
      // MenuItem) decides what's displayed in the item.
      // We want to use the short name when working with data, but
      // use the long name in the actual menu to be user-friendly.
      let menuItemComponent = (
        <MenuItem key={keyid} value={key}>
          {longName}
        </MenuItem>
      );
      keyid++;
      // Let's add this component to an array.
      // You can just directly put the array of components inside the
      // <Select> (down below) and it'll grab them all and use them to
      // create the drop-down menu.
      menuItems.push(menuItemComponent);
    }
    return menuItems;
  };

  render() {
    let menuItems = this.generateMenuItems(); // Calling the helper function.
    return (
      <div>
        <Select onChange={this.props.onChange} value={this.props.value}>
          {menuItems}
        </Select>
      </div>
    );
  }
}

export default SelectCustom;