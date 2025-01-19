package ee.qrent.common.in.validation;

public interface AttributeChecker {
  void checkLength(
      final String attributeName,
      final String attributeValue,
      final Integer minLength,
      final Integer maxLength,
      final ViolationsCollector violationsCollector);
}
