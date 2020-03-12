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
package org.eclipse.scout.widgets.client.ui.forms;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.browserfield.AbstractBrowserField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.eclipse.scout.rt.platform.util.collection.OrderedCollection;
import org.eclipse.scout.widgets.client.ResourceBase;
import org.eclipse.scout.widgets.client.WidgetsClientConfigProperties.ReadOnlyProperty;
import org.eclipse.scout.widgets.client.WidgetsHelper;
import org.eclipse.scout.widgets.client.ui.desktop.outlines.IAdvancedExampleForm;
import org.eclipse.scout.widgets.client.ui.forms.BrowserFieldForm.MainBox.CloseButton;
import org.eclipse.scout.widgets.client.ui.forms.BrowserFieldForm.MainBox.ExamplesBox;
import org.eclipse.scout.widgets.client.ui.forms.BrowserFieldForm.MainBox.ExamplesBox.BrowserField;
import org.eclipse.scout.widgets.client.ui.forms.BrowserFieldForm.MainBox.ExamplesBox.LinksBox;
import org.eclipse.scout.widgets.client.ui.forms.BrowserFieldForm.MainBox.ExamplesBox.LinksBox.BsiSoftwareButton;
import org.eclipse.scout.widgets.client.ui.forms.BrowserFieldForm.MainBox.ExamplesBox.LinksBox.EclipseScoutButton;
import org.eclipse.scout.widgets.client.ui.forms.BrowserFieldForm.MainBox.ExamplesBox.SetFileBox.BinaryResourceField;
import org.eclipse.scout.widgets.client.ui.forms.BrowserFieldForm.MainBox.ExamplesBox.SetFileBox.SetFileButton;
import org.eclipse.scout.widgets.client.ui.forms.BrowserFieldForm.MainBox.ExamplesBox.SetUrlBox.SetUrlButton;
import org.eclipse.scout.widgets.client.ui.forms.BrowserFieldForm.MainBox.ExamplesBox.SetUrlBox.URLField;
import org.eclipse.scout.widgets.shared.Icons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The following code-snippet shows what the content of an IFRAME must do to call the execPostMessage() callback. This
 * will only work, when the IFRAME is not restricted by the sandbox attribute.
 *
 * <pre>
 * &lt;script&gt;
 * function postMessage() {
 *   window.parent.postMessage('hello Scout application!', 'http://localhost:8082');
 * }
 * &lt;/script&gt;
 * &lt;button onclick="postMessage()"&gt;Post message&lt;/button&gt;
 *
 * The second parameter (targetOrigin) of the postMessage function is important, it points to the domain where the
 * Scout application runs. When the IFRAME content is called from another domain than localhost:8082, the browser
 * will NOT execute the function. You could also use '*' as targetOrigin, when you don't care which domain exactly
 * should be allowed to receive post messages from the IFRAME content.
 * </pre>
 */
