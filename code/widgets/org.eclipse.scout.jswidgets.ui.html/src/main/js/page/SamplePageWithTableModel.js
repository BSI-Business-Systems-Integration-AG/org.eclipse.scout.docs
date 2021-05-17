import {icons} from '@eclipse-scout/core';

export default () => ({
  id: 'jswidgets.SamplePageWithTable',
  objectType: 'PageWithTable',
  text: 'Page with Table',
  detailTable: {
    id: 'jswidgets.SamplePageWithTable.Table',
    objectType: 'jswidgets.SamplePageWithTableDetailTable',
    columns: [
      {
        id: 'IdColumn',
        objectType: 'NumberColumn',
        displayable: false
      },
      {
        id: 'StringColumn',
        objectType: 'Column',
        text: 'String Column',
        width: 300,
        sortActive: true,
        sortIndex: 0
      },
      {
        id: 'SmartColumn',
        objectType: 'SmartColumn',
        lookupCall: 'jswidgets.LocaleLookupCall',
        text: 'Smart Column',
        width: 300
      },
      {
        id: 'NumberColumn',
        objectType: 'NumberColumn',
        text: 'Number Column',
        width: 300
      },
      {
        id: 'BooleanColumn',
        objectType: 'BooleanColumn',
        text: 'Boolean Column',
        width: 150
      }
    ],
    menus: [
      {
        id: 'FormMenu',
        objectType: 'FormMenu',
        text: 'Form menu',
        menuTypes: [
          'Table.EmptySpace', 'Table.SingleSelection'
        ],
        form: {
          objectType: 'jswidgets.MiniForm'
        }
      },
      {
        id: 'AddRowMenu',
        objectType: 'Menu',
        text: '${textKey:AddRow}',
        menuTypes: [
          'Table.EmptySpace'
        ],
        keyStroke: 'insert'
      },
      {
        id: 'AddManyMenu',
        objectType: 'Menu',
        text: 'Add many',
        menuTypes: [
          'Table.EmptySpace'
        ]
      },
      {
        id: 'DeleteRowMenu',
        objectType: 'Menu',
        text: '${textKey:DeleteRow}',
        menuTypes: [
          'Table.SingleSelection',
          'Table.MultiSelection'
        ],
        keyStroke: 'delete'
      },
      {
        id: 'TileToggleMenu',
        objectType: 'jswidgets.SamplePageWithTableTileToggleMenu',
        iconId: icons.SQUARE_BOLD,
        stackable: false,
        horizontalAlignment: 1,
        menuTypes: [
          'Table.EmptySpace'
        ]
      }
    ],
    tableControls: [
      {
        id: 'SearchFormTableControl',
        objectType: 'FormTableControl',
        iconId: icons.SEARCH,
        form: {
          id: 'SearchForm',
          objectType: 'jswidgets.SamplePageWithTableSearchForm'
        }
      },
      {
        id: 'AggregateTableControl',
        objectType: 'AggregateTableControl'
      }
    ]
  }
});
