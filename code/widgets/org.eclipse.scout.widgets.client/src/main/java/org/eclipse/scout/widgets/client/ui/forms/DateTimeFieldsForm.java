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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.eclipse.scout.rt.client.context.ClientRunContext;
import org.eclipse.scout.rt.client.context.ClientRunContexts;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.IButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateTimeField;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractTimeField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.nls.NlsLocale;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.date.DateFormatProvider;
import org.eclipse.scout.rt.platform.util.date.DateUtility;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.widgets.client.ClientSession;
import org.eclipse.scout.widgets.client.services.lookup.LocaleLookupCall;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.CloseButton;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn1Box.ConfigLocaleField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn1Box.DateFieldFormatField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn1Box.DisplayTextField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn1Box.DontAllowCurrentDateField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn1Box.GetValueField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn1Box.HasDateField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn1Box.HasTimeField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn1Box.InputField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn2Box.DontAllow1000TimeField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn2Box.TimeDisplayTextField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn2Box.TimeFieldFormatField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn2Box.TimeInputField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn3Box.DateTimeDisplayTextField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn3Box.DateTimeFieldFormatField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ConfigurationBox.ConfigurationColumn3Box.DateTimeInputField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ExamplesBox;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ExamplesBox.ExampleColumn1Box.DateColumnField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ExamplesBox.ExampleColumn1Box.DefaultField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ExamplesBox.ExampleColumn1Box.DisabledField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ExamplesBox.ExampleColumn1Box.LocaleField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ExamplesBox.ExampleColumn1Box.MandatoryField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ExamplesBox.ExampleColumn2Box.TimeColumnField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ExamplesBox.ExampleColumn2Box.TimeDisabledField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ExamplesBox.ExampleColumn2Box.TimeField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ExamplesBox.ExampleColumn2Box.TimeMandatoryField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ExamplesBox.ExampleColumn3Box.DateTimeColumnField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ExamplesBox.ExampleColumn3Box.DateTimeDisabledField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ExamplesBox.ExampleColumn3Box.DateTimeField;
import org.eclipse.scout.widgets.client.ui.forms.DateTimeFieldsForm.MainBox.ExamplesBox.ExampleColumn3Box.DateTimeMandatoryField;
import org.eclipse.scout.widgets.shared.CustomDateFormatProvider;