@Order(8100)
@ClassId("8149752e-43cc-4b26-8b4e-e6e8e7877216")
public class BrowserFieldForm extends AbstractForm implements IAdvancedExampleForm {
  private static final Logger LOG = LoggerFactory.getLogger(BrowserFieldForm.class);

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("BrowserField");
  }

  @Override
  public void startPageForm() {
    startInternal(new PageFormHandler());
  }

  public BrowserField getBrowserField() {
    return getFieldByClass(BrowserField.class);
  }

  public BsiSoftwareButton getBsiagButton() {
    return getFieldByClass(BsiSoftwareButton.class);
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public EclipseScoutButton getEclipseScoutButton() {
    return getFieldByClass(EclipseScoutButton.class);
  }

  public ExamplesBox getExamplesBox() {
    return getFieldByClass(ExamplesBox.class);
  }

  public LinksBox getLinksBox() {
    return getFieldByClass(LinksBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public URLField getURLField() {
    return getFieldByClass(URLField.class);
  }

  public BinaryResourceField getBinaryResourceField() {
    return getFieldByClass(BinaryResourceField.class);
  }

  public SetUrlButton getSetUrlButton() {
    return getFieldByClass(SetUrlButton.class);
  }

  public SetFileButton getSetFileButton() {
    return getFieldByClass(SetFileButton.class);
  }

  @Order(10)
  @ClassId("0a756ada-c387-4b75-9703-6a82cbe8c6a3")
  public class MainBox extends AbstractGroupBox {

    @Override
    protected void injectMenusInternal(OrderedCollection<IMenu> menus) {
      BEANS.get(WidgetsHelper.class).injectReadOnlyMenu(menus);
    }

    @Override
    protected void execInitField() {
      setStatusVisible(false);
    }

    @Order(10)
    @ClassId("1194e7f6-3ba0-4e0f-975f-96e43c54319a")
    public class ExamplesBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 1;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Examples");
      }

      @Order(10)
      @ClassId("04b1775b-6cad-45fe-8f88-c7385dcecf7b")
      public class BrowserField extends AbstractBrowserField {

        @Override
        protected int getConfiguredGridH() {
          return 10;
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected boolean getConfiguredScrollBarEnabled() {
          return true;
        }

        @Override
        protected boolean getConfiguredSandboxEnabled() {
          return false;
        }

        @Override
        protected void execPostMessage(String data, String origin) {
          LOG.info("Received post-message: data={}, origin={}", data, origin);
        }
      }

      @Order(22)
      @ClassId("5eb5d910-85d2-4244-88c5-83bcbf04c796")
      public class SetUrlBox extends AbstractSequenceBox {

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Order(10)
        @ClassId("d36bbb39-93be-4145-969b-578f71984965")
        public class URLField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("URL");
          }

          @Override
          protected byte getConfiguredLabelPosition() {
            return LABEL_POSITION_ON_FIELD;
          }
        }

        @Order(20)
        @ClassId("406f1863-24f2-4d4e-b07a-2834bea42332")
        public class SetUrlButton extends AbstractButton {

          @Override
          protected String getConfiguredLabel() {
            return "Set";
          }

          @Override
          protected void execClickAction() {
            try {
              getBrowserField().setLocation(getURLField().getValue());
            }
            catch (IllegalArgumentException e) {
              throw new VetoException(TEXTS.get("EnteredUrlInvalid"));
            }
          }
        }
      }

      @Order(23)
      @ClassId("f095737a-b591-41a2-a391-68d12e43f377")
      public class SetFileBox extends AbstractSequenceBox {

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Order(10)
        @ClassId("fed1f643-24c4-4bcd-950e-3e50ce98b232")
        public class BinaryResourceField extends AbstractFileChooserField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("File");
          }

          @Override
          protected byte getConfiguredLabelPosition() {
            return LABEL_POSITION_ON_FIELD;
          }

          @Override
          protected void execInitField() {
            setEnabled(!CONFIG.getPropertyValue(ReadOnlyProperty.class));
          }
        }

        @Order(60)
        @ClassId("9df05795-7039-4ee4-acb1-834062c66775")
        public class SetFileButton extends AbstractButton {

          @Override
          protected String getConfiguredLabel() {
            return "Set";
          }

          @Override
          protected void execClickAction() {
            getBrowserField().setBinaryResource(getBinaryResourceField().getValue());
          }
        }
      }

      @Order(25)
      @ClassId("fc3817d4-0da0-4f5f-9ea2-ffc6808f1d43")
      public class LinksBox extends AbstractSequenceBox {

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Order(10)
        @ClassId("cf2e3364-de78-41b1-af8d-df2394b7d5f7")
        public class EclipseScoutButton extends AbstractLinkButton {

          @Override
          protected String getConfiguredLabel() {
            return "www.bing.com";
          }

          @Override
          protected String getConfiguredIconId() {
            return Icons.World;
          }

          @Override
          protected boolean getConfiguredProcessButton() {
            return false;
          }

          @Override
          protected void execClickAction() {
            getURLField().setValue("http://www.bing.com/search?q=Eclipse%20Scout");
            getSetUrlButton().doClick();
          }
        }

        @Order(20)
        @ClassId("2a28c202-59f8-4ad3-a4e6-72e3fd126728")
        public class BsiSoftwareButton extends AbstractLinkButton {

          @Override
          protected String getConfiguredLabel() {
            return "www.bsi-software.com";
          }

          @Override
          protected String getConfiguredIconId() {
            return Icons.World;
          }

          @Override
          protected boolean getConfiguredProcessButton() {
            return false;
          }

          @Override
          protected void execClickAction() {
            getURLField().setValue("http://www.bsi-software.com");
            getSetUrlButton().doClick();
          }
        }

        @Order(20)
        @ClassId("44220eea-3c74-41b7-b46d-a24717d25150")
        public class TestHtmlButton extends AbstractLinkButton {

          @Override
          protected String getConfiguredLabel() {
            return "BrowserFieldCustomHtml.html";
          }

          @Override
          protected String getConfiguredIconId() {
            return Icons.FileSolid;
          }

          @Override
          protected boolean getConfiguredProcessButton() {
            return false;
          }

          @Override
          protected void execClickAction() {
            try (InputStream in = ResourceBase.class.getResourceAsStream("html/BrowserFieldCustomHtml.html")) {
              BinaryResource resource = new BinaryResource("BrowserFieldCustomHtml.html", IOUtility.readBytes(in));
              getBinaryResourceField().setValue(resource);
              getSetFileButton().doClick();
            }
            catch (IOException e) {
              throw new ProcessingException("resource", e);
            }
          }
        }

        @Order(999)
        @ClassId("546fd072-99a0-40c9-b1a4-1aaa5c0c9827")
        public class ClearButton extends AbstractLinkButton {

          @Override
          protected String getConfiguredLabel() {
            return "(null)";
          }

          @Override
          protected String getConfiguredIconId() {
            return Icons.RemoveBold;
          }

          @Override
          protected void execClickAction() {
            getBrowserField().setLocation(null);
          }
        }
      }
    }

    @Order(30)
    @ClassId("29122615-c832-42f0-996e-4e607999f9ca")
    public class ConfigurationBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Configure");
      }

      @Override
      protected int getConfiguredGridColumnCount() {
        return 2;
      }

      @Order(10)
      @ClassId("167ddf56-bfaa-4cb0-9d15-a9bb44362481")
      public class VisibleField extends AbstractBooleanField {

        @Override
        protected String getConfiguredLabel() {
          return "Visible";
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected void execChangedValue() {
          getBrowserField().setVisible(isChecked());
        }

        @Override
        protected void execInitField() {
          setChecked(getBrowserField().isVisible());
        }
      }

      @Order(20)
      @ClassId("267b17c1-dee0-4416-828b-00f94a2ca8b1")
      public class EnabledField extends AbstractBooleanField {

        @Override
        protected String getConfiguredLabel() {
          return "Enabled";
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected void execChangedValue() {
          getBrowserField().setEnabled(isChecked());
        }

        @Override
        protected void execInitField() {
          setChecked(getBrowserField().isEnabled());
        }
      }

      @Order(30)
      @ClassId("49fe66bc-922d-4e83-9e3b-a6e5e0572c9f")
      public class ScrollableField extends AbstractBooleanField {

        @Override
        protected String getConfiguredLabel() {
          return "Scrollbar enabled";
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected void execChangedValue() {
          getBrowserField().setScrollBarEnabled(isChecked());
        }

        @Override
        protected void execInitField() {
          setChecked(getBrowserField().isScrollBarEnabled());
        }
      }

      @Order(50)
      @ClassId("5967c688-f93c-4e92-8dd0-5bbbb203b344")
      public class ShowInExternalWindowField extends AbstractBooleanField {

        @Override
        protected String getConfiguredLabel() {
          return "Show in external window";
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected void execChangedValue() {
          getBrowserField().setShowInExternalWindow(isChecked());
        }

        @Override
        protected void execInitField() {
          setChecked(getBrowserField().isShowInExternalWindow());
        }
      }

      @Order(60)
      @ClassId("723924ac-c72e-4d9e-8426-f16b7bb29f85")
      public class AutoCloseExternalWindowField extends AbstractBooleanField {

        @Override
        protected String getConfiguredLabel() {
          return "Auto close external window";
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected void execChangedValue() {
          getBrowserField().setAutoCloseExternalWindow(isChecked());
        }

        @Override
        protected void execInitField() {
          setChecked(getBrowserField().isAutoCloseExternalWindow());
        }
      }

      @Order(99)
      @ClassId("4be62e14-f463-4819-ac1f-0a5403e8b18d")
      public class SandboxPermissionsField extends AbstractLinkButton {

        private BrowserFieldSandboxForm m_sandboxForm;

        @Override
        protected String getConfiguredLabel() {
          return "Sandbox settings...";
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }

        @Override
        protected void execClickAction() {
          if (m_sandboxForm == null) {
            m_sandboxForm = new BrowserFieldSandboxForm(getBrowserField());
            m_sandboxForm.addFormListener(e -> {
              if (e.getType() == FormEvent.TYPE_CLOSED) {
                m_sandboxForm = null;
              }
            });
            m_sandboxForm.start();
          }
          else {
            m_sandboxForm.doClose();
          }
        }
      }
    }

    @Order(40)
    @ClassId("f0910f85-3a9f-49e5-beed-23f61dbbf5eb")
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
