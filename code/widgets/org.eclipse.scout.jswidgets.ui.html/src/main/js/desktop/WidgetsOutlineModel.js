export default () => ({
  id: 'jswidgets.WidgetsOutline',
  title: '${textKey:Widgets}',
  outlineOverview: {
    objectType: 'jswidgets.WidgetsOutlineOverview'
  },
  nodes: [
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Accordion',
      detailForm: {
        objectType: 'jswidgets.AccordionForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Breadcrumb Bar Field',
      detailForm: {
        objectType: 'jswidgets.BreadcrumbBarFieldForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Button',
      detailForm: {
        objectType: 'jswidgets.ButtonForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Browser Field',
      detailForm: {
        objectType: 'jswidgets.BrowserFieldForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Carousel',
      detailForm: {
        objectType: 'jswidgets.CarouselForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Chart Field',
      detailForm: {
        objectType: 'jswidgets.ChartFieldForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Check Box Field',
      detailForm: {
        objectType: 'jswidgets.CheckBoxFieldForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      expanded: true,
      text: 'Custom Fields',
      childNodes: [
        {
          objectType: 'PageWithNodes',
          leaf: true,
          text: 'Watch Field',
          detailForm: {
            objectType: 'jswidgets.WatchFieldForm'
          },
          detailTableVisible: false
        }
      ]
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Date Field',
      detailForm: {
        objectType: 'jswidgets.DateFieldForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Desktop',
      detailForm: {
        objectType: 'jswidgets.DesktopForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Desktop Notification',
      detailForm: {
        objectType: 'jswidgets.DesktopNotificationForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Form',
      detailForm: {
        objectType: 'jswidgets.FormForm',
        detailForm: true
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      expanded: true,
      text: 'File Chooser Field',
      detailForm: {
        objectType: 'jswidgets.FileChooserFieldForm'
      },
      childNodes: [
        {
          objectType: 'PageWithNodes',
          leaf: true,
          text: 'File Chooser Button',
          detailForm: {
            objectType: 'jswidgets.FileChooserButtonForm'
          },
          detailTableVisible: false
        },
        {
          objectType: 'PageWithNodes',
          leaf: true,
          text: 'File Chooser',
          detailForm: {
            objectType: 'jswidgets.FileChooserForm'
          },
          detailTableVisible: false
        }
      ]
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Group Box',
      detailForm: {
        objectType: 'jswidgets.GroupBoxForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Image',
      detailForm: {
        objectType: 'jswidgets.ImageForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Image Field',
      detailForm: {
        objectType: 'jswidgets.ImageFieldForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Label',
      detailForm: {
        objectType: 'jswidgets.LabelForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Label Field',
      detailForm: {
        objectType: 'jswidgets.LabelFieldForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'List Box',
      detailForm: {
        objectType: 'jswidgets.ListBoxForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Logical Grid',
      detailForm: {
        objectType: 'jswidgets.LogicalGridForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Menu Bar',
      detailForm: {
        objectType: 'jswidgets.MenuBarForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Mode Selector',
      detailForm: {
        objectType: 'jswidgets.ModeSelectorForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      text: 'Number Field',
      detailForm: {
        objectType: 'jswidgets.NumberFieldForm'
      },
      expanded: true,
      childNodes: [
        {
          objectType: 'PageWithNodes',
          leaf: true,
          text: 'Integer Field',
          detailForm: {
            objectType: 'jswidgets.IntegerFieldForm'
          },
          detailTableVisible: false
        }
      ]
    },
    {
      objectType: 'PageWithNodes',
      text: 'Pages',
      expanded: true,
      childNodes: [
        {
          objectType: 'jswidgets.SamplePageWithTable'
        },
        {
          objectType: 'jswidgets.SamplePageWithNodes'
        }
      ]
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Popup',
      detailForm: {
        objectType: 'jswidgets.PopupForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Radio Button Group',
      detailForm: {
        objectType: 'jswidgets.RadioButtonGroupForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Rest',
      detailForm: {
        objectType: 'jswidgets.RestForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Sequence Box',
      detailForm: {
        objectType: 'jswidgets.SequenceBoxForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      text: 'Smart Field',
      expanded: true,
      detailForm: {
        objectType: 'jswidgets.SmartFieldForm'
      },
      childNodes: [
        {
          objectType: 'PageWithNodes',
          leaf: true,
          text: 'Proposal Field',
          detailForm: {
            objectType: 'jswidgets.ProposalFieldForm'
          },
          detailTableVisible: false
        },
        {
          objectType: 'PageWithNodes',
          leaf: true,
          text: 'Multiline Smart Field',
          detailForm: {
            objectType: 'jswidgets.MultilineSmartFieldForm'
          },
          detailTableVisible: false
        },
        {
          objectType: 'PageWithNodes',
          leaf: true,
          text: 'Table Smart Field',
          detailForm: {
            objectType: 'jswidgets.TableSmartFieldForm'
          },
          detailTableVisible: false
        },
        {
          objectType: 'PageWithNodes',
          leaf: true,
          text: 'Tree Smart Field',
          detailForm: {
            objectType: 'jswidgets.TreeSmartFieldForm'
          },
          detailTableVisible: false
        }
      ]
    },
    {
      objectType: 'PageWithNodes',
      text: 'String Field',
      expanded: true,
      detailForm: {
        objectType: 'jswidgets.StringFieldForm'
      },
      childNodes: [
        {
          objectType: 'PageWithNodes',
          leaf: true,
          text: 'Multiline String Field',
          detailForm: {
            objectType: 'jswidgets.MultilineStringFieldForm'
          },
          detailTableVisible: false
        }
      ]
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Tab Box',
      detailForm: {
        objectType: 'jswidgets.TabBoxForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      text: 'Table',
      expanded: true,
      detailForm: {
        objectType: 'jswidgets.TableForm'
      },
      childNodes: [
        {
          objectType: 'PageWithNodes',
          leaf: true,
          text: 'Editable Table',
          detailForm: {
            objectType: 'jswidgets.EditableTableForm'
          },
          detailTableVisible: false
        },
        {
          objectType: 'PageWithNodes',
          leaf: true,
          text: 'Hierarchical Table',
          detailForm: {
            objectType: 'jswidgets.HierarchicalTableForm'
          },
          detailTableVisible: false
        }
      ]
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Tag Field',
      detailForm: {
        objectType: 'jswidgets.TagFieldForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      text: 'Tile Grid',
      expanded: true,
      detailForm: {
        objectType: 'jswidgets.TileGridForm'
      },
      childNodes: [
        {
          objectType: 'PageWithNodes',
          leaf: true,
          text: 'Virtual Tile Grid',
          detailForm: {
            objectType: 'jswidgets.VirtualTileGridForm'
          },
          detailTableVisible: false
        }
      ]
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Tile Accordion',
      detailForm: {
        objectType: 'jswidgets.TileAccordionForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Tree',
      detailForm: {
        objectType: 'jswidgets.TreeForm'
      },
      detailTableVisible: false
    },
    {
      objectType: 'PageWithNodes',
      leaf: true,
      text: 'Tree Box',
      detailForm: {
        objectType: 'jswidgets.TreeBoxForm'
      },
      detailTableVisible: false
    }
  ]
});
