package com.chatop.backend.validation;

public final class ValidationPatterns {

  public static final int EMAIL_MAX = 255;
  public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,20}$";
  public static final String EMAIL_MANDATORY_MESSAGE = "The email is mandatory.";
  public static final String EMAIL_SIZE_MESSAGE = "The email must not exceed " + EMAIL_MAX + " characters.";
  public static final String EMAIL_FORMAT_MESSAGE = "Invalid email format.";

  public static final int NAME_MIN = 3;
  public static final int NAME_MAX = 50;
  public static final String NAME_REGEX = "[\\p{L}\\p{M}]+(?:(?:[ '’-]|\\. ?)[\\p{L}\\p{M}]+)*\\.?";
  public static final String NAME_MANDATORY_MESSAGE = "The name is mandatory.";
  public static final String NAME_SIZE_MESSAGE =
    "The name must contain between " + NAME_MIN + " and " + NAME_MAX + " characters.";
  public static final String NAME_FORMAT_MESSAGE =
    "The name must contain only letters, separated by a single space, hyphen, apostrophe or period.";

  public static final String PASSWORD_MANDATORY_MESSAGE = "The password is mandatory.";


  public static final int RENTAL_NAME_MIN = 3;
  public static final int RENTAL_NAME_MAX = 100;
  public static final String RENTAL_NAME_SIZE_MESSAGE =
    "The name must contain between " + RENTAL_NAME_MIN + " and " + RENTAL_NAME_MAX + " characters.";
  public static final String RENTAL_NAME_FORMAT_MESSAGE = "The name must be a single line of text.";

  public static final int SURFACE_MIN = 1;
  public static final int SURFACE_MAX = 10_000;
  public static final String SURFACE_MANDATORY_MESSAGE = "The surface is mandatory.";
  public static final String SURFACE_RANGE_MESSAGE =
    "The surface must be between " + SURFACE_MIN + " and " + SURFACE_MAX + ".";

  public static final int PRICE_MIN = 1;
  public static final int PRICE_MAX = 1_000_000;
  public static final String PRICE_MANDATORY_MESSAGE = "The price is mandatory.";
  public static final String PRICE_RANGE_MESSAGE =
    "The price must be between " + PRICE_MIN + " and " + PRICE_MAX + ".";

  public static final int PICTURE_MAX = 255;
  public static final String PICTURE_MANDATORY_MESSAGE = "The picture is mandatory.";
  public static final String PICTURE_SIZE_MESSAGE =
    "The picture URL must not exceed " + PICTURE_MAX + " characters.";

  public static final String SINGLE_LINE_TEXT_REGEX = "[^\\x00-\\x1F\\x7F]*";
  public static final String FREE_TEXT_REGEX = "[^\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]*";

  public static final int DESCRIPTION_MAX = 2000;
  public static final String DESCRIPTION_MANDATORY_MESSAGE = "The description is mandatory.";
  public static final String DESCRIPTION_SIZE_MESSAGE =
    "The description must not exceed " + DESCRIPTION_MAX + " characters.";
  public static final String DESCRIPTION_FORMAT_MESSAGE = "The description contains unauthorized characters.";

  public static final String OWNER_MANDATORY_MESSAGE = "The owner is mandatory.";

  private ValidationPatterns() {
  }
}
