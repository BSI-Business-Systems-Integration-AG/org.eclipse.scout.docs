/*
 * Copyright (c) 2015 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 */
package org.eclipse.scout.widgets.client.ui.forms;

import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.ILabelField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBoxes;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.html.HTML;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.widgets.client.ui.forms.LabelFieldForm.MainBox.CloseButton;
import org.eclipse.scout.widgets.client.ui.forms.LabelFieldForm.MainBox.FormFieldPropertiesBox;
import org.eclipse.scout.widgets.client.ui.forms.LabelFieldForm.MainBox.LabelFieldBox.LabelField;
import org.eclipse.scout.widgets.client.ui.forms.fields.formfield.AbstractFormFieldPropertiesBox;

@ClassId("6d611416-0959-41d6-9230-44cf6bcdc102")
public class LabelFieldForm extends AbstractForm implements IPageForm {

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("LabelField");
  }

  @Override
  public void startPageForm() {
    startInternal(new PageFormHandler());
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public LabelField getLabelField() {
    return getFieldByClass(LabelField.class);
  }

  public FormFieldPropertiesBox getFormFieldPropertiesBox() {
    return getFieldByClass(FormFieldPropertiesBox.class);
  }

  protected <T> void setValueWithoutValueChangeTriggers(IValueField<T> valueField, T value) {
    if (valueField.isValueChanging()) {
      return;
    }
    valueField.setValueChangeTriggerEnabled(false);
    try {
      valueField.setValue(value);
    }
    finally {
      valueField.setValueChangeTriggerEnabled(true);
    }
  }

  @Order(10)
  @ClassId("ab93271a-d9b7-44d8-acc5-32b1c5215f8d")
  public class MainBox extends AbstractGroupBox {

    @Override
    protected int getConfiguredGridColumnCount() {
      return 2;
    }

    @Order(10)
    @ClassId("e5590d6c-9784-445a-a10d-83a10defcf6d")
    public class LabelFieldBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return "Label Field";
      }

      @Order(10)
      @ClassId("e1e2e679-a9a2-4d64-8321-2875fe1cea25")
      public class LabelField extends AbstractLabelField {

        @Override
        protected String getConfiguredLabel() {
          return "Message of the Day";
        }

        @Override
        protected byte getConfiguredLabelPosition() {
          return LABEL_POSITION_TOP;
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected boolean getConfiguredGridUseUiHeight() {
          return true;
        }

        @Override
        protected void execInitField() {
          setValue("Hello World!");
        }

        @Override
        protected void execAppLinkAction(String ref) {
          MessageBoxes.createOk().withBody("App link was clicked!\n\n[ref=" + ref + "]").show();
        }
      }
    }

    @Order(20)
    @ClassId("c27818b5-1bee-4020-825a-4ec8ee5fc3f1")
    public class LabelFieldPropertiesBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return "Label Field Properties";
      }

      @Order(10)
      @ClassId("c69556a4-6a7d-4f20-84b5-b383b6f4a469")
      public class LabelValueField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return "Value";
        }

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected double getConfiguredGridWeightY() {
          return 0;
        }

        @Override
        protected boolean getConfiguredMultilineText() {
          return true;
        }

        @Override
        protected boolean getConfiguredWrapText() {
          return true;
        }

        @Override
        protected int getConfiguredMaxLength() {
          return 1024 * 1024; // 1 MB
        }

        @Override
        protected void execInitField() {
          getLabelField().addPropertyChangeListener(ILabelField.PROP_VALUE, event -> setValueWithoutValueChangeTriggers(this, getLabelField().getValue()));
          setValueWithoutValueChangeTriggers(this, getLabelField().getValue());
        }

        @Override
        protected void execChangedValue() {
          getLabelField().setValue(getValue());
        }
      }

      @Order(20)
      @ClassId("ea901a7c-7907-4ecf-b9df-ed1d34e6887e")
      public class HtmlEnabledField extends AbstractBooleanField {

        @Override
        protected String getConfiguredLabel() {
          return "Html Enabled";
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected void execInitField() {
          getLabelField().addPropertyChangeListener(ILabelField.PROP_HTML_ENABLED, event -> setValueWithoutValueChangeTriggers(this, getLabelField().isHtmlEnabled()));
          setValueWithoutValueChangeTriggers(this, getLabelField().isHtmlEnabled());
        }

        @Override
        protected void execChangedValue() {
          getLabelField().setHtmlEnabled(isChecked());
        }
      }

      @Order(30)
      @ClassId("f5a8b59c-2518-45f9-b9f7-4f2662246754")
      public class WrapTextField extends AbstractBooleanField {

        @Override
        protected String getConfiguredLabel() {
          return "Wrap Text";
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected void execInitField() {
          getLabelField().addPropertyChangeListener(ILabelField.PROP_WRAP_TEXT, event -> setValueWithoutValueChangeTriggers(this, getLabelField().isWrapText()));
          setValueWithoutValueChangeTriggers(this, getLabelField().isWrapText());
        }

        @Override
        protected void execChangedValue() {
          getLabelField().setWrapText(isChecked());
        }
      }

      @Order(40)
      @ClassId("a92f71d0-af5a-4270-9f05-d8b0078d5cd8")
      public class SelectableField extends AbstractBooleanField {

        @Override
        protected String getConfiguredLabel() {
          return "Selectable";
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected void execInitField() {
          getLabelField().addPropertyChangeListener(ILabelField.PROP_SELECTABLE, event -> setValueWithoutValueChangeTriggers(this, getLabelField().isSelectable()));
          setValueWithoutValueChangeTriggers(this, getLabelField().isSelectable());
        }

        @Override
        protected void execChangedValue() {
          getLabelField().setSelectable(isChecked());
        }
      }
    }

    @Order(30)
    @ClassId("6c1b1c07-f0ea-4e36-9712-7b678a18a105")
    public class FormFieldPropertiesBox extends AbstractFormFieldPropertiesBox {

      @Override
      protected void execInitField() {
        setField(getLabelField());
      }
    }

    @Order(40)
    @ClassId("66fe293d-684c-4508-aca6-a0cf9810e79a")
    public class CloseButton extends AbstractCloseButton {
    }

    @Order(50)
    @ClassId("11c9067e-4c3f-4fb1-b344-8768c07e6841")
    public class ExampleValuesMenu extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return "Example Values";
      }

      @Order(10)
      @ClassId("f700cfa8-0827-4bc9-8abd-6c261f9db3d8")
      public class ShortTextMenu extends AbstractMenu {

        @Override
        protected String getConfiguredText() {
          return "Short Text";
        }

        @Override
        protected void execAction() {
          getLabelField().setValue("Lorem ipsum dolor sit amet.");
          getLabelField().setWrapText(false);
          getLabelField().setHtmlEnabled(false);
        }
      }

      @Order(20)
      @ClassId("4266d1cb-b046-45ab-86a8-b5a812125506")
      public class LongTextMenu extends AbstractMenu {

        @Override
        protected String getConfiguredText() {
          return "Long Text";
        }

        @Override
        protected void execAction() {
          getLabelField().setValue("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. " +
              "\n\n" +
              "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. " +
              "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. " +
              "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est.");
          getLabelField().setWrapText(true);
          getLabelField().setHtmlEnabled(false);
        }
      }

      @Order(30)
      @ClassId("c0f2d4e9-1d95-48a9-a76d-6f1a4ebea4ca")
      public class ShortHtmlMenu extends AbstractMenu {

        @Override
        protected String getConfiguredText() {
          return "Short HTML";
        }

        @Override
        protected void execAction() {
          getLabelField().setValue(HTML.div(HTML.fragment("Lorem "), HTML.appLink("ipsum", "ipsum"), HTML.fragment(" dolor sit "), HTML.bold("amet.")).toHtml());
          getLabelField().setWrapText(false);
          getLabelField().setHtmlEnabled(true);
        }
      }

      @Order(40)
      @ClassId("b2d14867-4496-47d7-b7fa-f473999a541f")
      public class LongHtmlMenu extends AbstractMenu {

        @Override
        protected String getConfiguredText() {
          return "Long HTML";
        }

        @Override
        protected void execAction() {
          getLabelField().setValue(HTML.div(
              HTML.fragment("Lorem "),
              HTML.appLink("ipsum", "ipsum"),
              HTML.fragment(" dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua."),
              HTML.br(),
              HTML.br(),
              HTML.fragment("At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum "),
              HTML.appLink("dolor", "dolor"),
              HTML.fragment(" sit amet. "),
              HTML.appLink("lorem", "Lorem"),
              HTML.fragment(" ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. " +
                  "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est."))
              .toHtml());
          getLabelField().setWrapText(true);
          getLabelField().setHtmlEnabled(true);
        }
      }
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
