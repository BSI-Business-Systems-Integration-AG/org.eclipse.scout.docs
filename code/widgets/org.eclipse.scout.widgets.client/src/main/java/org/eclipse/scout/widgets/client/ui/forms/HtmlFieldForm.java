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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.IHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBoxes;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.html.HTML;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.eclipse.scout.rt.platform.util.NumberUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.platform.util.TriState;
import org.eclipse.scout.rt.platform.util.date.DateUtility;
import org.eclipse.scout.widgets.client.ResourceBase;
import org.eclipse.scout.widgets.client.ui.desktop.outlines.IAdvancedExampleForm;
import org.eclipse.scout.widgets.client.ui.forms.HtmlFieldForm.MainBox.CloseButton;
import org.eclipse.scout.widgets.client.ui.forms.HtmlFieldForm.MainBox.ConfigurationGroupBox;
import org.eclipse.scout.widgets.client.ui.forms.HtmlFieldForm.MainBox.ConfigurationGroupBox.SetContentButtonsBox;
import org.eclipse.scout.widgets.client.ui.forms.HtmlFieldForm.MainBox.ConfigurationGroupBox.SetContentButtonsBox.BasicHtmlButton;
import org.eclipse.scout.widgets.client.ui.forms.HtmlFieldForm.MainBox.ConfigurationGroupBox.SetContentButtonsBox.BlankButton;
import org.eclipse.scout.widgets.client.ui.forms.HtmlFieldForm.MainBox.GroupBox;
import org.eclipse.scout.widgets.client.ui.forms.HtmlFieldForm.MainBox.GroupBox.HtmlField;
import org.eclipse.scout.widgets.client.ui.forms.fields.formfield.AbstractFormFieldPropertiesBox;
import org.eclipse.scout.widgets.shared.Icons;

