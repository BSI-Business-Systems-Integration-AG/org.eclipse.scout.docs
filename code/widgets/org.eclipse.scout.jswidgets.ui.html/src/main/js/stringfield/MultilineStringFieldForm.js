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
import {models} from '@eclipse-scout/core';
import {StringFieldForm} from '../index';
import MultilineStringFieldFormModel from './MultilineStringFieldFormModel';

export default class MultilineStringFieldForm extends StringFieldForm {

  constructor() {
    super();
  }

  _jsonModel() {
    let parentModel = super._jsonModel();
    return models.extend(MultilineStringFieldFormModel, parentModel);
  }
}