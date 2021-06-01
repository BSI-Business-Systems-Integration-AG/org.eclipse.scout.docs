export default () => ({
  type: 'extension',
  extensions: [
    {
      operation: 'appendTo',
      target: {
        id: 'StringField'
      },
      extension: {
        multilineText: true,
        gridDataHints: {
          h: 3
        }
      }
    }
  ]
});
