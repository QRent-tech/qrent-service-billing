package ee.qrent.common.in.validation;

public interface AttributeChecker {
  void checkLength(
      final String attributeName,
      final String attributeValue,
      final Integer minLength,
      final Integer maxLength,
      final ViolationsCollector violationsCollector);

  void checkFixedLength(
      final String attributeName,
      final Object attributeValue,
      final Integer length,
      final ViolationsCollector violationsCollector);

  void checkRequired(
      final String attributeName,
      final Object attributeValue,
      final ViolationsCollector violationsCollector);


}
