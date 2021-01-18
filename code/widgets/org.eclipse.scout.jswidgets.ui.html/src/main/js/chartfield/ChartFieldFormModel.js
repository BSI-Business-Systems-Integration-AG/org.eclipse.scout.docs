import {FormField, icons} from '@eclipse-scout/core';

export default () => ({
  id: 'jswidgets.ChartFieldForm',
  displayHint: 'view',
  rootGroupBox: {
    id: 'MainBox',
    objectType: 'GroupBox',
    statusVisible: false,
    fields: [
      {
        id: 'GroupBox',
        objectType: 'GroupBox',
        gridColumnCount: 4,
        borderVisible: false,
        fields: [{
          id: 'TopBox',
          objectType: 'GroupBox',
          labelVisible: false,
          borderVisible: false,
          gridDataHints: {
            weightY: 0
          },
          fields: [
            {
              id: 'ChartBox',
              objectType: 'GroupBox',
              label: 'Chart',
              verticalAlignment: -1,
              statusVisible: false,
              gridDataHints: {
                fillVertical: false,
                heightInPixel: 500
              },
              fields: [{
                id: 'ChartField',
                objectType: 'ChartField',
                gridDataHints: {
                  h: 3
                },
                labelVisible: false,
                statusVisible: false,
                chart: {
                  id: 'Chart',
                  objectType: 'Chart',
                  config: {
                    options: {
                      elements: {
                        line: {
                          fill: false
                        }
                      }
                    }
                  }
                }
              }, {
                id: 'ChartTileBox',
                objectType: 'TileField',
                gridDataHints: {
                  h: 3
                },
                labelVisible: false,
                statusVisible: false,
                visible: false,
                tileGrid: {
                  id: 'TileGrid',
                  objectType: 'TileGrid',
                  tiles: [
                    {
                      id: 'ChartTile',
                      objectType: 'ChartFieldTile',
                      labelVisible: false,
                      gridDataHints: {
                        weightY: 1
                      },
                      tileWidget: {
                        id: 'TileChartField',
                        objectType: 'ChartField',
                        labelVisible: false,
                        statusVisible: false,
                        chart: {
                          id: 'TileChart',
                          objectType: 'Chart',
                          config: {
                            options: {
                              elements: {
                                line: {
                                  fill: false
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  ]
                }
              }]
            }
          ]
        }, {
          id: 'BottomBox',
          objectType: 'TabBox',
          selectedTab: 'ChartPropertiesBox',
          tabItems: [{
            id: 'ChartPropertiesBox',
            objectType: 'TabItem',
            label: 'Properties',
            gridColumnCount: 4,
            fields: [
              {
                id: 'LeftBox',
                objectType: 'GroupBox',
                gridDataHints: {
                  w: 2
                },
                gridColumnCount: 2,
                borderVisible: false,
                fields: [
                  {
                    id: 'AutoColorCheckBox',
                    objectType: 'CheckBoxField',
                    label: 'Auto Color',
                    labelVisible: false
                  },
                  {
                    id: 'ClickableCheckBox',
                    objectType: 'CheckBoxField',
                    label: 'Clickable',
                    labelVisible: false
                  },
                  {
                    id: 'CheckableCheckBox',
                    objectType: 'CheckBoxField',
                    label: 'Checkable',
                    labelVisible: false
                  },
                  {
                    id: 'AnimatedCheckBox',
                    objectType: 'CheckBoxField',
                    label: 'Animated',
                    labelVisible: false
                  },
                  {
                    id: 'LegendVisibleBox',
                    objectType: 'CheckBoxField',
                    label: 'Legend visible',
                    labelVisible: false
                  },
                  {
                    id: 'LegendClickableCheckBox',
                    objectType: 'CheckBoxField',
                    label: 'Legend clickable',
                    tooltipText: 'Datasets can be shown and hidden using the legend',
                    labelVisible: false
                  },
                  {
                    id: 'TooltipsEnabledBox',
                    objectType: 'CheckBoxField',
                    label: 'Tooltips enabled',
                    labelVisible: false
                  },
                  {
                    id: 'DatalabelsVisibleCheckBox',
                    objectType: 'CheckBoxField',
                    label: 'Datalabels visible',
                    labelVisible: false
                  },
                  {
                    id: 'XAxisStackedCheckBox',
                    objectType: 'CheckBoxField',
                    label: 'x-Axis stacked',
                    labelVisible: false
                  },
                  {
                    id: 'YAxisStackedCheckBox',
                    objectType: 'CheckBoxField',
                    label: 'y-Axis stacked',
                    labelVisible: false
                  },
                  {
                    id: 'FillCheckBox',
                    objectType: 'CheckBoxField',
                    label: 'Fill',
                    labelVisible: false
                  },
                  {
                    id: 'TransparentCheckBox',
                    objectType: 'CheckBoxField',
                    label: 'Transparent',
                    labelVisible: false
                  },
                  {
                    id: 'AccordingToValuesCheckbox',
                    objectType: 'CheckBoxField',
                    label: 'Normalized',
                    labelVisible: false
                  },
                  {
                    id: 'FulfillmentStartValuePropertyCheckbox',
                    objectType: 'CheckBoxField',
                    label: 'Use start value property',
                    tooltipText: 'If set animation starts from the current value. If not set, the animation returns to 0 and starts from 0.',
                    labelVisible: false
                  },
                  {
                    id: 'TileCheckBox',
                    objectType: 'CheckBoxField',
                    label: 'Show inside tile',
                    labelVisible: false
                  }
                ]
              },
              {
                id: 'RightBox',
                objectType: 'GroupBox',
                gridDataHints: {
                  w: 2
                },
                gridColumnCount: 2,
                borderVisible: false,
                fields: [
                  {
                    id: 'ChartTypeField',
                    objectType: 'SmartField',
                    label: 'Chart Type',
                    labelPosition: FormField.LabelPosition.TOP,
                    lookupCall: 'jswidgets.ChartTypeLookupCall',
                    displayStyle: 'dropdown'
                  },
                  {
                    id: 'ColorSchemeField',
                    objectType: 'SmartField',
                    label: 'Chart Scheme',
                    labelPosition: FormField.LabelPosition.TOP,
                    lookupCall: 'jswidgets.ColorSchemeLookupCall',
                    displayStyle: 'dropdown'
                  },
                  {
                    id: 'TensionField',
                    objectType: 'NumberField',
                    label: 'Tension',
                    labelPosition: FormField.LabelPosition.TOP,
                    gridDataHints: {
                      horizontalAlignment: -1
                    },
                    minValue: 0,
                    maxValue: 1,
                    updateDisplayTextOnModify: true
                  },
                  {
                    id: 'GreenAreaPositionField',
                    objectType: 'SmartField',
                    label: 'Green area position',
                    labelPosition: FormField.LabelPosition.TOP,
                    lookupCall: 'jswidgets.SpeedoGreenAreaPositionLookupCall',
                    displayStyle: 'dropdown'
                  },
                  {
                    id: 'SizeOfLargestBubbleField',
                    objectType: 'NumberField',
                    label: 'Size of largest bubble',
                    labelPosition: FormField.LabelPosition.TOP,
                    gridDataHints: {
                      horizontalAlignment: -1
                    },
                    minValue: 0,
                    updateDisplayTextOnModify: true
                  },
                  {
                    id: 'MinBubbleSizeField',
                    objectType: 'NumberField',
                    label: 'Min bubble size',
                    labelPosition: FormField.LabelPosition.TOP,
                    gridDataHints: {
                      horizontalAlignment: -1
                    },
                    minValue: 0,
                    updateDisplayTextOnModify: true
                  },
                  {
                    id: 'LegendPositionField',
                    objectType: 'SmartField',
                    label: 'Legend position',
                    labelPosition: FormField.LabelPosition.TOP,
                    lookupCall: 'jswidgets.LegendPositionLookupCall',
                    displayStyle: 'dropdown'
                  }]
              },
              {
                id: 'FormFieldPropertiesBox',
                objectType: 'jswidgets.FormFieldPropertiesBox',
                expanded: false
              }
            ]
          }, {
            id: 'ChartDataBox',
            objectType: 'TabItem',
            label: 'Data',
            gridColumnCount: 3,
            fields: [{
              id: 'LeftBox',
              objectType: 'GroupBox',
              gridDataHints: {
                w: 2,
                weightY: 1
              },
              gridColumnCount: 2,
              borderVisible: false,
              fields: [{
                id: 'ChartDataTableField',
                objectType: 'TableField',
                labelVisible: false,
                enabled: false,
                table: {
                  id: 'Table',
                  objectType: 'Table',
                  sortEnabled: false,
                  headerMenusEnabled: false,
                  columns: [
                    {
                      id: 'DatasetLabelColumn',
                      objectType: 'Column',
                      editable: true,
                      width: 250,
                      fixedPosition: true,
                      fixedWidth: true
                    }
                  ],
                  menus: [
                    {
                      id: 'AddDatasetMenu',
                      objectType: 'Menu',
                      text: 'Add dataset',
                      menuTypes: [
                        'Table.EmptySpace',
                        'Table.SingleSelection',
                        'Table.MultiSelection'
                      ]
                    },
                    {
                      id: 'RemoveDatasetMenu',
                      objectType: 'Menu',
                      text: 'Remove dataset',
                      menuTypes: [
                        'Table.SingleSelection',
                        'Table.MultiSelection'
                      ]
                    },
                    {
                      id: 'AddDataMenu',
                      objectType: 'Menu',
                      iconId: icons.PLUS,
                      tooltipText: 'Add data',
                      menuTypes: [
                        'Table.Header'
                      ]
                    },
                    {
                      id: 'RemoveDataMenu',
                      objectType: 'Menu',
                      iconId: icons.MINUS,
                      tooltipText: 'Remove data',
                      enabled: false,
                      menuTypes: [
                        'Table.Header'
                      ]
                    }
                  ]
                }
              }]
            }, {
              id: 'RightBox',
              objectType: 'GroupBox',
              gridDataHints: {
                w: 1,
                weightY: 1
              },
              gridColumnCount: 4,
              borderVisible: false,
              fields: [{
                id: 'RandomCheckBox',
                objectType: 'CheckBoxField',
                label: 'Random',
                labelVisible: false,
                statusVisible: false
              }, {
                id: 'FillTableCheckBox',
                objectType: 'CheckBoxField',
                label: 'Fill Table',
                labelVisible: false,
                statusVisible: false
              }, {
                id: 'RandomDataButton',
                objectType: 'Button',
                label: 'Generate random data',
                keyStroke: 'enter',
                gridDataHints: {
                  w: 2
                },
                processButton: false
              }, {
                id: 'ValuesProviderField',
                objectType: 'SmartField',
                label: 'Values',
                labelPosition: FormField.LabelPosition.TOP,
                gridDataHints: {
                  w: 4
                },
                lookupCall: 'jswidgets.ValuesProviderLookupCall',
                displayStyle: 'dropdown'
              }, {
                id: 'NumberOfDatasetsField',
                objectType: 'IntegerField',
                label: 'Number of datasets',
                labelPosition: FormField.LabelPosition.TOP,
                gridDataHints: {
                  w: 4,
                  horizontalAlignment: -1
                },
                updateDisplayTextOnModify: true,
                menus: [
                  {
                    id: 'RandomNumberOfDatasetsMenu',
                    objectType: 'Menu',
                    text: 'Set random number'
                  }]
              }, {
                id: 'MaxSegmentsField',
                objectType: 'IntegerField',
                label: 'Max. segments',
                labelPosition: FormField.LabelPosition.TOP,
                gridDataHints: {
                  w: 4,
                  horizontalAlignment: -1
                },
                minValue: 1,
                updateDisplayTextOnModify: true
              }]
            }]
          }, {
            id: 'ActionsTab',
            objectType: 'TabItem',
            label: 'Actions',
            fields: [
              {
                id: 'WidgetActionsBox',
                objectType: 'jswidgets.WidgetActionsBox',
                expandable: false,
                labelVisible: false,
                borderVisible: false
              }
            ]
          }, {
            id: 'EventsTab',
            objectType: 'jswidgets.EventsTab'
          }]
        }]
      }
    ]
  }
});
