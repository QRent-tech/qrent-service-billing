package ee.qrental.common.core.validation;

import ee.qrent.common.in.validation.AttributeChecker;
import ee.qrent.common.in.validation.ViolationsCollector;

import java.util.function.Supplier;

import static java.lang.String.format;

public class AttributeCheckerImpl implements AttributeChecker {
  @Override
  public void checkLength(
      final String attributeName,
      final String attributeValue,
      final Integer minLength,
      final Integer maxLength,
      final ViolationsCollector violationsCollector) {

    if (minLength == null && maxLength == null
        || attributeName == null
        || violationsCollector == null) {
      throw invalidCallExceptionSupplier().get();
    }

    if (attributeValue == null) {
      return;
    }
    final int length = attributeValue.length();
    if (minLength == null) {
      if (length > maxLength) {
        violationsCollector.collect(
            format(
                "Invalid value for %s. Current length: %d. Valid length must be not more then: %d)",
                attributeName, length, maxLength));
      }
    }

    if (maxLength == null) {
      if (length < minLength) {
        violationsCollector.collect(
            format(
                "Invalid value for %s. Current length: %d. Valid length must be not less then: %d)",
                attributeName, length, minLength));
      }
    }

    if (length < minLength || length > maxLength) {
      violationsCollector.collect(
          format(
              "Invalid value for %s. Current length: %d. Valid length must be in a range: [%d ... %d])",
              attributeName, length, minLength, maxLength));
    }
  }

  @Override
  public void checkFixedLength(
      final String attributeName,
      final Object attributeValue,
      final Integer length,
      final ViolationsCollector violationsCollector) {
    if (attributeValue == null) {
      return;
    }
    final int attributeValueLength = attributeValue.toString().length();

    if (attributeValueLength != length) {
      violationsCollector.collect(
          format(
              "Invalid value for %s. Current length: %d. Valid length must equal to: %d)",
              attributeName, attributeValueLength, length));
    }
  }

  @Override
  public void checkRequired(
      final String attributeName,
      final Object attributeValue,
      final ViolationsCollector violationsCollector) {
    if (attributeName == null || violationsCollector == null) {
      throw invalidCallExceptionSupplier().get();
    }

    if (attributeValue == null) {
      violationsCollector.collect(format("Invalid value for %s. Value must be set", attributeName));
    }
  }

  private Supplier<RuntimeException> invalidCallExceptionSupplier() {
    return () ->
        new RuntimeException(
            "Invalid AttributeChecker method call. Please check all non null parameters.");
  }
}
