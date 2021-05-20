/*
 * Copyright (c) 2020 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 */
import {GroupBox, models} from '@eclipse-scout/core';
import MenuActionsBoxModel from './MenuActionsBoxModel';
import ChildActionsLookupCall from '../action/ChildActionsLookupCall';

export default class MenuActionsBox extends GroupBox {

  constructor() {
    super();
    this.menu = null;
  }

  _jsonModel() {
    return models.get(MenuActionsBoxModel);
  }

  _init(model) {
    super._init(model);
    let insertMenuButton = this.widget('InsertMenuButton');
    insertMenuButton.on('click', this._onInsertMenuClick.bind(this));

    let deleteMenuButton = this.widget('DeleteMenuButton');
    deleteMenuButton.on('click', this._onDeleteMenuClick.bind(this));

    let menuToDeleteField = this.widget('MenuToDeleteField');
    menuToDeleteField.setLookupCall(new ChildActionsLookupCall(this.menu));
    this._setMenu((this.menu));
  }

  setMenu(menu) {
    this.setProperty('menu', menu);
  }

  _setMenu(menu) {
    this._setProperty('menu', menu);
    this.widget('MenuToDeleteField').lookupCall.setAction(this.menu);
  }

  _onInsertMenuClick(event) {
    this.menu.insertChildAction({
      objectType: 'Menu',
      text: 'Menu ' + (this.menu.childActions.length + 1)
    });
  }

  _onDeleteMenuClick(event) {
    let menuToDeleteField = this.widget('MenuToDeleteField');
    let menu = menuToDeleteField.value;
    this.menu.deleteChildAction(menu);
    // Select last entry in the lookup rows
    menuToDeleteField.lookupCall.getAll().then(result => {
      let rows = result.lookupRows;
      let newValue = null;
      if (rows.length > 0) {
        newValue = rows[rows.length - 1].key;
      }
      menuToDeleteField.setValue(newValue);
    });
  }
}
