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
import {StaticLookupCall} from '@eclipse-scout/core';

export default class ChildActionsLookupCall extends StaticLookupCall {

  constructor(action) {
    super();
    this._actionPropertyChangeHandler = this._onActionPropertyChange.bind(this);
    this._childActionPropertyChangeHandler = this._onChildActionPropertyChange.bind(this);
    this.data = [];
    this.setAction(action);
  }

  _data() {
    return this.data;
  }

  setAction(action) {
    if (this.action) {
      this.action.off('propertyChange', this._actionPropertyChangeHandler);
      this.action.childActions.forEach(action => action.off('propertyChange', this._childActionPropertyChangeHandler));
    }
    this.action = action;
    if (this.action) {
      this.action.on('propertyChange', this._actionPropertyChangeHandler);
      this.action.childActions.forEach(action => action.on('propertyChange', this._childActionPropertyChangeHandler));
    }
    this._rebuildData();
  }

  _rebuildData() {
    if (!this.action) {
      this.data = [];
      return;
    }
    this.data = this.action.childActions.map(menu => {
      return [menu, menu.text];
    });
  }

  _onActionPropertyChange(event) {
    if (event.propertyName === 'childActions') {
      this._rebuildData();
    }
  }

  _onChildActionPropertyChange(event) {
    if (event.propertyName === 'text') {
      this._rebuildData();
    }
  }
}
