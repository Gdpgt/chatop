package com.chatop.backend.validation;

public final class ValidationPatterns {

  public static final int EMAIL_MAX = 255;
  public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,20}$";
  public static final String EMAIL_MANDATORY_MESSAGE = "The email is mandatory.";
  public static final String EMAIL_SIZE_MESSAGE = "The email must not exceed " + EMAIL_MAX + " characters.";
  public static final String EMAIL_FORMAT_MESSAGE = "Invalid email format.";

  public static final int NAME_MIN = 3;
  public static final int NAME_MAX = 50;
  public static final String NAME_REGEX = "^[a-zA-ZÀ-ÿ\\-'’ ]+$";
  public static final String NAME_MANDATORY_MESSAGE = "The name is mandatory.";
  public static final String NAME_SIZE_MESSAGE =
    "The name must contain between " + NAME_MIN + " and " + NAME_MAX + " characters.";
  public static final String NAME_FORMAT_MESSAGE = "The name contains unauthorized characters.";

  public static final String PASSWORD_MANDATORY_MESSAGE = "The password is mandatory.";

  private ValidationPatterns() {
  }
}
