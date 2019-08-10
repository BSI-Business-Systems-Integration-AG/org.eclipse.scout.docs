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
package org.eclipsescout.demo.bahbah.shared.util;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipsescout.demo.bahbah.shared.services.code.UserRoleCodeType.AdministratorCode;
import org.eclipsescout.demo.bahbah.shared.services.code.UserRoleCodeType.UserCode;

public class SharedUserUtility {

  public static final int MAX_USERNAME_LENGTH = 32;
  public static final int MIN_USERNAME_LENGTH = 3;

  public static final int MAX_PASSWORD_LENGTH = 64;
  public static final int MIN_PASSWORD_LENGTH = 3;

  public static void checkUsername(String username) {
    if (StringUtility.length(username) < MIN_USERNAME_LENGTH) {
      throw new VetoException(TEXTS.get("UsernameMinLength", "" + MIN_USERNAME_LENGTH));
    }
    if (username.contains("@")) {
      throw new VetoException(TEXTS.get("UsernameSpecialChars"));
    }
    if (StringUtility.length(username) > MAX_USERNAME_LENGTH) {
      throw new VetoException();
    }
  }

  public static void checkPermissionId(Integer id) {
    if (!UserCode.ID.equals(id) && !AdministratorCode.ID.equals(id)) {
      throw new VetoException();
    }
  }

  public static void checkPassword(char[] password) {
    if (password == null || password.length < MIN_PASSWORD_LENGTH) {
      throw new VetoException(TEXTS.get("PasswordMinLength", "" + MIN_PASSWORD_LENGTH));
    }
    if (password.length > MAX_PASSWORD_LENGTH) {
      throw new VetoException();
    }
  }
}
