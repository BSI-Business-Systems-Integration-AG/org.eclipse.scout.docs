/*
 * Copyright (c) 2017 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 */
import {Button, ButtonAdapterMenu, GroupBox, models, scout} from '@eclipse-scout/core';
import GroupBoxAddMenuBoxModel from './GroupBoxAddMenuBoxModel';

export default class GroupBoxAddMenuBox extends GroupBox {

  constructor() {
    super();
    this.field = null;
    this.dynamicMenuCounter = 0;
  }

  _jsonModel() {
    return models.get(GroupBoxAddMenuBoxModel);
  }

  _init(model) {
    super._init(model);
    this._setField(this.field);

    this.widget('MenuBarItemType').setValue('Menu');
  }

  setField(field) {
    this.setProperty('field', field);
  }

  _setField(field) {
    this._setProperty('field', field);
    if (!this.field) {
      return;
    }

    this.labelField = this.widget('LabelField');
    this.iconIdField = this.widget('IconIdField');
    this.horizontalAlignmentField = this.widget('HorizontalAlignmentField');
    this.stackableField = this.widget('StackableField');
    this.shrinkableField = this.widget('ShrinkableField');

    let addFieldButton = this.widget('CreateButton');
    addFieldButton.on('click', this._onAddMenuButtonClick.bind(this));

    this._updateAddMenuBarDefaultValues();
  }

  _updateAddMenuBarDefaultValues() {
    this.labelField.setValue('Dynamic Menu ' + this.dynamicMenuCounter);
    this.stackableField.setValue(true);
    this.shrinkableField.setValue(false);
  }

  _onAddMenuButtonClick(event) {
    let label = this.labelField.value || '';
    this.dynamicMenuCounter++;
    let newMenu = scout.create(scout.nvl(this.widget('MenuBarItemType').value, 'Menu'), {
      parent: this.field,
      id: 'DynMenu ' + this.dynamicMenuCounter,
      label: label,
      text: label,
      iconId: this.iconIdField.value,
      horizontalAlignment: this.horizontalAlignmentField.value,
      gridDataHints: {
        horizontalAlignment: this.horizontalAlignmentField.value
      },
      stackable: this.stackableField.value,
      shrinkable: this.shrinkableField.value
    });

    if (newMenu instanceof Button) {
      newMenu = scout.create('ButtonAdapterMenu',
        ButtonAdapterMenu.adaptButtonProperties(newMenu, {
          parent: this,
          menubar: this.menuBar,
          button: newMenu
        }));
    }

    let newMenuItems = this.field.menuBar.menuItems.slice();
    newMenuItems.splice(newMenuItems.length, 0, newMenu);
    this.field.setMenus(newMenuItems);

    this._updateAddMenuBarDefaultValues();
    // Validate layout immediately to prevent flickering
    this.field.validateLayoutTree();
  }
}
