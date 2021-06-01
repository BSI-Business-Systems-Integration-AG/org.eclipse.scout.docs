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
import {Form, models, scout} from '@eclipse-scout/core';
import StringFieldFormModel from './StringFieldFormModel';

export default class StringFieldForm extends Form {

  constructor() {
    super();
  }

  _jsonModel() {
    return models.get(StringFieldFormModel);
  }

  // noinspection DuplicatedCode
  _init(model) {
    super._init(model);

    let stringField = this.widget('StringField');
    stringField.on('selectionChange', this._onFieldSelectionChange.bind(this));
    stringField.on('action', this._onFieldAction.bind(this));

    let hasActionField = this.widget('HasActionField');
    hasActionField.setValue(stringField.hasActionField);
    hasActionField.on('propertyChange', this._onHasActionPropertyChange.bind(this));

    let inputMaskedField = this.widget('InputMaskedField');
    inputMaskedField.setValue(stringField.inputMasked);
    inputMaskedField.on('propertyChange', this._onInputMaskedPropertyChange.bind(this));

    let multilineTextField = this.widget('MultilineTextField');
    multilineTextField.setValue(stringField.multilineText);
    multilineTextField.on('propertyChange', this._onMultilineTextPropertyChange.bind(this));

    let spellCheckEnabledField = this.widget('SpellCheckEnabledField');
    spellCheckEnabledField.setValue(stringField.spellCheckEnabled);
    spellCheckEnabledField.on('propertyChange', this._onSpellCheckEnabledPropertyChange.bind(this));

    let trimTextField = this.widget('TrimTextField');
    trimTextField.setValue(stringField.trimText);
    trimTextField.on('propertyChange', this._onTrimTextPropertyChange.bind(this));

    let updateDisplayTextOnModifyField = this.widget('UpdateDisplayTextOnModifyField');
    updateDisplayTextOnModifyField.setValue(stringField.updateDisplayTextOnModify);
    updateDisplayTextOnModifyField.on('propertyChange', this._onUpdateDisplayTextOnModifyPropertyChange.bind(this));

    let formatField = this.widget('FormatField');
    formatField.setValue(stringField.format);
    formatField.on('propertyChange', this._onFormatPropertyChange.bind(this));

    let maxLengthField = this.widget('MaxLengthField');
    maxLengthField.setValue(stringField.maxLength);
    maxLengthField.on('propertyChange', this._onMaxLengthPropertyChange.bind(this));

    let selectionTrackingEnabledField = this.widget('SelectionTrackingEnabledField');
    selectionTrackingEnabledField.setValue(stringField.selectionTrackingEnabled);
    selectionTrackingEnabledField.on('propertyChange', this._onSelectionTrackingEnabledPropertyChange.bind(this));

    let selectionStartField = this.widget('SelectionStartField');
    selectionStartField.setValue(stringField.selectionStart);
    selectionStartField.on('propertyChange', this._onSelectionStartPropertyChange.bind(this));

    let selectionEndField = this.widget('SelectionEndField');
    selectionEndField.setValue(stringField.selectionEnd);
    selectionEndField.on('propertyChange', this._onSelectionEndPropertyChange.bind(this));

    let blockFormatField = this.widget('BlockFormatField');
    blockFormatField.setValue(stringField.format);
    blockFormatField.on('propertyChange', this._onBlockFormatPropertyChange.bind(this));

    this.widget('ValueField').setEnabled(true);
    this.widget('ValueFieldPropertiesBox').setField(stringField);
    this.widget('FormFieldPropertiesBox').setField(stringField);
    this.widget('GridDataBox').setField(stringField);
    this.widget('WidgetActionsBox').setField(stringField);
    this.widget('FormFieldActionsBox').setField(stringField);
    this.widget('EventsTab').setField(stringField);
  }

  _onHasActionPropertyChange(event) {
    if (event.propertyName === 'value') {
      this.widget('StringField').setHasAction(event.newValue);
    }
  }

  _onInputMaskedPropertyChange(event) {
    if (event.propertyName === 'value') {
      this.widget('StringField').setInputMasked(event.newValue);
    }
  }

  _onMultilineTextPropertyChange(event) {
    if (event.propertyName === 'value') {
      let field = this.widget('StringField');
      field._setMultilineText(event.newValue);
      field.parent.rerenderControls();
      // Validate layout immediately to prevent flickering
      field.parent.htmlBody.validateLayoutTree();
    }
  }

  _onSpellCheckEnabledPropertyChange(event) {
    if (event.propertyName === 'value') {
      this.widget('StringField').setSpellCheckEnabled(event.newValue);
    }
  }

  _onTrimTextPropertyChange(event) {
    if (event.propertyName === 'value') {
      this.widget('StringField').setTrimText(event.newValue);
    }
  }

  _onUpdateDisplayTextOnModifyPropertyChange(event) {
    if (event.propertyName === 'value') {
      this.widget('StringField').setUpdateDisplayTextOnModify(event.newValue);
    }
  }

  _onFormatPropertyChange(event) {
    if (event.propertyName === 'value') {
      this.widget('StringField').setFormat(event.newValue);
    }
  }

  _onMaxLengthPropertyChange(event) {
    if (event.propertyName === 'value') {
      this.widget('StringField').setMaxLength(event.newValue);
    }
  }

  _onSelectionTrackingEnabledPropertyChange(event) {
    if (event.propertyName === 'value') {
      this.widget('StringField').setSelectionTrackingEnabled(event.newValue);
    }
  }

  _onSelectionStartPropertyChange(event) {
    if (event.propertyName === 'value') {
      let stringField = this.widget('StringField');
      stringField.focus();
      stringField.setSelectionStart(event.newValue);
    }
  }

  _onSelectionEndPropertyChange(event) {
    if (event.propertyName === 'value') {
      let stringField = this.widget('StringField');
      stringField.focus();
      stringField.setSelectionEnd(event.newValue);
    }
  }

  _onBlockFormatPropertyChange(event) {
    if (event.propertyName === 'value') {
      let stringField = this.widget('StringField');
      if (event.newValue) {
        stringField.setFormatter(StringFieldForm.blockFormatter);
        stringField.setParser(StringFieldForm.blockParser);
      } else {
        stringField.setFormatter(null);
        stringField.setParser(null);
      }
    }
  }

  _onFieldAction(event) {
    let msgBox = scout.create('scout.MessageBox', {
      parent: this,
      yesButtonText: this.session.text('Thanks') + '!',
      body: this.session.text('StringFieldHasActionMessage')
    });
    msgBox.open();
    msgBox.on('action', () => {
      msgBox.close();
    });
  }

  _onFieldSelectionChange(event) {
    let selectionStartField = this.widget('SelectionStartField');
    selectionStartField.setValue(event.selectionStart);
    let selectionEndField = this.widget('SelectionEndField');
    selectionEndField.setValue(event.selectionEnd);
  }

  static blockFormatter(value, defaultFormatter) {
    let displayText = defaultFormatter(value);
    if (!displayText) {
      return displayText;
    }
    return displayText.match(/.{4}/g).join('-');
  }

  static blockParser(displayText, defaultParser) {
    if (displayText) {
      return displayText.replace(/-/g, '');
    }
    return defaultParser(displayText);
  }
}