@Order(6000.0)
@ClassId("b84af891-a79c-4e3f-80b8-242eea1e6d71")
public class HtmlFieldForm extends AbstractForm implements IAdvancedExampleForm {

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("HTMLField");
  }

  @Override
  protected void execInitForm() {
    // Load default content
    getBasicHtmlButton().doClick();
  }

  @Override
  public void startPageForm() {
    startInternal(new PageFormHandler());
  }

  public BlankButton getBlankButton() {
    return getFieldByClass(BlankButton.class);
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public SetContentButtonsBox getSetContentButtonsBox() {
    return getFieldByClass(SetContentButtonsBox.class);
  }

  public HtmlField getHtmlField() {
    return getFieldByClass(HtmlField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public BasicHtmlButton getBasicHtmlButton() {
    return getFieldByClass(BasicHtmlButton.class);
  }

  public ConfigurationGroupBox getConfigurationGroupBox() {
    return getFieldByClass(ConfigurationGroupBox.class);
  }

  public GroupBox getGroupBox() {
    return getFieldByClass(GroupBox.class);
  }

  protected void loadFile(String simpleName) {
    loadFile(simpleName, null);
  }

  protected void loadFile(String simpleName, List<BinaryResource> attachments) {
    String html = null;
    if (simpleName != null) {
      try (InputStream in = ResourceBase.class.getResource("html/" + simpleName).openStream()) {
        html = IOUtility.readString(in, null);
      }
      catch (IOException e) {
        throw new ProcessingException("Error while loading file {}", simpleName, e);
      }
    }
    getHtmlField().setValue(html);
    getHtmlField().setAttachments(attachments);
  }

  @Order(10)
  @ClassId("9d851138-a523-427d-be09-731c99044970")
  public class MainBox extends AbstractGroupBox {

    @Override
    protected void execInitField() {
      setStatusVisible(false);
    }

    @Order(10)
    @ClassId("f643e6f6-0df5-4222-837c-cbecabc54292")
    public class GroupBox extends AbstractGroupBox {

      @Order(10)
      @ClassId("b5118fc8-c56d-4033-8368-dd0a413b35c5")
      public class HtmlField extends AbstractHtmlField {

        @Override
        protected int getConfiguredGridH() {
          return 12;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
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
        protected void execAppLinkAction(String ref) {
          MessageBoxes.createOk()
              .withHeader("AppLink clicked")
              .withBody("Ref:\n" + ref).show();
        }
      }
    }

    @Order(20)
    @ClassId("ae485a2c-dfb8-4a23-a529-3262afc09b8a")
    public class ConfigurationGroupBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Configure");
      }

      @Override
      protected boolean getConfiguredExpandable() {
        return true;
      }

      @Override
      protected TriState getConfiguredResponsive() {
        return TriState.FALSE;
      }

      @Order(5)
      @ClassId("99e3f0b2-92b5-4cab-b102-750beefd6bf1")
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
        protected void execChangedValue() {
          getHtmlField().setSelectable(isChecked());
        }

        @Override
        protected void execInitField() {
          setChecked(getHtmlField().isSelectable());
        }
      }

      @Order(10)
      @ClassId("79efda24-776b-477c-9eec-5dd53704a566")
      public class ScrollBarEnabledField extends AbstractBooleanField {

        @Override
        protected String getConfiguredLabel() {
          return "Scroll Bar Enabled";
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected void execChangedValue() {
          getHtmlField().setScrollBarEnabled(isChecked());
        }

        @Override
        protected void execInitField() {
          setChecked(getHtmlField().isScrollBarEnabled());
        }
      }

      @Order(20)
      @ClassId("d73fb3b0-1348-420d-8758-4656c876f587")
      public class ScrollToAnchorField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("JumpToAnchor");
        }

        @Override
        protected void execChangedValue() {
          getHtmlField().setScrollToAnchor(getValue());
        }

        @Override
        protected void execInitField() {
          setValue(getHtmlField().getScrollToAnchor());
          getHtmlField().addPropertyChangeListener(IHtmlField.PROP_SCROLL_TO_ANCHOR, event -> setValue(getHtmlField().getScrollToAnchor()));
        }
      }

      @Order(40)
      @ClassId("3a5a4e67-44ae-48f4-a784-63b504dd288c")
      public class SetContentButtonsBox extends AbstractSequenceBox {

        @Override
        protected String getConfiguredLabel() {
          return "Set content to";
        }

        @Override
        protected byte getConfiguredLabelPosition() {
          return LABEL_POSITION_TOP;
        }

        @Override
        protected int getConfiguredGridW() {
          return FULL_WIDTH;
        }

        @Order(10)
        @ClassId("4716d2b8-a621-4ed7-b543-9b7f20349373")
        public class BlankButton extends AbstractLinkButton {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Blank");
          }

          @Override
          protected void execClickAction() {
            getHtmlField().setAttachments(null);
            getHtmlField().setValue(null);
          }
        }

        @Order(20)
        @ClassId("9e496e3b-0ef5-4df2-919c-28d4566c89e6")
        public class BasicHtmlButton extends AbstractLinkButton {

          @Override
          protected String getConfiguredLabel() {
            return "Basic HTML";
          }

          @Override
          protected void execClickAction() {
            BinaryResource file = IOUtility.readBinaryResource(ResourceBase.class.getResource("icons/eclipse_scout_logo.png"));
            loadFile("HtmlFieldCustomHtml.html", Collections.singletonList(file));
          }
        }

        @Order(40)
        @ClassId("8d956289-ff2c-4ccc-bb52-a53090c8556b")
        public class ScoutHtmlButton extends AbstractLinkButton {

          @Override
          protected String getConfiguredLabel() {
            return "Scout HTML";
          }

          @Override
          protected void execClickAction() {
            List<BinaryResource> files = new ArrayList<>();
            files.add(IOUtility.readBinaryResource(ResourceBase.class.getResource("images/download.png")));
            files.add(IOUtility.readBinaryResource(ResourceBase.class.getResource("images/s.png")));
            files.add(IOUtility.readBinaryResource(ResourceBase.class.getResource("images/bird_1008.jpg"), "bird.jpg"));
            loadFile("ScoutHtml.html", files);
          }
        }

        @Order(70)
        @ClassId("f1afc482-caf8-4204-a6e9-b3a0b3fd1b6e")
        public class ALotOfContentHtmlButton extends AbstractLinkButton {

          @Override
          protected String getConfiguredLabel() {
            return "A lot of content (with anchor)";
          }

          @Override
          protected void execClickAction() {
            loadFile("ALotOfContent.html");
          }
        }

        @Order(80)
        @ClassId("4fc3c47c-2275-4b8d-87c2-04c2538b346e")
        public class HtmlBuilderButton extends AbstractLinkButton {

          @Override
          protected String getConfiguredLabel() {
            return "Dynamic content";
          }

          @Override
          protected void execClickAction() {
            String randomColor = "#"
                + StringUtility.lpad(Integer.toHexString(NumberUtility.randomInt(256)), "0", 2)
                + StringUtility.lpad(Integer.toHexString(NumberUtility.randomInt(256)), "0", 2)
                + StringUtility.lpad(Integer.toHexString(NumberUtility.randomInt(256)), "0", 2);
            getHtmlField().setValue(HTML.fragment(
                HTML.icon(Icons.ClockBold)
                    .style("font-size: 50px; padding-right: 10px; vertical-align: middle; color: " + randomColor),
                HTML.span(
                    "The current server time is: ",
                    HTML.bold(DateUtility.formatTime(new Date())))
                    .style("vertical-align: middle;"))
                .toHtml());
            getHtmlField().setAttachments(null);
          }
        }
      }
    }

    @Order(30)
    @ClassId("df29263e-0aaf-479f-8cce-e3050e676308")
    public class FormFieldPropertiesBox extends AbstractFormFieldPropertiesBox {

      @Override
      protected void execInitField() {
        setField(getHtmlField());
      }
    }

    @Order(50)
    @ClassId("6c485f0d-7346-41e4-9331-164f76c0e73a")
    public class CloseButton extends AbstractCloseButton {
    }

    @Order(70)
    @ClassId("f6dcd706-fd9e-49d3-8785-d72c67a31d9e")
    public class ScrollToEndMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("ScrollToEnd");
      }

      @Override
      protected void execAction() {
        getHtmlField().scrollToEnd();
      }
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
