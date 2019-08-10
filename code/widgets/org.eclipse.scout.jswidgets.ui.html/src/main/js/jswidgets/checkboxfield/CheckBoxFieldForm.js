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
jswidgets.CheckBoxFieldForm = function() {
  jswidgets.CheckBoxFieldForm.parent.call(this);
};
scout.inherits(jswidgets.CheckBoxFieldForm, scout.Form);

jswidgets.CheckBoxFieldForm.prototype._jsonModel = function() {
  return scout.models.getModel('jswidgets.CheckBoxFieldForm');
};

jswidgets.CheckBoxFieldForm.prototype._init = function(model) {
  jswidgets.CheckBoxFieldForm.parent.prototype._init.call(this, model);

  var field = this.widget('CheckBoxField');

  var triStateEnabledField = this.widget('TriStateEnabledField');
  triStateEnabledField.setValue(field.triStateEnabled);
  triStateEnabledField.on('propertyChange', this._onTriStateEnabledPropertyChange.bind(this));

  var wrapTextEnabledField = this.widget('WrapTextField');
  wrapTextEnabledField.setValue(field.wrapText);
  wrapTextEnabledField.on('propertyChange', this._onWrapTextPropertyChange.bind(this));

  var keyStrokeField = this.widget('KeyStrokeField');
  keyStrokeField.setValue(field.keyStroke);
  keyStrokeField.on('propertyChange', this._onKeyStrokePropertyChange.bind(this));

  this.widget('ValueFieldPropertiesBox').setField(field);
  this.widget('FormFieldPropertiesBox').setField(field);
  this.widget('GridDataBox').setField(field);
  this.widget('WidgetActionsBox').setField(field);
  this.widget('EventsTab').setField(field);
};

jswidgets.CheckBoxFieldForm.prototype._onTriStateEnabledPropertyChange = function(event) {
  if (event.propertyName === 'value') {
    this.widget('CheckBoxField').setTriStateEnabled(event.newValue);
  }
};

jswidgets.CheckBoxFieldForm.prototype._onWrapTextPropertyChange = function(event) {
  if (event.propertyName === 'value') {
    this.widget('CheckBoxField').setWrapText(event.newValue);
  }
};

jswidgets.CheckBoxFieldForm.prototype._onKeyStrokePropertyChange = function(event) {
  if (event.propertyName === 'value') {
    this.widget('CheckBoxField').setKeyStroke(event.newValue);
  }
};
