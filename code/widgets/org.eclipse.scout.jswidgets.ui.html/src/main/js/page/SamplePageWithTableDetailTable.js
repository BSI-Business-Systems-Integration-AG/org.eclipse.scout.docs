import {scout, Table} from '@eclipse-scout/core';

export default class SamplePageWithTableDetailTable extends Table {

  constructor() {
    super();
  }

  _init(model) {
    super._init(model);

    this.widget('AddRowMenu').on('action', this._onAddRowMenuAction.bind(this));
    this.widget('AddManyMenu').on('action', this._onAddManyMenuAction.bind(this));
    this.widget('DeleteRowMenu').on('action', this._onDeleteRowMenuAction.bind(this));
    let formMenu = this.widget('FormMenu');
    formMenu.on('propertyChange:selected', event => {
      if (event.newValue && !formMenu.form) {
        formMenu.setForm(scout.create('jswidgets.MiniForm', {
          parent: formMenu
        }));
      }
    });
  }

  createTileForRow(row) {
    let model = {
      parent: this,
      content: '<br><b>ID:</b> ' +
        row.data.id + '<br><b>String Column:</b> ' +
        row.data.string + '<br><b>Number Column:</b> ' +
        row.data.number + '<br><b>Boolean Column:</b> ' +
        row.data.bool
    };
    return scout.create('HtmlTile', model);
  }

  _onAddRowMenuAction() {
    this.insertRow(this._createRow());
  }

  _onAddManyMenuAction() {
    let rows = [];
    for (let i = 0; i < 10; i++) {
      rows.push(this._createRow(this.rows.length + i));
    }
    this.insertRows(rows);
  }

  _onDeleteRowMenuAction() {
    this.deleteRows(this.selectedRows);
  }

  _createRow(rowNo) {
    rowNo = scout.nvl(rowNo, this.rows.length + 1);
    let smartValues = [null, 'es_CR', null, 'pt_BR', 'ro_RO'];

    let row = {
      id: this.rows.length + 1,
      string: 'string ' + rowNo,
      smartValue: smartValues[rowNo % smartValues.length],
      number: Math.floor(Math.random() * Math.floor(999999)),
      bool: Math.random() >= 0.5
    };
    return {
      data: row,
      cells: [
        row.id,
        row.string,
        row.smartValue,
        row.number,
        row.bool
      ]
    };
  }
}
