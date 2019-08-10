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
jswidgets.CarouselForm = function() {
  jswidgets.CarouselForm.parent.call(this);
};
scout.inherits(jswidgets.CarouselForm, scout.Form);

jswidgets.CarouselForm.prototype._jsonModel = function() {
  return scout.models.getModel('jswidgets.CarouselForm');
};

jswidgets.CarouselForm.prototype._init = function(model) {
  jswidgets.CarouselForm.parent.prototype._init.call(this, model);

  var carousel = this.widget('Carousel');
  var statusEnabledField = this.widget('StatusEnabledField');
  statusEnabledField.setValue(carousel.statusEnabled);
  statusEnabledField.on('propertyChange', this._onStatusEnabledPropertyChange.bind(this));

  var carouselField = this.widget('CarouselField');
  this.widget('FormFieldPropertiesBox').setField(carouselField);
  this.widget('GridDataBox').setField(carouselField);
  this.widget('WidgetActionsBox').setField(carouselField);
  this.widget('EventsTab').setField(carousel);
};

jswidgets.CarouselForm.prototype._onStatusEnabledPropertyChange = function(event) {
  if (event.propertyName === 'value') {
    this.widget('Carousel').setStatusEnabled(event.newValue);
  }
};
