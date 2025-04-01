package ee.qrent.insurance.api.in.query;

public interface GetQKaskoQuery {
  boolean hasQKasko(final Long driverId, final Long qWeekId);
}
