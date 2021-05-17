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
import {GroupBox, MenuBarBox, models} from '@eclipse-scout/core';
import ActionPropertiesBoxModel from './ActionPropertiesBoxModel';

export default class ActionPropertiesBox extends GroupBox {

  constructor() {
    super();
    this.field = null;
  }

  _jsonModel() {
    return models.get(ActionPropertiesBoxModel);
  }

  _init(model) {
    super._init(model);

    this._setField(this.field);
  }

  setField(field) {
    this.setProperty('field', field);
  }

  // noinspection DuplicatedCode
  _setField(field) {
    this._setProperty('field', field);
    this.setEnabled(this.field);
    if (!this.field) {
      return;
    }
    let enabledField = this.widget('EnabledField');
    enabledField.setValue(this.field.enabled);
    enabledField.on('propertyChange', this._onPropertyChange.bind(this));

    let visibleField = this.widget('VisibleField');
    visibleField.setValue(this.field.visible);
    visibleField.on('propertyChange', this._onPropertyChange.bind(this));

    let toggleActionField = this.widget('ToggleActionField');
    toggleActionField.setValue(this.field.toggleAction);
    toggleActionField.on('propertyChange', this._onPropertyChange.bind(this));

    let selectedField = this.widget('SelectedField');
    selectedField.setValue(this.field.selected);
    selectedField.on('propertyChange', this._onPropertyChange.bind(this));

    let preventDoubleClickField = this.widget('PreventDoubleClickField');
    preventDoubleClickField.setValue(this.field.preventDoubleClick);
    preventDoubleClickField.on('propertyChange', this._onPropertyChange.bind(this));

    let inheritAccessibilityField = this.widget('InheritAccessibilityField');
    inheritAccessibilityField.setValue(this.field.inheritAccessibility);
    inheritAccessibilityField.on('propertyChange', this._onPropertyChange.bind(this));

    let iconIdField = this.widget('IconIdField');
    iconIdField.setValue(this.field.iconId);
    iconIdField.on('propertyChange', this._onPropertyChange.bind(this));

    let keyStrokeField = this.widget('KeyStrokeField');
    keyStrokeField.setValue(this.field.keyStroke);
    keyStrokeField.on('propertyChange', this._onPropertyChange.bind(this));

    let textField = this.widget('TextField');
    textField.setValue(this.field.text);
    textField.on('propertyChange', this._onPropertyChange.bind(this));

    let textPositionField = this.widget('TextPositionField');
    textPositionField.setValue(this.field.textPosition);
    textPositionField.on('propertyChange', this._onPropertyChange.bind(this));

    let tooltipTextField = this.widget('TooltipTextField');
    tooltipTextField.setValue(this.field.tooltipText);
    tooltipTextField.on('propertyChange', this._onPropertyChange.bind(this));

    let horizontalAlignmentField = this.widget('HorizontalAlignmentField');
    horizontalAlignmentField.setValue(this.field.horizontalAlignment);
    horizontalAlignmentField.on('propertyChange', this._onPropertyChange.bind(this));

    let actionStyleField = this.widget('ActionStyleField');
    actionStyleField.setValue(this.field.actionStyle);
    actionStyleField.on('propertyChange', this._onPropertyChange.bind(this));
  }

  _onPropertyChange(event) {
    if (event.propertyName === 'value' && event.source.id === 'EnabledField') {
      this.field.setEnabled(event.newValue);
    } else if (event.propertyName === 'value' && event.source.id === 'VisibleField') {
      this.field.setVisible(event.newValue);
    } else if (event.propertyName === 'value' && event.source.id === 'ToggleActionField') {
      this.field.setToggleAction(event.newValue);
    } else if (event.propertyName === 'value' && event.source.id === 'SelectedField') {
      this.field.setSelected(event.newValue);
    } else if (event.propertyName === 'value' && event.source.id === 'PreventDoubleClickField') {
      this.field.setPreventDoubleClick(event.newValue);
    } else if (event.propertyName === 'value' && event.source.id === 'InheritAccessibilityField') {
      this.field.setInheritAccessibility(event.newValue);
    } else if (event.propertyName === 'value' && event.source.id === 'IconIdField') {
      this.field.setIconId(event.newValue);
    } else if (event.propertyName === 'value' && event.source.id === 'KeyStrokeField') {
      this.field.setKeyStroke(event.newValue);
    } else if (event.propertyName === 'value' && event.source.id === 'TextField') {
      this.field.setText(event.newValue);
    } else if (event.propertyName === 'value' && event.source.id === 'TextPositionField') {
      this.field.setTextPosition(event.newValue);
    } else if (event.propertyName === 'value' && event.source.id === 'TooltipTextField') {
      this.field.setTooltipText(event.newValue);
    } else if (event.propertyName === 'value' && event.source.id === 'HorizontalAlignmentField') {
      this.field.setHorizontalAlignment(event.newValue);
    } else if (event.propertyName === 'value' && event.source.id === 'ActionStyleField') {
      // ActionStyle may not be changed during run time officially, use this little hack to work around by rerendering the whole menu bar
      this.field.actionStyle = event.newValue;
      if (this.field.parent instanceof MenuBarBox) {
        let menuItems = this.field.parent.menuItems;
        this.field.parent.setMenuItems([]);
        this.field.parent.setMenuItems(menuItems);
      }
    }
  }
}
