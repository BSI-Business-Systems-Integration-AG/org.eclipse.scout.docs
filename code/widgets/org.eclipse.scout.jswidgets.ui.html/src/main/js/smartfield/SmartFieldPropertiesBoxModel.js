export default function() {
  return {
  id: 'jswidgets.SmartFieldPropertiesBox',
  objectType: 'GroupBox',
  label: 'Smart Field Properties',
  expandable: true,
  fields: [
    {
      id: 'LookupCallField',
      objectType: 'SmartField',
      label: 'LookupCall',
      lookupCall: 'jswidgets.LookupCallLookupCall'
    },
    {
      id: 'DisplayStyleField',
      objectType: 'SmartField',
      lookupCall: 'jswidgets.SmartFieldDisplayStyleLookupCall',
      label: 'Display Style'
    },
    {
      id: 'BrowseMaxRowCountField',
      objectType: 'NumberField',
      label: 'Browse Max Row Count'
    },
    {
      id: 'ActiveFilterEnabledField',
      objectType: 'CheckBoxField',
      label: 'Active Filter Enabled'
    },
    {
      id: 'SearchRequiredField',
      objectType: 'CheckBoxField',
      label: 'Search Required'
    }
  ]
};
}