public class DateTimeFieldsForm extends AbstractForm implements IPageForm {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("NumberFields");
  }

  @Override
  public void startPageForm() {
    startInternal(new PageFormHandler());
  }

  public TimeDisplayTextField getTimeDisplayTextField() {
    return getFieldByClass(TimeDisplayTextField.class);
  }

  public TimeField getTimeField() {
    return getFieldByClass(TimeField.class);
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public DateTimeDisplayTextField getDateTimeDisplayTextField() {
    return getFieldByClass(DateTimeDisplayTextField.class);
  }

  public DateTimeField getDateTimeField() {
    return getFieldByClass(DateTimeField.class);
  }

  public DefaultField getDefaultField() {
    return getFieldByClass(DefaultField.class);
  }

  public DisabledField getDisabledField() {
    return getFieldByClass(DisabledField.class);
  }

  public GetValueField getGetValueField() {
    return getFieldByClass(GetValueField.class);
  }

  public InputField getInputField() {
    return getFieldByClass(InputField.class);
  }

  public DateColumnField getDateColumnField() {
    return getFieldByClass(DateColumnField.class);
  }

  public DontAllowCurrentDateField getDontAllowCurrentDateField() {
    return getFieldByClass(DontAllowCurrentDateField.class);
  }

  public DontAllow1000TimeField getDontAllow1000TimeField() {
    return getFieldByClass(DontAllow1000TimeField.class);
  }

  public HasDateField getHasDateField() {
    return getFieldByClass(HasDateField.class);
  }

  public HasTimeField getHasTimeField() {
    return getFieldByClass(HasTimeField.class);
  }

  public TimeColumnField getTimeColumnField() {
    return getFieldByClass(TimeColumnField.class);
  }

  public TimeDisabledField getTimeDisabledField() {
    return getFieldByClass(TimeDisabledField.class);
  }

  public LocaleField getLocaleField() {
    return getFieldByClass(LocaleField.class);
  }

  public TimeFieldFormatField getTimeFieldFormatField() {
    return getFieldByClass(TimeFieldFormatField.class);
  }

  public TimeInputField getTimeInputField() {
    return getFieldByClass(TimeInputField.class);
  }

  public TimeMandatoryField getTimeMandatoryField() {
    return getFieldByClass(TimeMandatoryField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public DateTimeDisabledField getDateTimeDisabledField() {
    return getFieldByClass(DateTimeDisabledField.class);
  }

  public DateTimeMandatoryField getDateTimeMandatoryField() {
    return getFieldByClass(DateTimeMandatoryField.class);
  }

  public DateFieldFormatField getDateFieldFormatField() {
    return getFieldByClass(DateFieldFormatField.class);
  }

  public ConfigLocaleField getConfigLocaleField() {
    return getFieldByClass(ConfigLocaleField.class);
  }

  public DateTimeColumnField getDateTimeColumnField() {
    return getFieldByClass(DateTimeColumnField.class);
  }

  public DateTimeFieldFormatField getDateTimeFieldFormatField() {
    return getFieldByClass(DateTimeFieldFormatField.class);
  }

  public DateTimeInputField getDateTimeInputField() {
    return getFieldByClass(DateTimeInputField.class);
  }

  public MandatoryField getMandatoryField() {
    return getFieldByClass(MandatoryField.class);
  }

  public DisplayTextField getDisplayTextField() {
    return getFieldByClass(DisplayTextField.class);
  }

  public ExamplesBox getExamplesBox() {
    return getFieldByClass(ExamplesBox.class);
  }

  public ConfigurationBox getConfigurationBox() {
    return getFieldByClass(ConfigurationBox.class);
  }

  @Order(10)
  public class MainBox extends AbstractGroupBox {

    @Order(10)
    public class ExamplesBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 4;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Examples");
      }

      @Order(10)
      public class ExampleColumn0Box extends AbstractGroupBox {

        @Override
        protected boolean getConfiguredBorderVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Override
        protected int getConfiguredWidthInPixel() {
          return 100;
        }

        @Override
        protected double getConfiguredGridWeightX() {
          return 0;
        }

        @Order(10)
        public class TitleColumnField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredStatusVisible() {
            return false;
          }

          @Override
          protected void execInitField() {
            setValue("");
          }
        }

        @Order(20)
        public class TitleDefaultField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredStatusVisible() {
            return false;
          }

          @Override
          protected void execInitField() {
            setValue(TEXTS.get("Default"));
          }
        }

        @Order(30)
        public class TitleMandatoryField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredStatusVisible() {
            return false;
          }

          @Override
          protected void execInitField() {
            setValue(TEXTS.get("Mandatory"));
          }
        }

        @Order(40)
        public class TitleDisabledField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredStatusVisible() {
            return false;
          }

          @Override
          protected void execInitField() {
            setValue(TEXTS.get("Disabled"));
          }
        }

        @Order(50)
        public class TitleLocaleField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredStatusVisible() {
            return false;
          }

          @Override
          protected void execInitField() {
            setValue(TEXTS.get("Locale"));
          }
        }
      }

      @Order(10)
      public class ExampleColumn1Box extends AbstractGroupBox {

        @Override
        protected boolean getConfiguredBorderVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Order(10)
        public class DateColumnField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredFont() {
            return "BOLD";
          }

          @Override
          protected void execInitField() {
            setValue(TEXTS.get("DateField"));
          }
        }

        @Order(20)
        public class DefaultField extends AbstractDateField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Default");
          }

          @Override
          protected String getConfiguredTooltipText() {
            return TEXTS.get("DateFieldKeyboardControl");
          }
        }

        @Order(30)
        public class MandatoryField extends AbstractDateField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Mandatory");
          }

          @Override
          protected boolean getConfiguredMandatory() {
            return true;
          }
        }

        @Order(40)
        public class DisabledField extends AbstractDateField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Disabled");
          }

          @Override
          protected void execInitField() {
            setValue(new Date());
          }
        }

        @Order(50)
        public class LocaleField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Locale");
          }

          @Override
          protected void execInitField() {
            setValue(NlsLocale.get().getDisplayName(NlsLocale.get()));
          }
        }
      }

      @Order(20)
      public class ExampleColumn2Box extends AbstractGroupBox {

        @Override
        protected boolean getConfiguredBorderVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Order(60)
        public class TimeColumnField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredFont() {
            return "BOLD";
          }

          @Override
          protected void execInitField() {
            setValue(TEXTS.get("TimeField"));
          }
        }

        @Order(70)
        public class TimeField extends AbstractTimeField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Default");
          }
        }

        @Order(80)
        public class TimeMandatoryField extends AbstractTimeField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Mandatory");
          }

          @Override
          protected boolean getConfiguredMandatory() {
            return true;
          }
        }

        @Order(90)
        public class TimeDisabledField extends AbstractTimeField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Disabled");
          }

          @Override
          protected void execInitField() {
            setValue(new Date());
          }
        }
      }

      @Order(30)
      public class ExampleColumn3Box extends AbstractGroupBox {

        @Override
        protected boolean getConfiguredBorderVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Order(110)
        public class DateTimeColumnField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredFont() {
            return "BOLD";
          }

          @Override
          protected void execInitField() {
            setValue(TEXTS.get("DateTimeField"));
          }
        }

        @Order(120)
        public class DateTimeField extends AbstractDateTimeField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Default");
          }
        }

        @Order(130)
        public class DateTimeMandatoryField extends AbstractDateTimeField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Mandatory");
          }

          @Override
          protected boolean getConfiguredMandatory() {
            return true;
          }
        }

        @Order(140)
        public class DateTimeDisabledField extends AbstractDateTimeField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Disabled");
          }

          @Override
          protected void execInitField() {
            setValue(new Date());
          }
        }
      }
    }

    @Order(20)
    public class ConfigurationBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 4;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Configure");
      }

      @Order(10)
      public class ConfigurationColumn0Box extends AbstractGroupBox {

        @Override
        protected boolean getConfiguredBorderVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Override
        protected int getConfiguredWidthInPixel() {
          return 100;
        }

        @Override
        protected double getConfiguredGridWeightX() {
          return 0;
        }

        @Order(10)
        public class TitleColumnField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredStatusVisible() {
            return false;
          }

          @Override
          protected void execInitField() {
            setValue("");
          }
        }

        @Order(20)
        public class TitleInputField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredStatusVisible() {
            return false;
          }

          @Override
          protected void execInitField() {
            setValue("Input");
          }
        }

        @Order(30)
        public class TitleValueField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredStatusVisible() {
            return false;
          }

          @Override
          protected void execInitField() {
            setValue(TEXTS.get("Value"));
          }
        }

        @Order(40)
        public class TitleFormatField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredStatusVisible() {
            return false;
          }

          @Override
          protected void execInitField() {
            setValue(TEXTS.get("Format"));
          }
        }

        @Order(50)
        public class TitleDisplayTextField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredStatusVisible() {
            return false;
          }

          @Override
          protected void execInitField() {
            setValue(TEXTS.get("DisplayText"));
          }
        }

        @Order(60)
        public class TitleLocaleField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredStatusVisible() {
            return false;
          }

          @Override
          protected void execInitField() {
            setValue(TEXTS.get("Locale"));
          }
        }

        @Order(70)
        public class TitleAutoDateField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredStatusVisible() {
            return false;
          }

          @Override
          protected void execInitField() {
            setValue("Auto date");
          }
        }
      }

      @Order(20)
      public class ConfigurationColumn1Box extends AbstractGroupBox {

        @Override
        protected boolean getConfiguredBorderVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Order(5)
        public class DateColumnField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredFont() {
            return "BOLD";
          }

          @Override
          protected void execInitField() {
            setValue(TEXTS.get("DateField"));
          }
        }

        @Order(10)
        public class InputField extends AbstractDateField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("DateFieldInput");
          }

          @Override
          protected String execFormatValue(final Date value) {
            final ClientRunContext runContext = ClientRunContexts.copyCurrent().withLocale(getConfigLocaleField().getValue());
            return runContext.call(() -> InputField.super.execFormatValue(value));
          }

          @Override
          public void setDisplayText(String s) {
            super.setDisplayText(s);
            getDisplayTextField().setValue(s);
          }

          @Override
          protected Date execValidateValue(Date rawValue) {
            Date date = super.execValidateValue(rawValue);
            if (getDontAllowCurrentDateField().getValue() && DateUtility.isSameDay(date, new Date())) {
              throw new VetoException("You are not allowed to select the current day");
            }
            return date;
          }
        }

        @Order(20)
        public class GetValueField extends AbstractStringField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("GetValue");
          }

          @Override
          protected Class<? extends IValueField> getConfiguredMasterField() {
            return InputField.class;
          }

          @Override
          protected void execChangedMasterValue(Object newMasterValue) {
            if (newMasterValue != null) {
              setValue(((Date) newMasterValue).toString());
            }
            else {
              setValue(null);
            }
          }
        }

        @Order(30)
        public class DateFieldFormatField extends AbstractStringField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("DateFieldFormat");
          }

          @Override
          protected String getConfiguredLabelFont() {
            return "ITALIC";
          }

          @Override
          protected String getConfiguredTooltipText() {
            return TEXTS.get("UseSimpleDateFormatPattern");
          }

          @Override
          protected void execChangedValue() {
            getInputField().setFormat(getValue());
          }
        }

        @Order(37)
        public class DisplayTextField extends AbstractStringField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("DisplayText");
          }

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }
        }

        @Order(40)
        public class ConfigLocaleField extends AbstractSmartField<Locale> {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Locale");
          }

          @Override
          protected String getConfiguredLabelFont() {
            return "ITALIC";
          }

          @Override
          protected Class<? extends ILookupCall<Locale>> getConfiguredLookupCall() {
            return (Class<? extends ILookupCall<Locale>>) LocaleLookupCall.class;
          }

          @Override
          protected String getConfiguredTooltipText() {
            return TEXTS.get("SelectLocale");
          }

          @Override
          protected void execChangedValue() {
            getInputField().setFormat(getDateFieldFormatField().getValue());
            getTimeInputField().setFormat(getTimeFieldFormatField().getValue());
            getDateTimeInputField().setFormat(getDateTimeFieldFormatField().getValue());
          }

          @Override
          protected void execInitField() {
            setValue(ClientSession.get().getLocale());
          }
        }

        @Order(50)
        public class AutoDateDateField extends AbstractDateTimeField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return "Auto date";
          }

          @Override
          protected void execChangedValue() {
            Date autoDate = getValue();
            getForm().getFieldByClass(InputField.class).setAutoDate(autoDate);
            getForm().getFieldByClass(TimeInputField.class).setAutoDate(autoDate);
            getForm().getFieldByClass(DateTimeInputField.class).setAutoDate(autoDate);
          }
        }

        @Order(2000)
        @ClassId("f12e34f8-a2d6-4c12-8246-a2db6d4aeec4")
        public class DontAllowCurrentDateField extends AbstractBooleanField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("DontAllowCurrentDate");
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected void execChangedValue() {
            InputField field = getForm().getFieldByClass(InputField.class);
            field.setValue(field.getValue()); // revalidate
          }
        }

        @Order(3000)
        @ClassId("ea115900-a7b4-410b-a14d-2405f74026ec")
        public class HasDateField extends AbstractBooleanField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("HasDate");
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected void execChangedValue() {
            getInputField().setHasDate(!getInputField().isHasDate());
          }

          @Override
          protected void execInitField() {
            setValue(getInputField().isHasDate());
          }
        }

        @Order(4000)
        @ClassId("86d06171-f93b-4d19-86f1-f3e1fc79cc82")
        public class HasTimeField extends AbstractBooleanField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("HasTime");
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected void execChangedValue() {
            getInputField().setHasTime(!getInputField().isHasTime());
          }

          @Override
          protected void execInitField() {
            setValue(getInputField().isHasTime());
          }
        }
      }

      @Order(30)
      public class ConfigurationColumn2Box extends AbstractGroupBox {

        @Override
        protected boolean getConfiguredBorderVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Order(5)
        public class TimeColumnField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredFont() {
            return "BOLD";
          }

          @Override
          protected void execInitField() {
            setValue(TEXTS.get("TimeField"));
          }
        }

        @Order(50)
        public class TimeInputField extends AbstractTimeField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("TimeFieldInput");
          }

          @Override
          protected String execFormatValue(final Date value) {
            final ClientRunContext runContext = ClientRunContexts.copyCurrent().withLocale(getConfigLocaleField().getValue());
            return runContext.call(() -> TimeInputField.super.execFormatValue(value));
          }

          @Override
          public void setDisplayText(String s) {
            super.setDisplayText(s);
            getTimeDisplayTextField().setValue(s);
          }

          @Override
          protected Date execValidateValue(Date rawValue) {
            Date date = super.execValidateValue(rawValue);
            if (date == null) {
              return null;
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            if (getDontAllow1000TimeField().getValue() && cal.get(Calendar.HOUR_OF_DAY) == 10 && cal.get(Calendar.MINUTE) == 0) {
              throw new VetoException("You are not allowed to select 10:00");
            }
            return date;
          }
        }

        @Order(60)
        public class GetTimeValueField extends AbstractStringField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("GetValue");
          }

          @Override
          protected Class<? extends IValueField> getConfiguredMasterField() {
            return TimeInputField.class;
          }

          @Override
          protected void execChangedMasterValue(Object newMasterValue) {
            if (newMasterValue != null) {
              setValue(((Date) newMasterValue).toString());
            }
            else {
              setValue(null);
            }
          }
        }

        @Order(70)
        public class TimeFieldFormatField extends AbstractStringField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("TimeFieldFormat");
          }

          @Override
          protected String getConfiguredLabelFont() {
            return "ITALIC";
          }

          @Override
          protected String getConfiguredTooltipText() {
            return TEXTS.get("UseSimpleDateFormatPattern");
          }

          @Override
          protected void execChangedValue() {
            getTimeInputField().setFormat(getTimeFieldFormatField().getValue());
          }
        }

        @Order(77)
        public class TimeDisplayTextField extends AbstractStringField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("DisplayText");
          }

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }
        }

        @Order(2000)
        @ClassId("b7ed1507-62b8-447f-b23f-3d9883aec82c")
        public class DontAllow1000TimeField extends AbstractBooleanField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("DontAllow1000Time");
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected void execChangedValue() {
            TimeInputField field = getForm().getFieldByClass(TimeInputField.class);
            field.setValue(field.getValue()); // revalidate
          }
        }

      }

      @Order(40)
      public class ConfigurationColumn3Box extends AbstractGroupBox {

        @Override
        protected boolean getConfiguredBorderVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Order(5)
        public class DateTimeColumnField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredFont() {
            return "BOLD";
          }

          @Override
          protected void execInitField() {
            setValue(TEXTS.get("DateTimeField"));
          }
        }

        @Order(90)
        public class DateTimeInputField extends AbstractDateTimeField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("DateTimeFieldInput");
          }

          @Override
          protected String execFormatValue(final Date value) {
            final ClientRunContext runContext = ClientRunContexts.copyCurrent().withLocale(getConfigLocaleField().getValue());
            return runContext.call(() -> DateTimeInputField.super.execFormatValue(value));
          }

          @Override
          public void setDisplayText(String s) {
            super.setDisplayText(s);
            getDateTimeDisplayTextField().setValue(s);
          }
        }

        @Order(100)
        public class GetDateTimeValueField extends AbstractStringField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("GetValue");
          }

          @Override
          protected Class<? extends IValueField> getConfiguredMasterField() {
            return DateTimeInputField.class;
          }

          @Override
          protected void execChangedMasterValue(Object newMasterValue) {
            if (newMasterValue != null) {
              setValue(((Date) newMasterValue).toString());
            }
            else {
              setValue(null);
            }
          }
        }

        @Order(110)
        public class DateTimeFieldFormatField extends AbstractStringField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("DateTimeFieldFormat");
          }

          @Override
          protected String getConfiguredLabelFont() {
            return "ITALIC";
          }

          @Override
          protected String getConfiguredTooltipText() {
            return TEXTS.get("UseSimpleDateFormatPattern");
          }

          @Override
          protected void execChangedValue() {
            getDateTimeInputField().setFormat(getDateTimeFieldFormatField().getValue());
          }
        }

        @Order(2000)
        public class DateTimeDisplayTextField extends AbstractStringField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("DisplayText");
          }

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }
        }
      }
    }

    private void updateDateTimeFields(Date d) {
      if (d != null) {
        getInputField().setValue(d);
        getTimeInputField().setValue(d);
        getDateTimeInputField().setValue(d);
        getDefaultField().setValue(d);
        getMandatoryField().setValue(d);
        getTimeField().setValue(d);
        getTimeMandatoryField().setValue(d);
        getDateTimeField().setValue(d);
        getDateTimeMandatoryField().setValue(d);
      }

      Locale oldLocale = NlsLocale.get();
      try {
        NlsLocale.set(getConfigLocaleField().getValue());
        getInputField().setFormat(getDateFieldFormatField().getValue());
        getTimeInputField().setFormat(getTimeFieldFormatField().getValue());
        getDateTimeInputField().setFormat(getDateTimeFieldFormatField().getValue());
      }
      finally {
        NlsLocale.set(oldLocale);
      }
    }

    @Order(10)
    public class SetDateMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("SetDate");
      }

      @Order(10)
      public class NowMenu extends AbstractMenu {

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("Now");
        }

        @Override
        protected void execAction() {
          updateDateTimeFields(new Date());
        }
      }

      @Order(20)
      public class NineteenSeventyMenu extends AbstractMenu {

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("NineteenSeventy");
        }

        @Override
        protected void execAction() {
          Date d = new Date();
          d.setTime(0);
          updateDateTimeFields(d);
        }
      }

    }

    @Order(20)
    public class SetFormatMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("SetFormat");
      }

      @Order(10)
      public class SampleFormatMenu extends AbstractMenu {

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("SampleFormat");
        }

        @Override
        protected void execAction() {
          getDateFieldFormatField().setValue("EEEE, dd. MMMM yyyy");
          getTimeFieldFormatField().setValue("HH:mm:ss Z");
          getDateTimeFieldFormatField().setValue("MM/dd/yy h:mm a");
        }
      }

      @Order(20)
      public class ShortFormatMenu extends AbstractMenu {

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("ShortFormat");
        }

        @Override
        protected void execAction() {
          DateFormat df = BEANS.get(DateFormatProvider.class).getDateInstance(DateFormat.SHORT, getConfigLocaleField().getValue());
          if (df instanceof SimpleDateFormat) {
            getDateFieldFormatField().setValue(((SimpleDateFormat) df).toPattern());
          }
        }
      }

      @Order(30)
      public class MediumFormatMenu extends AbstractMenu {

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("MediumFormat");
        }

        @Override
        protected void execAction() {
          DateFormat df = BEANS.get(DateFormatProvider.class).getDateInstance(DateFormat.MEDIUM, getConfigLocaleField().getValue());
          if (df instanceof SimpleDateFormat) {
            getDateFieldFormatField().setValue(((SimpleDateFormat) df).toPattern());
          }
        }
      }

      @Order(40)
      public class CustomMediumFormatMenu extends AbstractMenu {

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("CustomMediumFormat");
        }

        @Override
        protected void execAction() {
          DateFormat df = BEANS.get(DateFormatProvider.class).getDateInstance(CustomDateFormatProvider.CUSTOM_MEDIUM, getConfigLocaleField().getValue());
          if (df instanceof SimpleDateFormat) {
            getDateFieldFormatField().setValue(((SimpleDateFormat) df).toPattern());
          }
        }
      }
    }

    @Order(30)
    public class ToggleAllowedDatesButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("AllowedDates");
      }

      @Override
      protected int getConfiguredDisplayStyle() {
        return IButton.DISPLAY_STYLE_TOGGLE;
      }

      @Override
      protected String getConfiguredTooltipText() {
        return TEXTS.get("AllowedDatesTooltip");
      }

      @Override
      protected void execSelectionChanged(boolean selection) {
        List<Date> allowedDates = null;
        if (selection) {
          allowedDates = new ArrayList<>();
          Date today = DateUtility.truncDate(new Date());
          allowedDates.add(DateUtility.addTime(today, Calendar.DATE, -1));
          allowedDates.add(DateUtility.addTime(today, Calendar.DATE, 1));
          allowedDates.add(DateUtility.addTime(today, Calendar.MONTH, -1));
          allowedDates.add(DateUtility.addTime(today, Calendar.MONTH, 1));
        }
        getDefaultField().setAllowedDates(allowedDates);
      }
    }

    @Order(170)
    public class CloseButton extends AbstractCloseButton {
      @Override
      protected void execClickAction() {
      }
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
